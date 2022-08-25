package com.wyg.teach.service.impl;

import java.util.List;

import com.wyg.common.core.constant.SecurityConstants;
import com.wyg.common.core.utils.DateUtils;
import com.wyg.common.datascope.annotation.DataScope;
import com.wyg.common.security.utils.SecurityUtils;
import com.wyg.system.api.RemoteDeptService;
import com.wyg.system.api.domain.SysDept;
import com.wyg.teach.api.domain.TeachCollegeStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.teach.mapper.TeachCourseMapper;
import com.wyg.teach.api.domain.TeachCourse;
import com.wyg.teach.service.ITeachCourseService;

/**
 * 课程信息Service业务层处理
 * 
 * @author WorrilessGo
 * @date 2022-07-21
 */
@Service
public class TeachCourseServiceImpl implements ITeachCourseService 
{
    @Autowired
    private TeachCourseMapper teachCourseMapper;

    @Autowired
    private RemoteDeptService remoteDeptService;

    /**
     * 查询课程信息
     * 
     * @param id 课程信息主键
     * @return 课程信息
     */
    @Override
    public TeachCourse selectTeachCourseById(Long id)
    {
        TeachCourse course= teachCourseMapper.selectTeachCourseById(id);

        SysDept sysDept = remoteDeptService.findById(course.getCollegeId(), SecurityConstants.INNER).getData();
        course.setCollege(sysDept);

        return course;
    }

    /**
     * 查询课程信息列表
     * 
     * @param teachCourse 课程信息
     * @return 课程信息
     */
    @Override
    @DataScope(primaryTableAlias = "t", deptFieldAlias = "college_id")
    public List<TeachCourse> selectTeachCourseList(TeachCourse teachCourse)
    {
        List<TeachCourse> courseList= teachCourseMapper.selectTeachCourseList(teachCourse);

        for (TeachCourse course : courseList) {
            SysDept sysDept = remoteDeptService.findById(course.getCollegeId(), SecurityConstants.INNER).getData();
            course.setCollege(sysDept);
        }

        return courseList;
    }

    /**
     * 新增课程信息
     * 
     * @param teachCourse 课程信息
     * @return 结果
     */
    @Override
    public int insertTeachCourse(TeachCourse teachCourse)
    {
        teachCourse.setCreateId(SecurityUtils.getUserId());
        teachCourse.setCreateBy(SecurityUtils.getUsername());
        teachCourse.setCreateTime(DateUtils.getNowDate());
        return teachCourseMapper.insertTeachCourse(teachCourse);
    }

    /**
     * 修改课程信息
     * 
     * @param teachCourse 课程信息
     * @return 结果
     */
    @Override
    public int updateTeachCourse(TeachCourse teachCourse)
    {
        teachCourse.setUpdateId(SecurityUtils.getUserId());
        teachCourse.setUpdateBy(SecurityUtils.getUsername());
        teachCourse.setUpdateTime(DateUtils.getNowDate());
        return teachCourseMapper.updateTeachCourse(teachCourse);
    }

    /**
     * 批量删除课程信息
     * 
     * @param ids 需要删除的课程信息主键
     * @return 结果
     */
    @Override
    public int deleteTeachCourseByIds(Long[] ids)
    {
        return teachCourseMapper.deleteTeachCourseByIds(ids);
    }

    /**
     * 删除课程信息信息
     *
     * @param id 课程信息主键
     * @return 结果
     */
    @Override
    public int deleteTeachCourseById(Long id)
    {
        return teachCourseMapper.deleteTeachCourseById(id);
    }
}
