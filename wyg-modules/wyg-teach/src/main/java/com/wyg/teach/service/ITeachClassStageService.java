package com.wyg.teach.service;

import java.util.List;
import com.wyg.teach.api.domain.TeachClassStage;
import com.wyg.teach.api.domain.TeachClasses;

/**
 * 班级阶段Service接口
 * 
 * @author WorrilessGo
 * @date 2022-07-29
 */
public interface ITeachClassStageService 
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
     * 批量删除班级阶段
     * 
     * @param ids 需要删除的班级阶段主键集合
     * @return 结果
     */
    public int deleteTeachClassStageByIds(Long[] ids);

    /**
     * 删除班级阶段信息
     * 
     * @param id 班级阶段主键
     * @return 结果
     */
    public int deleteTeachClassStageById(Long id);

    boolean addClassStage(TeachClasses classes);
}
