package com.wyg.teach.mapper;

import java.util.List;
import com.wyg.teach.api.domain.TeachClassStage;

/**
 * 班级阶段Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-07-29
 */
public interface TeachClassStageMapper 
{
    /**
     * 查询班级阶段
     * 
     * @param id 班级阶段主键
     * @return 班级阶段
     */
    public TeachClassStage selectTeachClassStageById(Long id);

    /**
     * 查询班级阶段列表
     * 
     * @param teachClassStage 班级阶段
     * @return 班级阶段集合
     */
    public List<TeachClassStage> selectTeachClassStageList(TeachClassStage teachClassStage);

    /**
     * 新增班级阶段
     * 
     * @param teachClassStage 班级阶段
     * @return 结果
     */
    public int insertTeachClassStage(TeachClassStage teachClassStage);

    /**
     * 修改班级阶段
     * 
     * @param teachClassStage 班级阶段
     * @return 结果
     */
    public int updateTeachClassStage(TeachClassStage teachClassStage);

    /**
     * 删除班级阶段
     * 
     * @param id 班级阶段主键
     * @return 结果
     */
    public int deleteTeachClassStageById(Long id);

    /**
     * 批量删除班级阶段
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachClassStageByIds(Long[] ids);

    public int insertBatch(List<TeachClassStage> classStageList);
}
