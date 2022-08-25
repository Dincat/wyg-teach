package com.wyg.teach.mapper;

import java.util.List;
import com.wyg.teach.api.domain.TeachCourse;

/**
 * 课程信息Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-07-21
 */
public interface TeachCourseMapper 
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
     * 删除课程信息
     * 
     * @param id 课程信息主键
     * @return 结果
     */
    public int deleteTeachCourseById(Long id);

    /**
     * 批量删除课程信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachCourseByIds(Long[] ids);
}
