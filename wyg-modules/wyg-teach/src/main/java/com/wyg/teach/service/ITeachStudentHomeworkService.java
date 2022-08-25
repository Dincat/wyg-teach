package com.wyg.teach.service;

import java.util.List;
import com.wyg.teach.api.domain.TeachStudentHomework;

/**
 * 学生作业Service接口
 * 
 * @author WorrilessGo
 * @date 2022-08-12
 */
public interface ITeachStudentHomeworkService 
{
    /**
     * 查询学生作业
     * 
     * @param id 学生作业主键
     * @return 学生作业
     */
    public TeachStudentHomework selectTeachStudentHomeworkById(Long id);

    /**
     * 查询学生作业列表
     * 
     * @param teachStudentHomework 学生作业
     * @return 学生作业集合
     */
    public List<TeachStudentHomework> selectTeachStudentHomeworkList(TeachStudentHomework teachStudentHomework);

    /**
     * 新增学生作业
     * 
     * @param teachStudentHomework 学生作业
     * @return 结果
     */
    public int insertTeachStudentHomework(TeachStudentHomework teachStudentHomework);

    /**
     * 修改学生作业
     * 
     * @param teachStudentHomework 学生作业
     * @return 结果
     */
    public int updateTeachStudentHomework(TeachStudentHomework teachStudentHomework);

    /**
     * 批量删除学生作业
     * 
     * @param ids 需要删除的学生作业主键集合
     * @return 结果
     */
    public int deleteTeachStudentHomeworkByIds(Long[] ids);

    /**
     * 删除学生作业信息
     * 
     * @param id 学生作业主键
     * @return 结果
     */
    public int deleteTeachStudentHomeworkById(Long id);
}
