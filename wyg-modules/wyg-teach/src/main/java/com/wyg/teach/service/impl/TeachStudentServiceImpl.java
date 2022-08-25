package com.wyg.teach.service.impl;

import java.util.List;
import java.util.Map;

import com.wyg.common.core.constant.Constants;
import com.wyg.common.core.constant.SecurityConstants;
import com.wyg.common.core.constant.UserConstants;
import com.wyg.common.core.enums.UserTypes;
import com.wyg.common.core.exception.ServiceException;
import com.wyg.common.datascope.annotation.DataScope;
import com.wyg.common.security.utils.SecurityUtils;
import com.wyg.common.core.utils.DateUtils;
import com.wyg.system.api.RemoteConfigService;
import com.wyg.system.api.RemoteDeptService;
import com.wyg.system.api.RemoteUserService;
import com.wyg.system.api.domain.SysDept;
import com.wyg.system.api.domain.SysUser;
import com.wyg.teach.api.domain.TeachCollegeStage;
import com.wyg.teach.api.domain.TeachTeacher;
import com.wyg.teach.service.ITeachClassesService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.teach.mapper.TeachStudentMapper;
import com.wyg.teach.api.domain.TeachStudent;
import com.wyg.teach.service.ITeachStudentService;

/**
 * 学生信息Service业务层处理
 *
 * @author WorrilessGo
 * @date 2022-07-28
 */
@Service
public class TeachStudentServiceImpl implements ITeachStudentService {
    @Autowired
    private TeachStudentMapper teachStudentMapper;

    @Autowired
    RemoteConfigService remoteConfigService;

    @Autowired
    RemoteUserService remoteUserService;

    @Autowired
    ITeachClassesService teachClassesService;


    @Autowired
    private RemoteDeptService remoteDeptService;

    /**
     * 查询学生信息
     *
     * @param id 学生信息主键
     * @return 学生信息
     */
    @Override
    @DataScope(primaryTableAlias = "s", deptFieldAlias = "college_id")
    public TeachStudent selectTeachStudentById(Long id) {

        TeachStudent student= teachStudentMapper.selectTeachStudentById(id);
        SysDept sysDept = remoteDeptService.findById(student.getCollegeId(), SecurityConstants.INNER).getData();
        student.setCollege(sysDept);

        return student;
    }

    /**
     * 查询学生信息列表
     *
     * @param teachStudent 学生信息
     * @return 学生信息
     */
    @Override
    @DataScope(primaryTableAlias = "s", deptFieldAlias = "college_id")
    public List<TeachStudent> selectTeachStudentList(TeachStudent teachStudent) {
        List<TeachStudent> studentList= teachStudentMapper.selectTeachStudentList(teachStudent);

        for (TeachStudent student : studentList) {
            SysDept sysDept = remoteDeptService.findById(student.getCollegeId(), SecurityConstants.INNER).getData();
            student.setCollege(sysDept);
        }

        return studentList;
    }

    /**
     * 新增学生信息
     *
     * @param teachStudent 学生信息
     * @return 结果
     */
    @Override
    public int insertTeachStudent(TeachStudent teachStudent) {
        teachStudent.setCreateId(SecurityUtils.getUserId());
        teachStudent.setCreateBy(SecurityUtils.getUsername());
        teachStudent.setCreateTime(DateUtils.getNowDate());

        SysUser sysUser = addStudentSysUser(teachStudent);
        if (sysUser != null) {
            teachStudent.setUserId(sysUser.getUserId());
        }

        int result = teachStudentMapper.insertTeachStudent(teachStudent);

        if (result > 0 && teachStudent.getClassId() != null)
            teachClassesService.updateStudentCounting(teachStudent.getClassId());

        return result;
    }

    /**
     * 修改学生信息
     *
     * @param teachStudent 学生信息
     * @return 结果
     */
    @Override
    public int updateTeachStudent(TeachStudent teachStudent) {
        teachStudent.setCreateId(SecurityUtils.getUserId());
        teachStudent.setCreateBy(SecurityUtils.getUsername());
        teachStudent.setUpdateTime(DateUtils.getNowDate());
        int result = teachStudentMapper.updateTeachStudent(teachStudent);
        if (result < 1) return result;

        SysUser sysUser = remoteUserService.findById(teachStudent.getUserId(), SecurityConstants.INNER).getData();
        boolean hasUser = true;
        if (sysUser == null) {
            sysUser = addStudentSysUser(teachStudent);
            teachStudent.setUserId(sysUser.getUserId());
            hasUser = false;
        }

        result = teachStudentMapper.updateTeachStudent(teachStudent);

        if (result > 0 && hasUser) {
            sysUser = new SysUser();
            updateStudentSysUser(teachStudent, sysUser);
        }

        if (result > 0 && teachStudent.getClassId() != null)
            teachClassesService.updateStudentCounting(teachStudent.getClassId());

        return result;
    }

    private SysUser addStudentSysUser(TeachStudent teachStudent) {

        SysUser existUser = new SysUser();
        existUser.setIdNumber(teachStudent.getIdNumber());
        if (remoteUserService.checkIdNumberExist(existUser, SecurityConstants.INNER).getData()) {
            throw new ServiceException("当前身份证号码已被使用！");
        }

        existUser = new SysUser();
        existUser.setEmail(teachStudent.getPhone());
        if (remoteUserService.checkPhoneExist(existUser, SecurityConstants.INNER).getData()) {
            throw new ServiceException("当前电话号码已被使用！");
        }

        existUser = new SysUser();
        existUser.setEmail(teachStudent.getEmail());
        if (remoteUserService.checkEmailExist(existUser, SecurityConstants.INNER).getData()) {
            throw new ServiceException("当前邮箱地址已被使用！");
        }

        SysUser sysUser = new SysUser();
        sysUser.setUserName(teachStudent.getIdNumber());
        sysUser.setNickName(teachStudent.getStuName());
        sysUser.setUserType(UserTypes.Student.getCode());
        sysUser.setEmail(teachStudent.getEmail());
        sysUser.setIdNumber(teachStudent.getIdNumber());
        sysUser.setSex(teachStudent.getSex());
        sysUser.setAvatar(teachStudent.getAvatar());
        String password = remoteConfigService.getConfigValue("sys.user.initPassword").getData();
        sysUser.setPassword(SecurityUtils.encryptPassword(password));
        sysUser.setStatus(UserConstants.COMMON_NORMAL);
        sysUser.setCreateBy(SecurityUtils.getUsername());
        sysUser.setCreateTime(DateUtils.getNowDate());
        sysUser.setLoginPower(Constants.COMMON_YES);
        sysUser.setRoleIds(new Long[]{new Long(7)});
        sysUser = remoteUserService.addUser(sysUser, SecurityConstants.INNER).getData();
        if (sysUser == null || sysUser.getUserId() == null || sysUser.getUserId().longValue() < 1) {
            throw new ServiceException("添加教职工用户失败");
        }

        return sysUser;
    }

    private boolean updateStudentSysUser(TeachStudent student, SysUser sysUser) {

        SysUser existUser = new SysUser();
        existUser.setIdNumber(student.getIdNumber());
        existUser.setUserId(student.getUserId());
        if (remoteUserService.checkIdNumberExist(existUser, SecurityConstants.INNER).getData()) {
            throw new ServiceException("当前身份证号码已被使用！");
        }

        existUser = new SysUser();
        existUser.setEmail(student.getPhone());
        existUser.setUserId(student.getUserId());
        if (remoteUserService.checkPhoneExist(existUser, SecurityConstants.INNER).getData()) {
            throw new ServiceException("当前电话号码已被使用！");
        }

        existUser = new SysUser();
        existUser.setEmail(student.getEmail());
        existUser.setUserId(student.getUserId());
        if (remoteUserService.checkEmailExist(existUser, SecurityConstants.INNER).getData()) {
            throw new ServiceException("当前邮箱地址已被使用！");
        }

        sysUser.setUserId(student.getUserId());
        sysUser.setUserName(student.getIdNumber());
        sysUser.setNickName(student.getStuName());
        sysUser.setUserType(UserTypes.Student.getCode());
        sysUser.setEmail(student.getEmail());
        sysUser.setIdNumber(student.getIdNumber());
        sysUser.setSex(student.getSex());
        sysUser.setAvatar(student.getAvatar());

        sysUser.setStatus(UserConstants.COMMON_NORMAL);
        sysUser.setCreateBy(SecurityUtils.getUsername());
        sysUser.setCreateTime(DateUtils.getNowDate());
        sysUser.setLoginPower(Constants.COMMON_YES);

        remoteUserService.updateUser(sysUser, SecurityConstants.INNER).getData();

        return true;
    }

    /**
     * 批量删除学生信息
     *
     * @param ids 需要删除的学生信息主键
     * @return 结果
     */
    @Override
    public int deleteTeachStudentByIds(Long[] ids) {
        return teachStudentMapper.deleteTeachStudentByIds(ids);
    }

    /**
     * 删除学生信息信息
     *
     * @param id 学生信息主键
     * @return 结果
     */
    @Override
    public int deleteTeachStudentById(Long id) {
        TeachStudent teachStudent = teachStudentMapper.selectTeachStudentById(id);
        int result = teachStudentMapper.deleteTeachStudentById(id);

        if (result > 0 && teachStudent.getClassId() != null)
            teachClassesService.updateStudentCounting(teachStudent.getClassId());

        return result;
    }

    @Override
    public Map peopleCounting(TeachStudent teachStudent) {
        return teachStudentMapper.peopleCounting(teachStudent);
    }

    @Override
    public TeachStudent getStudentByIdNumber(String idNumber) {
        TeachStudent query=new TeachStudent();
        query.setIdNumber(idNumber);
        List<TeachStudent> list=teachStudentMapper.selectTeachStudentList(query);

        if(CollectionUtils.isEmpty(list)) return null;

        TeachStudent student=list.get(0);
        SysDept sysDept = remoteDeptService.findById(student.getCollegeId(), SecurityConstants.INNER).getData();
        student.setCollege(sysDept);
        return student;
    }
}
