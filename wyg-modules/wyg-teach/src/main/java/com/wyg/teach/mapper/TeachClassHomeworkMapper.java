package com.wyg.teach.mapper;

import java.util.List;
import com.wyg.teach.api.domain.TeachClassHomework;

/**
 * 班级作业Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-08-12
 */
public interface TeachClassHomeworkMapper 
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
     * 删除班级作业
     * 
     * @param id 班级作业主键
     * @return 结果
     */
    public int deleteTeachClassHomeworkById(Long id);

    /**
     * 批量删除班级作业
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachClassHomeworkByIds(Long[] ids);
}
