package com.wyg.common.datascope.aspect;

import com.wyg.common.core.enums.DataScopeTypes;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import com.wyg.common.core.utils.StringUtils;
import com.wyg.common.core.web.domain.BaseEntity;
import com.wyg.common.datascope.annotation.DataScope;
import com.wyg.common.security.utils.SecurityUtils;
import com.wyg.system.api.domain.SysRole;
import com.wyg.system.api.domain.SysUser;
import com.wyg.system.api.model.LoginUser;

import java.util.Objects;

/**
 * 数据过滤处理
 *
 * @author wyg
 */
@Aspect
@Component
public class DataScopeAspect {

    /**
     * 数据权限过滤关键字
     */
    public static final String DATA_SCOPE = "dataScope";

    @Before("@annotation(controllerDataScope)")
    public void doBefore(JoinPoint point, DataScope controllerDataScope) throws Throwable {
        clearDataScope(point);
        handleDataScope(point, controllerDataScope);
    }

    protected void handleDataScope(final JoinPoint joinPoint, DataScope controllerDataScope) {
        // 获取当前的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (StringUtils.isNotNull(loginUser)) {
            SysUser currentUser = loginUser.getSysUser();
            // 如果是超级管理员，则不过滤数据
            if (StringUtils.isNotNull(currentUser) && !currentUser.isAdmin()) {
                dataScopeFilter(joinPoint, loginUser, controllerDataScope);
            }
        }
    }


    /**
     * 数据范围过滤
     *
     * @param joinPoint 切点
     * @param user      用户
     */
    public static void dataScopeFilter(JoinPoint joinPoint, LoginUser user, DataScope controllerDataScope) {
        com.wyg.system.api.model.DataScope scope = user.getScope();

        StringBuilder sqlString = new StringBuilder();
        String scopeType = scope.getDataScope();
        switch (Objects.requireNonNull(DataScopeTypes.getValue(scopeType))) {
            case ALL:
                sqlString = new StringBuilder();
                break;
            case CUSTOM:
            case DEPT:
            case DEPT_AND_CHILD:
                String deptIds = "";
                int index = 0;
                for (Long deptId : scope.getDeptScope()) {
                    deptIds += deptId.longValue();
                    if (index < scope.getDeptScope().size() - 1) {
                        deptIds += ",";
                    }
                    index++;
                }

                if (StringUtils.isEmpty(controllerDataScope.primaryTableAlias())) {
                    if (StringUtils.isEmpty(deptIds)) {
                        sqlString.append(StringUtils.format(" OR {}= 0 ", controllerDataScope.deptFieldAlias(), deptIds));
                    } else {
                        sqlString.append(StringUtils.format(" OR {} IN ({}) ", controllerDataScope.deptFieldAlias(), deptIds));
                    }
                } else {
                    if (StringUtils.isEmpty(deptIds)) {
                        sqlString.append(StringUtils.format(" OR {}.{}= 0 ", controllerDataScope.primaryTableAlias(), controllerDataScope.deptFieldAlias(), deptIds));
                    } else {
                        sqlString.append(StringUtils.format(" OR {}.{} IN ({}) ", controllerDataScope.primaryTableAlias(), controllerDataScope.deptFieldAlias(), deptIds));
                    }
                }

                break;
            case SELF:
                String userIds = "";
                index = 0;
                for (Long userId : scope.getUserScope()) {
                    userIds += userId.longValue();
                    if (index < scope.getUserScope().size() - 1) {
                        userIds += ",";
                    }
                    index++;
                }

                if (StringUtils.isEmpty(controllerDataScope.primaryTableAlias())) {
                    if (StringUtils.isEmpty(userIds)) {
                        sqlString.append(StringUtils.format(" OR {}= 0 ",  controllerDataScope.userFieldAlias(), userIds));
                    } else {
                        sqlString.append(StringUtils.format(" OR {} IN ({}) ", controllerDataScope.userFieldAlias(), userIds));
                    }
                }else{
                    if (StringUtils.isEmpty(userIds)) {
                        sqlString.append(StringUtils.format(" OR {}.{}= 0 ", controllerDataScope.primaryTableAlias(), controllerDataScope.userFieldAlias(), userIds));
                    } else {
                        sqlString.append(StringUtils.format(" OR {}.{} IN ({}) ", controllerDataScope.primaryTableAlias(), controllerDataScope.userFieldAlias(), userIds));
                    }
                }

                break;
        }

        if (StringUtils.isNotBlank(sqlString.toString())) {
            Object params = joinPoint.getArgs()[0];
            if (StringUtils.isNotNull(params) && params instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) params;
                baseEntity.getParams().put(DATA_SCOPE, " AND (" + sqlString.substring(4) + ")");
            }
        }

    }


    /**
     * 拼接权限sql前先清空params.dataScope参数防止注入
     */
    private void clearDataScope(final JoinPoint joinPoint) {
        Object params = joinPoint.getArgs()[0];
        if (StringUtils.isNotNull(params) && params instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) params;
            baseEntity.getParams().put(DATA_SCOPE, "");
        }
    }
}
