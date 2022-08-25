package com.wyg.teach.service.impl;

import java.util.List;

import com.wyg.common.core.constant.Constants;
import com.wyg.common.core.constant.SecurityConstants;
import com.wyg.common.core.constant.UserConstants;
import com.wyg.common.core.enums.UserTypes;
import com.wyg.common.core.exception.ServiceException;
import com.wyg.common.core.utils.DateUtils;
import com.wyg.common.datascope.annotation.DataScope;
import com.wyg.common.security.utils.SecurityUtils;
import com.wyg.system.api.RemoteConfigService;
import com.wyg.system.api.RemoteDeptService;
import com.wyg.system.api.RemoteUserService;
import com.wyg.system.api.domain.SysDept;
import com.wyg.system.api.domain.SysUser;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.teach.mapper.TeachTeacherMapper;
import com.wyg.teach.api.domain.TeachTeacher;
import com.wyg.teach.service.ITeachTeacherService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 教职工信息Service业务层处理
 *
 * @author WorrilessGo
 * @date 2022-07-21
 */
@Service
public class TeachTeacherServiceImpl implements ITeachTeacherService {
    @Autowired
    private TeachTeacherMapper teachTeacherMapper;

    @Autowired
    RemoteConfigService remoteConfigService;

    @Autowired
    RemoteUserService remoteUserService;

    @Autowired
    private RemoteDeptService remoteDeptService;

    /**
     * 查询教职工信息
     *
     * @param id 教职工信息主键
     * @return 教职工信息
     */
    @Override
    @DataScope(primaryTableAlias = "t", deptFieldAlias = "college_id")
    public TeachTeacher selectTeachTeacherById(Long id) {
        TeachTeacher teacher= teachTeacherMapper.selectTeachTeacherById(id);

        SysDept sysDept = remoteDeptService.findById(teacher.getCollegeId(), SecurityConstants.INNER).getData();
        teacher.setCollege(sysDept);

        return teacher;
    }

    /**
     * 查询教职工信息列表
     *
     * @param teachTeacher 教职工信息
     * @return 教职工信息
     */
    @Override
    @DataScope(primaryTableAlias = "t", deptFieldAlias = "college_id")
    public List<TeachTeacher> selectTeachTeacherList(TeachTeacher teachTeacher) {
        List<TeachTeacher> teacherList= teachTeacherMapper.selectTeachTeacherList(teachTeacher);

        for (TeachTeacher teacher : teacherList) {
            SysDept sysDept = remoteDeptService.findById(teacher.getCollegeId(), SecurityConstants.INNER).getData();
            teacher.setCollege(sysDept);
        }

        return teacherList;
    }

    /**
     * 新增教职工信息
     *
     * @param teachTeacher 教职工信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertTeachTeacher(TeachTeacher teachTeacher) {
        teachTeacher.setCreateId(SecurityUtils.getUserId());
        teachTeacher.setCreateBy(SecurityUtils.getUsername());
        teachTeacher.setCreateTime(DateUtils.getNowDate());

        SysUser sysUser = addTeacherSysUser(teachTeacher);
        if (sysUser != null) {
            teachTeacher.setUserId(sysUser.getUserId());
        }

        int result = teachTeacherMapper.insertTeachTeacher(teachTeacher);

        return result;
    }

    /**
     * 修改教职工信息
     *
     * @param teachTeacher 教职工信息
     * @return 结果
     */
    @Override
    public int updateTeachTeacher(TeachTeacher teachTeacher) {
        TeachTeacher srcTeacher = teachTeacherMapper.selectTeachTeacherById(teachTeacher.getId());

        teachTeacher.setUserId(srcTeacher.getUserId());
        teachTeacher.setUpdateId(SecurityUtils.getUserId());
        teachTeacher.setUpdateBy(SecurityUtils.getUsername());
        teachTeacher.setUpdateTime(DateUtils.getNowDate());

        SysUser sysUser = remoteUserService.findById(teachTeacher.getUserId(), SecurityConstants.INNER).getData();
        boolean hasUser = true;
        if (sysUser == null) {
            sysUser = addTeacherSysUser(teachTeacher);
            teachTeacher.setUserId(sysUser.getUserId());
            hasUser = false;
        }

        int result = teachTeacherMapper.updateTeachTeacher(teachTeacher);

        if (result > 0 && hasUser) {
            sysUser = new SysUser();
            updateTeacherSysUser(teachTeacher, sysUser);
        }

        return result;
    }

    private SysUser addTeacherSysUser(TeachTeacher teachTeacher) {

        SysUser existUser = new SysUser();
        existUser.setIdNumber(teachTeacher.getIdNumber());
        if (remoteUserService.checkIdNumberExist(existUser, SecurityConstants.INNER).getData()) {
            throw new ServiceException("当前身份证号码已被使用！");
        }

        existUser = new SysUser();
        existUser.setEmail(teachTeacher.getPhone());
        if (remoteUserService.checkPhoneExist(existUser, SecurityConstants.INNER).getData()) {
            throw new ServiceException("当前电话号码已被使用！");
        }

        existUser = new SysUser();
        existUser.setEmail(teachTeacher.getEmail());
        if (remoteUserService.checkEmailExist(existUser, SecurityConstants.INNER).getData()) {
            throw new ServiceException("当前邮箱地址已被使用！");
        }

        SysUser sysUser = new SysUser();
        sysUser.setUserName(teachTeacher.getIdNumber());
        sysUser.setNickName(teachTeacher.getTeacherName());
        sysUser.setUserType(UserTypes.Teacher.getCode());
        sysUser.setEmail(teachTeacher.getEmail());
        sysUser.setIdNumber(teachTeacher.getIdNumber());
        sysUser.setSex(teachTeacher.getSex());
        sysUser.setAvatar(teachTeacher.getAvatar());
        String password = remoteConfigService.getConfigValue("sys.user.initPassword").getData();
        sysUser.setPassword(SecurityUtils.encryptPassword(password));
        sysUser.setStatus(UserConstants.COMMON_NORMAL);
        sysUser.setCreateBy(SecurityUtils.getUsername());
        sysUser.setCreateTime(DateUtils.getNowDate());
        sysUser.setLoginPower(Constants.COMMON_YES);
        sysUser.setRoleIds(new Long[]{new Long(5)});
        sysUser = remoteUserService.addUser(sysUser, SecurityConstants.INNER).getData();
        if (sysUser == null || sysUser.getUserId() == null || sysUser.getUserId().longValue() < 1) {
            throw new ServiceException("添加教职工用户失败");
        }

        return sysUser;
    }

    private boolean updateTeacherSysUser(TeachTeacher teachTeacher, SysUser sysUser) {

        SysUser existUser = new SysUser();
        existUser.setIdNumber(teachTeacher.getIdNumber());
        existUser.setUserId(teachTeacher.getUserId());
        if (remoteUserService.checkIdNumberExist(existUser, SecurityConstants.INNER).getData()) {
            throw new ServiceException("当前身份证号码已被使用！");
        }

        existUser = new SysUser();
        existUser.setEmail(teachTeacher.getPhone());
        existUser.setUserId(teachTeacher.getUserId());
        if (remoteUserService.checkPhoneExist(existUser, SecurityConstants.INNER).getData()) {
            throw new ServiceException("当前电话号码已被使用！");
        }

        existUser = new SysUser();
        existUser.setEmail(teachTeacher.getEmail());
        existUser.setUserId(teachTeacher.getUserId());
        if (remoteUserService.checkEmailExist(existUser, SecurityConstants.INNER).getData()) {
            throw new ServiceException("当前邮箱地址已被使用！");
        }

        sysUser.setUserId(teachTeacher.getUserId());
        sysUser.setUserName(teachTeacher.getIdNumber());
        sysUser.setNickName(teachTeacher.getTeacherName());
        sysUser.setUserType(UserTypes.Teacher.getCode());
        sysUser.setEmail(teachTeacher.getEmail());
        sysUser.setIdNumber(teachTeacher.getIdNumber());
        sysUser.setSex(teachTeacher.getSex());
        sysUser.setAvatar(teachTeacher.getAvatar());

        sysUser.setStatus(UserConstants.COMMON_NORMAL);
        sysUser.setCreateBy(SecurityUtils.getUsername());
        sysUser.setCreateTime(DateUtils.getNowDate());
        sysUser.setLoginPower(Constants.COMMON_YES);

        remoteUserService.updateUser(sysUser, SecurityConstants.INNER).getData();

        return true;
    }

    /**
     * 批量删除教职工信息
     *
     * @param ids 需要删除的教职工信息主键
     * @return 结果
     */
    @Override
    public int deleteTeachTeacherByIds(Long[] ids) {
        return teachTeacherMapper.deleteTeachTeacherByIds(ids);
    }

    /**
     * 删除教职工信息信息
     *
     * @param id 教职工信息主键
     * @return 结果
     */
    @Override
    public int deleteTeachTeacherById(Long id) {
        return teachTeacherMapper.deleteTeachTeacherById(id);
    }

    @Override
    public TeachTeacher getTeacherByIdNumber(String idNumber) {
        TeachTeacher query=new TeachTeacher();
        query.setIdNumber(idNumber);
        List<TeachTeacher> list=teachTeacherMapper.selectTeachTeacherList(query);

        if(CollectionUtils.isEmpty(list)) return null;

        TeachTeacher teacher=list.get(0);
        SysDept sysDept = remoteDeptService.findById(teacher.getCollegeId(), SecurityConstants.INNER).getData();
        teacher.setCollege(sysDept);

        return teacher;
    }
}
