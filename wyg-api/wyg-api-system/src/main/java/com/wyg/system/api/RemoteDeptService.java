package com.wyg.system.api;

import com.wyg.common.core.constant.SecurityConstants;
import com.wyg.common.core.constant.ServiceNameConstants;
import com.wyg.common.core.domain.R;
import com.wyg.system.api.domain.SysDept;
import com.wyg.system.api.domain.SysUser;
import com.wyg.system.api.factory.RemoteDeptFallbackFactory;
import com.wyg.system.api.factory.RemoteUserFallbackFactory;
import com.wyg.system.api.model.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 部门服务
 *
 * @author wyg
 */
@FeignClient(contextId = "remoteDeptService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteDeptFallbackFactory.class)
public interface RemoteDeptService {

    /**
     * 通过部门ID查询本部门及下级部门ID
     *
     * @param id 用户名
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/dept/selectSelfAndChildrenDeptById/{deptId}")
    public R<List<SysDept>> selectSelfAndChildrenDeptById(@PathVariable("deptId") Long deptId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);


    /**
     * 通过部门ID查询本部门及下级部门ID
     *
     * @param id 用户名
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/dept/selectDeptIdByRoleIds")
    public R<List<Long>> selectDeptIdByRoleIds(@RequestBody Set<Long> roleIds, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);


    @GetMapping("/dept/findById/{id}")
    public R<SysDept> findById(@PathVariable("id") Long id, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

}
