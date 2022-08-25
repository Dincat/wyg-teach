package com.wyg.system.api.factory;


import com.wyg.common.core.domain.R;
import com.wyg.system.api.RemoteDeptService;

import com.wyg.system.api.RemoteUserService;
import com.wyg.system.api.domain.SysDept;
import com.wyg.system.api.model.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 部门服务降级处理
 *
 * @author wyg
 */
@Component
public class RemoteDeptFallbackFactory implements FallbackFactory<RemoteDeptService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteDeptFallbackFactory.class);

    @Override
    public RemoteDeptService create(Throwable throwable) {
        log.error("部门服务调用失败:{}", throwable.getMessage());

        return new RemoteDeptService(){

            @Override
            public R<List<SysDept>> selectSelfAndChildrenDeptById(Long deptId, String source) {
                return R.fail("获取部门列表失败：" + throwable.getMessage());
            }

            @Override
            public R<List<Long>> selectDeptIdByRoleIds(Set<Long> roleIds, String source) {
                return R.fail("获取部门ID列表失败：" + throwable.getMessage());
            }

            @Override
            public R<SysDept> findById(Long id, String source) {
                return R.fail("获取部门信息失败：" + throwable.getMessage());
            }


        };
    }
}
