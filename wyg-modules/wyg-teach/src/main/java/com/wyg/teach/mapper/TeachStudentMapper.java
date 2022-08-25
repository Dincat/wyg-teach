package com.wyg.teach.mapper;

import java.util.List;
import java.util.Map;

import com.wyg.teach.api.domain.TeachStudent;

/**
 * 学生信息Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-07-28
 */
public interface TeachStudentMapper 
{
    /**
     * 查询学生信息
     * 
     * @param id 学生信息主键
     * @return 学生信息
     */
    public TeachStudent selectTeachStudentById(Long id);

    /**
     * 查询学生信息列表
     * 
     * @param teachStudent 学生信息
     * @return 学生信息集合
     */
    public List<TeachStudent> selectTeachStudentList(TeachStudent teachStudent);

    /**
     * 新增学生信息
     * 
     * @param teachStudent 学生信息
     * @return 结果
     */
    public int insertTeachStudent(TeachStudent teachStudent);

    /**
     * 修改学生信息
     * 
     * @param teachStudent 学生信息
     * @return 结果
     */
    public int updateTeachStudent(TeachStudent teachStudent);

    /**
     * 删除学生信息
     * 
     * @param id 学生信息主键
     * @return 结果
     */
    public int deleteTeachStudentById(Long id);

    /**
     * 批量删除学生信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachStudentByIds(Long[] ids);

    public Map peopleCounting(TeachStudent teachStudent);

}
