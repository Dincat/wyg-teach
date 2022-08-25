package com.wyg.teach.mapper;

import java.util.List;
import com.wyg.teach.api.domain.TeachTeacher;

/**
 * 教职工信息Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-07-21
 */
public interface TeachTeacherMapper 
{
    /**
     * 查询教职工信息
     * 
     * @param id 教职工信息主键
     * @return 教职工信息
     */
    public TeachTeacher selectTeachTeacherById(Long id);

    /**
     * 查询教职工信息列表
     * 
     * @param teachTeacher 教职工信息
     * @return 教职工信息集合
     */
    public List<TeachTeacher> selectTeachTeacherList(TeachTeacher teachTeacher);

    /**
     * 新增教职工信息
     * 
     * @param teachTeacher 教职工信息
     * @return 结果
     */
    public int insertTeachTeacher(TeachTeacher teachTeacher);

    /**
     * 修改教职工信息
     * 
     * @param teachTeacher 教职工信息
     * @return 结果
     */
    public int updateTeachTeacher(TeachTeacher teachTeacher);

    /**
     * 删除教职工信息
     * 
     * @param id 教职工信息主键
     * @return 结果
     */
    public int deleteTeachTeacherById(Long id);

    /**
     * 批量删除教职工信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachTeacherByIds(Long[] ids);
}
