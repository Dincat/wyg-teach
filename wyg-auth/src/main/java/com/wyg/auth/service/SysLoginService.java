package com.wyg.auth.service;

import cn.hutool.core.util.StrUtil;
import com.wyg.auth.form.RegisterBody;
import com.wyg.common.core.enums.DataScopeTypes;
import com.wyg.common.core.enums.UserTypes;
import com.wyg.system.api.RemoteDeptService;
import com.wyg.system.api.domain.SysDept;
import com.wyg.system.api.domain.SysRole;
import com.wyg.system.api.model.DataScope;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.wyg.common.core.constant.Constants;
import com.wyg.common.core.constant.SecurityConstants;
import com.wyg.common.core.constant.UserConstants;
import com.wyg.common.core.domain.R;
import com.wyg.common.core.enums.UserStatus;
import com.wyg.common.core.exception.ServiceException;
import com.wyg.common.core.utils.ServletUtils;
import com.wyg.common.core.utils.StringUtils;
import com.wyg.common.core.utils.ip.IpUtils;
import com.wyg.common.security.utils.SecurityUtils;
import com.wyg.system.api.RemoteLogService;
import com.wyg.system.api.RemoteUserService;
import com.wyg.system.api.domain.SysLogininfor;
import com.wyg.system.api.domain.SysUser;
import com.wyg.system.api.model.LoginUser;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 登录校验方法
 *
 * @author wyg
 */
@Component
public class SysLoginService {
    @Autowired
    private RemoteLogService remoteLogService;

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private RemoteDeptService remoteDeptService;


    /**
     * 登录
     */
    public LoginUser login(String username, String password) {
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password)) {
            recordLogininfor(username, Constants.LOGIN_FAIL, "用户/密码必须填写");
            throw new ServiceException("用户/密码必须填写");
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            recordLogininfor(username, Constants.LOGIN_FAIL, "用户密码不在指定范围");
            throw new ServiceException("用户密码不在指定范围");
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            recordLogininfor(username, Constants.LOGIN_FAIL, "用户名不在指定范围");
            throw new ServiceException("用户名不在指定范围");
        }
        // 查询用户信息
        R<LoginUser> userResult = remoteUserService.getUserInfo(username, SecurityConstants.INNER);

        if (R.FAIL == userResult.getCode()) {
            throw new ServiceException(userResult.getMsg());
        }

        if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData())) {
            recordLogininfor(username, Constants.LOGIN_FAIL, "登录用户不存在");
            throw new ServiceException("登录用户：" + username + " 不存在");
        }
        LoginUser userInfo = userResult.getData();
        SysUser user = userResult.getData().getSysUser();
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            recordLogininfor(username, Constants.LOGIN_FAIL, "对不起，您的账号已被删除");
            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
        }
        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            recordLogininfor(username, Constants.LOGIN_FAIL, "用户已停用，请联系管理员");
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }
        if (!SecurityUtils.matchesPassword(password, user.getPassword())) {
            recordLogininfor(username, Constants.LOGIN_FAIL, "用户密码错误");
            throw new ServiceException("用户不存在/密码错误");
        }


        userInfo.setScope(getDataScope(user));

        recordLogininfor(username, Constants.LOGIN_SUCCESS, "登录成功");
        return userInfo;
    }


    public DataScope getDataScope(SysUser user) {
        DataScope scope = new DataScope();
        // 1.判断是否为超管用户
        if (user.isAdmin()) {
            scope.setDataScope(DataScopeTypes.ALL.getCode());
            return scope;
        }
        // 2.判断有无全部数据权限角色
        for (SysRole role : user.getRoles()) {
            if (StrUtil.equals(role.getDataScope(), DataScopeTypes.ALL.getCode())) {
                scope.setDataScope(DataScopeTypes.ALL.getCode());
                return scope;
            }
        }
        // 3.组建权限集
        Set<Long> deptScope = new HashSet<>(), userScope = new HashSet<>(), customRoleId = new HashSet<>();
        int isCustom = 0, isDept = 0, isDeptAndChild = 0, isSelf = 0;
        for (SysRole role : user.getRoles()) {
            switch (Objects.requireNonNull(DataScopeTypes.getValue(role.getDataScope()))) {
                case CUSTOM:
                    if (isCustom++ == 0) {
                        customRoleId.add(role.getRoleId());
                    }
                    break;
                case DEPT:
                    if (isDept++ == 0)
                        deptScope.add(user.getDeptId());
                    break;
                case DEPT_AND_CHILD:
                    if (isDeptAndChild++ == 0) {
                        List<SysDept> deptList = remoteDeptService.selectSelfAndChildrenDeptById(user.getDeptId(), SecurityConstants.INNER).getData();
                        if (CollectionUtils.isEmpty(deptList)) break;
                        deptScope.addAll(deptList.stream().map(SysDept::getDeptId).collect(Collectors.toSet()));
                    }
                    break;
                case SELF:
                    if (isSelf++ == 0)
                        userScope.add(user.getUserId());
                    break;
                default:
                    break;
            }
        }

        if (isCustom > 0) {
            List<Long> deptIds = remoteDeptService.selectDeptIdByRoleIds(customRoleId, SecurityConstants.INNER).getData();
            if (CollectionUtils.isNotEmpty(deptIds))
                deptScope.addAll(deptIds);
        }
        scope.setDeptScope(deptScope);
        scope.setUserScope(userScope);
        if (isCustom > 0) {
            scope.setDataScope(DataScopeTypes.CUSTOM.getCode());
            return scope;
        } else if (isDeptAndChild > 0) {
            scope.setDataScope(DataScopeTypes.DEPT_AND_CHILD.getCode());
            return scope;
        } else if (isDept > 0) {
            scope.setDataScope(DataScopeTypes.DEPT.getCode());
            return scope;
        } else if (isSelf > 0) {
            scope.setDataScope(DataScopeTypes.SELF.getCode());
            return scope;
        }
        scope.setDataScope(DataScopeTypes.NONE.getCode());
        return scope;
    }


    public void logout(String loginName) {
        recordLogininfor(loginName, Constants.LOGOUT, "退出成功");
    }

    /**
     * 注册
     */
    public void register(String username, String password) {
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password)) {
            throw new ServiceException("用户/密码必须填写");
        }
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            throw new ServiceException("账户长度必须在2到20个字符之间");
        }
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            throw new ServiceException("密码长度必须在5到20个字符之间");
        }

        // 注册用户信息
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
        sysUser.setNickName(username);
        sysUser.setPassword(SecurityUtils.encryptPassword(password));
        R<?> registerResult = remoteUserService.registerUserInfo(sysUser, SecurityConstants.INNER);

        if (R.FAIL == registerResult.getCode()) {
            throw new ServiceException(registerResult.getMsg());
        }
        recordLogininfor(username, Constants.REGISTER, "注册成功");
    }

    public R<Boolean> register(RegisterBody registerBody) {
        if (registerBody == null) {
            throw new ServiceException("注册信息获取失败");
        }

        String username = registerBody.getUsername();
        String password = registerBody.getPassword();
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password)) {
            throw new ServiceException("用户/密码必须填写");
        }
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            throw new ServiceException("账户长度必须在2到20个字符之间");
        }
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            throw new ServiceException("密码长度必须在6到20个字符之间");
        }

        // 注册用户信息
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
        sysUser.setNickName(username);
        sysUser.setPassword(SecurityUtils.encryptPassword(password));
        sysUser.setPhonenumber(registerBody.getPhone());
        sysUser.setEmail(registerBody.getEmail());

        R<Boolean> registerResult = remoteUserService.registerUserInfo(sysUser, SecurityConstants.INNER);

        if (R.FAIL == registerResult.getCode()) {
            throw new ServiceException(registerResult.getMsg());
        }
        recordLogininfor(username, Constants.REGISTER, "注册成功");

        return registerResult;
    }


    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息内容
     * @return
     */
    public void recordLogininfor(String username, String status, String message) {
        SysLogininfor logininfor = new SysLogininfor();
        logininfor.setUserName(username);
        logininfor.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        logininfor.setMsg(message);
        // 日志状态
        if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
            logininfor.setStatus(Constants.LOGIN_SUCCESS_STATUS);
        } else if (Constants.LOGIN_FAIL.equals(status)) {
            logininfor.setStatus(Constants.LOGIN_FAIL_STATUS);
        }
        remoteLogService.saveLogininfor(logininfor, SecurityConstants.INNER);
    }
}