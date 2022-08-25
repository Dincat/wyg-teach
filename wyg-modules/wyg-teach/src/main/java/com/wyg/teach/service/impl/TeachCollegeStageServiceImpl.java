package com.wyg.teach.service.impl;

import java.util.List;

import com.wyg.common.core.constant.SecurityConstants;
import com.wyg.common.core.utils.DateUtils;
import com.wyg.common.datascope.annotation.DataScope;
import com.wyg.common.security.utils.SecurityUtils;
import com.wyg.system.api.RemoteDeptService;
import com.wyg.system.api.domain.SysDept;
import com.wyg.teach.api.domain.TeachMajor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.teach.mapper.TeachCollegeStageMapper;
import com.wyg.teach.api.domain.TeachCollegeStage;
import com.wyg.teach.service.ITeachCollegeStageService;

/**
 * 学期阶段Service业务层处理
 *
 * @author WorrilessGo
 * @date 2022-07-21
 */
@Service
public class TeachCollegeStageServiceImpl implements ITeachCollegeStageService {
    @Autowired
    private TeachCollegeStageMapper teachCollegeStageMapper;


    @Autowired
    private RemoteDeptService remoteDeptService;

    /**
     * 查询学期阶段
     *
     * @param id 学期阶段主键
     * @return 学期阶段
     */
    @Override
    @DataScope(primaryTableAlias = "s", deptFieldAlias = "college_id")
    public TeachCollegeStage selectTeachCollegeStageById(Long id) {
        TeachCollegeStage stage= teachCollegeStageMapper.selectTeachCollegeStageById(id);

        SysDept sysDept = remoteDeptService.findById(stage.getCollegeId(), SecurityConstants.INNER).getData();
        stage.setCollege(sysDept);
        return stage;
    }

    /**
     * 查询学期阶段列表
     *
     * @param teachCollegeStage 学期阶段
     * @return 学期阶段
     */
    @Override
    @DataScope(primaryTableAlias = "s", deptFieldAlias = "college_id")
    public List<TeachCollegeStage> selectTeachCollegeStageList(TeachCollegeStage teachCollegeStage) {
        List<TeachCollegeStage> stageList = teachCollegeStageMapper.selectTeachCollegeStageList(teachCollegeStage);

        for (TeachCollegeStage stage : stageList) {
            SysDept sysDept = remoteDeptService.findById(stage.getCollegeId(), SecurityConstants.INNER).getData();
            stage.setCollege(sysDept);
        }

        return stageList;
    }

    /**
     * 新增学期阶段
     *
     * @param teachCollegeStage 学期阶段
     * @return 结果
     */
    @Override
    public int insertTeachCollegeStage(TeachCollegeStage teachCollegeStage) {
        teachCollegeStage.setCreateId(SecurityUtils.getUserId());
        teachCollegeStage.setCreateBy(SecurityUtils.getUsername());
        teachCollegeStage.setCreateTime(DateUtils.getNowDate());
        return teachCollegeStageMapper.insertTeachCollegeStage(teachCollegeStage);
    }

    /**
     * 修改学期阶段
     *
     * @param teachCollegeStage 学期阶段
     * @return 结果
     */
    @Override
    public int updateTeachCollegeStage(TeachCollegeStage teachCollegeStage) {
        teachCollegeStage.setUpdateId(SecurityUtils.getUserId());
        teachCollegeStage.setUpdateBy(SecurityUtils.getUsername());
        teachCollegeStage.setUpdateTime(DateUtils.getNowDate());
        return teachCollegeStageMapper.updateTeachCollegeStage(teachCollegeStage);
    }

    /**
     * 批量删除学期阶段
     *
     * @param ids 需要删除的学期阶段主键
     * @return 结果
     */
    @Override
    public int deleteTeachCollegeStageByIds(Long[] ids) {
        return teachCollegeStageMapper.deleteTeachCollegeStageByIds(ids);
    }

    /**
     * 删除学期阶段信息
     *
     * @param id 学期阶段主键
     * @return 结果
     */
    @Override
    public int deleteTeachCollegeStageById(Long id) {
        return teachCollegeStageMapper.deleteTeachCollegeStageById(id);
    }
}
