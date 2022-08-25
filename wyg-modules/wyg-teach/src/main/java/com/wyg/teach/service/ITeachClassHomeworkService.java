package com.wyg.teach.service;

import java.util.List;
import com.wyg.teach.api.domain.TeachClassHomework;

/**
 * 班级作业Service接口
 * 
 * @author WorrilessGo
 * @date 2022-08-12
 */
public interface ITeachClassHomeworkService 
{
    /**
     * 查询班级作业
     * 
     * @param id 班级作业主键
     * @return 班级作业
     */
    public TeachClassHomework selectTeachClassHomeworkById(Long id);

    /**
     * 查询班级作业列表
     * 
     * @param teachClassHomework 班级作业
     * @return 班级作业集合
     */
    public List<TeachClassHomework> selectTeachClassHomeworkList(TeachClassHomework teachClassHomework);

    /**
     * 新增班级作业
     * 
     * @param teachClassHomework 班级作业
     * @return 结果
     */
    public int insertTeachClassHomework(TeachClassHomework teachClassHomework);

    /**
     * 修改班级作业
     * 
     * @param teachClassHomework 班级作业
     * @return 结果
     */
    public int updateTeachClassHomework(TeachClassHomework teachClassHomework);

    /**
     * 批量删除班级作业
     * 
     * @param ids 需要删除的班级作业主键集合
     * @return 结果
     */
    public int deleteTeachClassHomeworkByIds(Long[] ids);

    /**
     * 删除班级作业信息
     * 
     * @param id 班级作业主键
     * @return 结果
     */
    public int deleteTeachClassHomeworkById(Long id);

    /**
     * 布置作业
     * @param id 班级作业
     * @return
     */
    public int publishHomework(Long id);
}
