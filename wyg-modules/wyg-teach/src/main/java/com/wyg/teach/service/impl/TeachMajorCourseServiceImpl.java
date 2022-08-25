package com.wyg.teach.service.impl;

import java.util.List;
import com.wyg.common.security.utils.SecurityUtils;
import com.wyg.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.teach.mapper.TeachMajorCourseMapper;
import com.wyg.teach.api.domain.TeachMajorCourse;
import com.wyg.teach.service.ITeachMajorCourseService;

/**
 * 专业课程Service业务层处理
 * 
 * @author WorrilessGo
 * @date 2022-07-29
 */
@Service
public class TeachMajorCourseServiceImpl implements ITeachMajorCourseService 
{
    @Autowired
    private TeachMajorCourseMapper teachMajorCourseMapper;

    /**
     * 查询专业课程
     * 
     * @param id 专业课程主键
     * @return 专业课程
     */
    @Override
    public TeachMajorCourse selectTeachMajorCourseById(Long id)
    {
        return teachMajorCourseMapper.selectTeachMajorCourseById(id);
    }

    /**
     * 查询专业课程列表
     * 
     * @param teachMajorCourse 专业课程
     * @return 专业课程
     */
    @Override
    public List<TeachMajorCourse> selectTeachMajorCourseList(TeachMajorCourse teachMajorCourse)
    {
        return teachMajorCourseMapper.selectTeachMajorCourseList(teachMajorCourse);
    }

    /**
     * 新增专业课程
     * 
     * @param teachMajorCourse 专业课程
     * @return 结果
     */
    @Override
    public int insertTeachMajorCourse(TeachMajorCourse teachMajorCourse)
    {










        teachMajorCourse.setCreateId(SecurityUtils.getUserId());

        teachMajorCourse.setCreateBy(SecurityUtils.getUsername());

        teachMajorCourse.setCreateTime(DateUtils.getNowDate());



        return teachMajorCourseMapper.insertTeachMajorCourse(teachMajorCourse);
    }

    /**
     * 修改专业课程
     * 
     * @param teachMajorCourse 专业课程
     * @return 结果
     */
    @Override
    public int updateTeachMajorCourse(TeachMajorCourse teachMajorCourse)
    {
        teachMajorCourse.setCreateId(SecurityUtils.getUserId());
        teachMajorCourse.setCreateBy(SecurityUtils.getUsername());
        teachMajorCourse.setUpdateTime(DateUtils.getNowDate());
        return teachMajorCourseMapper.updateTeachMajorCourse(teachMajorCourse);
    }

    /**
     * 批量删除专业课程
     * 
     * @param ids 需要删除的专业课程主键
     * @return 结果
     */
    @Override
    public int deleteTeachMajorCourseByIds(Long[] ids)
    {
        return teachMajorCourseMapper.deleteTeachMajorCourseByIds(ids);
    }

    /**
     * 删除专业课程信息
     *
     * @param id 专业课程主键
     * @return 结果
     */
    @Override
    public int deleteTeachMajorCourseById(Long id)
    {
        return teachMajorCourseMapper.deleteTeachMajorCourseById(id);
    }
}
