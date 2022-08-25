package com.wyg.teach.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wyg.common.core.enums.TeachClassStatus;
import com.wyg.common.datascope.annotation.DataScope;
import com.wyg.common.security.utils.SecurityUtils;
import com.wyg.common.core.utils.DateUtils;
import com.wyg.teach.api.domain.TeachClasses;
import com.wyg.teach.api.domain.TeachCollegeStage;
import com.wyg.teach.api.domain.TeachMajor;
import com.wyg.teach.mapper.TeachCollegeStageMapper;
import com.wyg.teach.mapper.TeachMajorMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.teach.mapper.TeachClassStageMapper;
import com.wyg.teach.api.domain.TeachClassStage;
import com.wyg.teach.service.ITeachClassStageService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 班级阶段Service业务层处理
 *
 * @author WorrilessGo
 * @date 2022-07-29
 */
@Service
public class TeachClassStageServiceImpl implements ITeachClassStageService {
    @Autowired
    private TeachClassStageMapper teachClassStageMapper;

    @Autowired
    TeachMajorMapper teachMajorMapper;

    @Autowired
    TeachCollegeStageMapper teachCollegeStageMapper;

    /**
     * 查询班级阶段
     *
     * @param id 班级阶段主键
     * @return 班级阶段
     */
    @Override
    @DataScope(primaryTableAlias = "t", deptFieldAlias = "college_id")
    public TeachClassStage selectTeachClassStageById(Long id) {
        return teachClassStageMapper.selectTeachClassStageById(id);
    }

    /**
     * 查询班级阶段列表
     *
     * @param teachClassStage 班级阶段
     * @return 班级阶段
     */
    @Override
    @DataScope(primaryTableAlias = "t", deptFieldAlias = "college_id")
    public List<TeachClassStage> selectTeachClassStageList(TeachClassStage teachClassStage) {
        return teachClassStageMapper.selectTeachClassStageList(teachClassStage);
    }

    /**
     * 新增班级阶段
     *
     * @param teachClassStage 班级阶段
     * @return 结果
     */
    @Override
    public int insertTeachClassStage(TeachClassStage teachClassStage) {

        teachClassStage.setCreateId(SecurityUtils.getUserId());
        teachClassStage.setCreateBy(SecurityUtils.getUsername());
        teachClassStage.setCreateTime(DateUtils.getNowDate());

        return teachClassStageMapper.insertTeachClassStage(teachClassStage);
    }

    /**
     * 修改班级阶段
     *
     * @param teachClassStage 班级阶段
     * @return 结果
     */
    @Override
    public int updateTeachClassStage(TeachClassStage teachClassStage) {
        teachClassStage.setCreateId(SecurityUtils.getUserId());
        teachClassStage.setCreateBy(SecurityUtils.getUsername());
        teachClassStage.setUpdateTime(DateUtils.getNowDate());
        return teachClassStageMapper.updateTeachClassStage(teachClassStage);
    }

    /**
     * 批量删除班级阶段
     *
     * @param ids 需要删除的班级阶段主键
     * @return 结果
     */
    @Override
    public int deleteTeachClassStageByIds(Long[] ids) {
        return teachClassStageMapper.deleteTeachClassStageByIds(ids);
    }

    /**
     * 删除班级阶段信息
     *
     * @param id 班级阶段主键
     * @return 结果
     */
    @Override
    public int deleteTeachClassStageById(Long id) {
        return teachClassStageMapper.deleteTeachClassStageById(id);
    }

    @Transactional
    public boolean addClassStage(TeachClasses classes){

        if(classes==null || classes.getMajorId()==null || classes.getMajorId().longValue()<1) return false;

        TeachMajor major=teachMajorMapper.selectTeachMajorById(classes.getMajorId());

        TeachCollegeStage stageQuery=new TeachCollegeStage();
        stageQuery.setEduSystem(major.getMajorType());
        stageQuery.setCollegeId(major.getCollegeId());
        List<TeachCollegeStage> stageList=teachCollegeStageMapper.selectTeachCollegeStageList(stageQuery);

        if(CollectionUtils.isEmpty(stageList)){
            return false;
        }

        List<TeachClassStage> classStageList=new ArrayList<>();

        for(TeachCollegeStage collegeStage:stageList){
            TeachClassStage classStage=new TeachClassStage();
            classStage.setClassId(classes.getId());
            classStage.setCollegeId(classes.getCollegeId());
            classStage.setStageId(collegeStage.getId());
            classStage.setStageName(collegeStage.getStageName());
            classStage.setOrderNum(collegeStage.getOrderNum());
            classStage.setStatus(TeachClassStatus.Waiting.getCode());
            classStage.setCreateId(SecurityUtils.getUserId());
            classStage.setCreateBy(SecurityUtils.getUsername());
            classStage.setUpdateTime(DateUtils.getNowDate());
            classStage.setTenantCode(SecurityUtils.getTenantCode());
            classStageList.add(classStage);
        }

        return teachClassStageMapper.insertBatch(classStageList)>0;

    }


}
