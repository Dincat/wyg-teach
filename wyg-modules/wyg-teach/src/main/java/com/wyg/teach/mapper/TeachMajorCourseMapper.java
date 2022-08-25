package com.wyg.teach.mapper;

import java.util.List;
import com.wyg.teach.api.domain.TeachMajorCourse;

/**
 * 专业课程Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-07-29
 */
public interface TeachMajorCourseMapper 
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
     * 删除专业课程
     * 
     * @param id 专业课程主键
     * @return 结果
     */
    public int deleteTeachMajorCourseById(Long id);

    /**
     * 批量删除专业课程
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachMajorCourseByIds(Long[] ids);
}
