package com.wyg.teach.mapper;

import java.util.List;
import com.wyg.teach.api.domain.TeachCollegeStage;

/**
 * 学期阶段Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-07-21
 */
public interface TeachCollegeStageMapper 
{
    /**
     * 查询学期阶段
     * 
     * @param id 学期阶段主键
     * @return 学期阶段
     */
    public TeachCollegeStage selectTeachCollegeStageById(Long id);

    /**
     * 查询学期阶段列表
     * 
     * @param teachCollegeStage 学期阶段
     * @return 学期阶段集合
     */
    public List<TeachCollegeStage> selectTeachCollegeStageList(TeachCollegeStage teachCollegeStage);

    /**
     * 新增学期阶段
     * 
     * @param teachCollegeStage 学期阶段
     * @return 结果
     */
    public int insertTeachCollegeStage(TeachCollegeStage teachCollegeStage);

    /**
     * 修改学期阶段
     * 
     * @param teachCollegeStage 学期阶段
     * @return 结果
     */
    public int updateTeachCollegeStage(TeachCollegeStage teachCollegeStage);

    /**
     * 删除学期阶段
     * 
     * @param id 学期阶段主键
     * @return 结果
     */
    public int deleteTeachCollegeStageById(Long id);

    /**
     * 批量删除学期阶段
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachCollegeStageByIds(Long[] ids);
}
