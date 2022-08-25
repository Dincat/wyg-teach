package com.wyg.teach.service;

import java.util.List;
import com.wyg.teach.api.domain.TeachMajorCourse;

/**
 * 专业课程Service接口
 * 
 * @author WorrilessGo
 * @date 2022-07-29
 */
public interface ITeachMajorCourseService 
{
    /**
     * 查询专业课程
     * 
     * @param id 专业课程主键
     * @return 专业课程
     */
    public TeachMajorCourse selectTeachMajorCourseById(Long id);

    /**
     * 查询专业课程列表
     * 
     * @param teachMajorCourse 专业课程
     * @return 专业课程集合
     */
    public List<TeachMajorCourse> selectTeachMajorCourseList(TeachMajorCourse teachMajorCourse);

    /**
     * 新增专业课程
     * 
     * @param teachMajorCourse 专业课程
     * @return 结果
     */
    public int insertTeachMajorCourse(TeachMajorCourse teachMajorCourse);

    /**
     * 修改专业课程
     * 
     * @param teachMajorCourse 专业课程
     * @return 结果
     */
    public int updateTeachMajorCourse(TeachMajorCourse teachMajorCourse);

    /**
     * 批量删除专业课程
     * 
     * @param ids 需要删除的专业课程主键集合
     * @return 结果
     */
    public int deleteTeachMajorCourseByIds(Long[] ids);

    /**
     * 删除专业课程信息
     * 
     * @param id 专业课程主键
     * @return 结果
     */
    public int deleteTeachMajorCourseById(Long id);
}
