package com.wyg.system.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.wyg.common.core.constant.UserConstants;
import com.wyg.common.core.domain.R;
import com.wyg.common.core.utils.StringUtils;
import com.wyg.common.core.utils.poi.ExcelUtil;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.web.page.TableDataInfo;
import com.wyg.common.log.annotation.Log;
import com.wyg.common.log.enums.BusinessType;
import com.wyg.common.security.annotation.InnerAuth;
import com.wyg.common.security.annotation.RequiresPermissions;
import com.wyg.common.security.utils.SecurityUtils;
import com.wyg.system.api.domain.SysRole;
import com.wyg.system.api.domain.SysUser;
import com.wyg.system.api.model.LoginUser;
import com.wyg.system.service.ISysConfigService;
import com.wyg.system.service.ISysPermissionService;
import com.wyg.system.service.ISysPostService;
import com.wyg.system.service.ISysRoleService;
import com.wyg.system.service.ISysUserService;

/**
 * 用户信息
 * 
 * @author wyg
 */
@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private ISysPermissionService permissionService;

    @Autowired
    private ISysConfigService configService;

    /**
     * 获取用户列表
     */
    @RequiresPermissions("system:user:list")
    @GetMapping("/list")
    public TableDataInfo list(SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysUser user)
    {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        util.exportExcel(response, list, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:user:import")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        String operName = SecurityUtils.getUsername();
        String message = userService.importUser(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        util.importTemplateExcel(response, "用户数据");
    }

    /**
     * 获取当前用户信息
     */
    @InnerAuth
    @GetMapping("/info/{username}")
    public R<LoginUser> info(@PathVariable("username") String username)
    {
        SysUser sysUser = userService.selectUserByUserName(username);
        if (StringUtils.isNull(sysUser))
        {
            return R.fail("用户名或密码错误");
        }
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(sysUser.getUserId());
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(sysUser.getUserId());
        LoginUser sysUserVo = new LoginUser();
        sysUserVo.setSysUser(sysUser);
        sysUserVo.setRoles(roles);
        sysUserVo.setPermissions(permissions);
        return R.ok(sysUserVo);
    }

    /**
     * 注册用户信息
     */
    @InnerAuth
    @PostMapping("/register")
    public R<Boolean> register(@RequestBody SysUser sysUser)
    {
        String username = sysUser.getUserName();
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return R.fail("当前系统没有开启注册功能！");
        }
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(username)))
        {
            return R.fail("保存用户'" + username + "'失败，注册账号已存在");
        }
        return R.ok(userService.registerUser(sysUser));
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        Long userId = SecurityUtils.getUserId();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(userId);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(userId);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", userService.selectUserById(userId));
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 根据用户编号获取详细信息
     */
    @RequiresPermissions("system:user:query")
    @GetMapping(value = { "/", "/{userId}" })
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId)
    {
        userService.checkUserDataScope(userId);
        AjaxResult ajax = AjaxResult.success();
        List<SysRole> roles = roleService.selectRoleAll();
        ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        ajax.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(userId))
        {
            SysUser sysUser = userService.selectUserById(userId);
            ajax.put(AjaxResult.DATA_TAG, sysUser);
            ajax.put("postIds", postService.selectPostListByUserId(userId));
            ajax.put("roleIds", sysUser.getRoles().stream().map(SysRole::getRoleId).collect(Collectors.toList()));
        }
        return ajax;
    }

    /**
     * 新增用户
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysUser user)
    {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName())))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setCreateBy(SecurityUtils.getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getUserId());
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @RequiresPermissions("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        if (ArrayUtils.contains(userIds, SecurityUtils.getUserId()))
        {
            return AjaxResult.error("当前用户不能删除");
        }
        return toAjax(userService.deleteUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getUserId());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getUserId());
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUserStatus(user));
    }

    /**
     * 根据用户编号获取授权角色
     */
    @RequiresPermissions("system:user:query")
    @GetMapping("/authRole/{userId}")
    public AjaxResult authRole(@PathVariable("userId") Long userId)
    {
        AjaxResult ajax = AjaxResult.success();
        SysUser user = userService.selectUserById(userId);
        List<SysRole> roles = roleService.selectRolesByUserId(userId);
        ajax.put("user", user);
        ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        return ajax;
    }

    /**
     * 用户授权角色
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    @PutMapping("/authRole")
    public AjaxResult insertAuthRole(Long userId, Long[] roleIds)
    {
        userService.checkUserDataScope(userId);
        userService.insertUserAuth(userId, roleIds);
        return success();
    }

    @InnerAuth
    @Log(title = "添加用户", businessType = BusinessType.INSERT)
    @PostMapping("/addUser")
    public R<SysUser> addUser(@RequestBody SysUser sysUser) {
        return R.ok(userService.addUser(sysUser));
    }

    @InnerAuth
    @Log(title = "添加用户", businessType = BusinessType.INSERT)
    @PostMapping("/updateUser")
    public R<Integer> updateUser(@RequestBody SysUser sysUser) {
        return R.ok(userService.updateUser(sysUser));
    }


    @InnerAuth
    @GetMapping("/selectByWxOpenId/{wxOpenId}")
    public R<LoginUser> selectByWxOpenId(@PathVariable("wxOpenId") String wxOpenId) {
        SysUser sysUser = userService.selectByWxOpenId(wxOpenId);
        if (sysUser == null) {
            return R.fail("非法商户");
        }

        // 角色集合
        Set<String> roles = permissionService.getRolePermission(sysUser.getUserId());
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(sysUser.getUserId());
        LoginUser sysUserVo = new LoginUser();
        sysUserVo.setSysUser(sysUser);
        sysUserVo.setRoles(roles);
        sysUserVo.setPermissions(permissions);
        return R.ok(sysUserVo);
    }


    /**
     * 获取用户列表
     */
    @GetMapping(value={"/isExist/{username}","/isExist/{username}/{userId}"})
    public AjaxResult isExist(@PathVariable("username") String username,@PathVariable(value = "userId",required = false) Long userId) {
        SysUser sysUser=new SysUser();
        sysUser.setUserName(username);
        sysUser.setUserId(userId);
        SysUser existUser = userService.isUserNameExist(sysUser);
        AjaxResult result = AjaxResult.success();

        if (existUser != null && existUser.getUserId() != null && existUser.getUserId() > 0) {
            result.put("isExist", true);
        }

        return result;
    }


    @InnerAuth
    @PostMapping(value={"/checkUserNameExist"})
    public R<Boolean> checkUserNameExist(@RequestBody SysUser sysUser) {

        SysUser existUser = userService.isUserNameExist(sysUser);

        if (existUser != null && existUser.getUserId() != null && existUser.getUserId() > 0) {
            return  R.ok(true);
        }

        return  R.ok(false);
    }


    /**
     * 获取用户列表
     */
    @GetMapping(value={"/isPhoneExist/{phone}","/isPhoneExist/{phone}/{userId}"})
    public AjaxResult isPhoneExist(@PathVariable("phone") String phone,@PathVariable(value = "userId",required = false) Long userId) {

        SysUser sysUser=new SysUser();
        sysUser.setPhonenumber(phone);
        sysUser.setUserId(userId);

        SysUser existUser = userService.selectUserByPhone(sysUser);
        AjaxResult result = AjaxResult.success();

        if (existUser != null && existUser.getUserId() != null && existUser.getUserId() > 0) {
            result.put("isExist", true);
        }

        return result;
    }

    @InnerAuth
    @GetMapping("/findById/{id}")
    public R<SysUser> findById(@PathVariable("id") Long id) {
        SysUser sysUser = userService.selectUserById(id);
        if (sysUser == null || sysUser.getUserId() < 1) {
            return R.fail("用户不存在");
        }

        return R.ok(sysUser);
    }

    @InnerAuth
    @PostMapping(value={"/checkPhoneExist"})
    public R<Boolean> checkPhoneExist(@RequestBody SysUser sysUser) {
        SysUser existUser = userService.selectUserByPhone(sysUser);

        if (existUser != null && existUser.getUserId() != null && existUser.getUserId() > 0) {
            return  R.ok(true);
        }

        return  R.ok(false);
    }

    /**
     * 获取用户列表
     */
    @GetMapping(value={"/isEmailExist/{email}","/isEmailExist/{email}/{userId}"})
    public AjaxResult isEmailExist(@PathVariable("email") String email,@PathVariable(value = "userId",required = false) Long userId) {
        SysUser sysUser=new SysUser();
        sysUser.setEmail(email);
        sysUser.setUserId(userId);
        SysUser existUser = userService.selectUserByEmail(sysUser);
        AjaxResult result = AjaxResult.success();

        if (existUser != null && existUser.getUserId() != null && existUser.getUserId() > 0) {
            result.put("isExist", true);
        }

        return result;
    }

    /**
     * 获取用户列表
     */
    @InnerAuth
    @PostMapping(value={"/checkEmailExist"})
    public R<Boolean> checkEmailExist(@RequestBody SysUser sysUser) {

        SysUser existUser = userService.selectUserByEmail(sysUser);

        if (existUser != null && existUser.getUserId() != null && existUser.getUserId() > 0) {
            return  R.ok(true);
        }

        return R.ok(false);
    }

    /**
     * 获取用户列表
     */
    @GetMapping(value={"/isIdNumberExist/{idNumber}","/isIdNumberExist/{idNumber}/{userId}"})
    public AjaxResult isIdNumberExist(@PathVariable("idNumber") String idNumber,@PathVariable(value = "userId",required = false) Long userId) {
        SysUser sysUser=new SysUser();
        sysUser.setIdNumber(idNumber);
        sysUser.setUserId(userId);

        SysUser existUser = userService.selectUserByIdNumber(sysUser);
        AjaxResult result = AjaxResult.success();

        if (existUser != null && existUser.getUserId() != null && existUser.getUserId() > 0) {
            result.put("isExist", true);
        }

        return result;
    }

    /**
     * 获取用户列表
     */
    @PostMapping(value={"/checkIdNumberExist"})
    public R<Boolean> checkIdNumberExist(@RequestBody SysUser sysUser) {
        SysUser existUser = userService.selectUserByIdNumber(sysUser);

        if (existUser != null && existUser.getUserId() != null && existUser.getUserId() > 0) {
            return  R.ok(true);
        }

        return R.ok(false);
    }

}
