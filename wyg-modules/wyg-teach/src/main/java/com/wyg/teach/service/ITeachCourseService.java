package com.wyg.teach.service;

import java.util.List;
import com.wyg.teach.api.domain.TeachCourse;

/**
 * 课程信息Service接口
 * 
 * @author WorrilessGo
 * @date 2022-07-21
 */
public interface ITeachCourseService 
{
    /**
     * 查询课程信息
     * 
     * @param id 课程信息主键
     * @return 课程信息
     */
    public TeachCourse selectTeachCourseById(Long id);

    /**
     * 查询课程信息列表
     * 
     * @param teachCourse 课程信息
     * @return 课程信息集合
     */
    public List<TeachCourse> selectTeachCourseList(TeachCourse teachCourse);

    /**
     * 新增课程信息
     * 
     * @param teachCourse 课程信息
     * @return 结果
     */
    public int insertTeachCourse(TeachCourse teachCourse);

    /**
     * 修改课程信息
     * 
     * @param teachCourse 课程信息
     * @return 结果
     */
    public int updateTeachCourse(TeachCourse teachCourse);

    /**
     * 批量删除课程信息
     * 
     * @param ids 需要删除的课程信息主键集合
     * @return 结果
     */
    public int deleteTeachCourseByIds(Long[] ids);

    /**
     * 删除课程信息信息
     * 
     * @param id 课程信息主键
     * @return 结果
     */
    public int deleteTeachCourseById(Long id);
}
