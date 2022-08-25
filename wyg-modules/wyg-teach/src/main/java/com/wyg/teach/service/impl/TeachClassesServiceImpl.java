package com.wyg.teach.service.impl;

import java.util.List;
import java.util.Map;

import com.wyg.common.core.constant.SecurityConstants;
import com.wyg.common.core.utils.DateUtils;
import com.wyg.common.core.utils.MapUtils;
import com.wyg.common.datascope.annotation.DataScope;
import com.wyg.common.security.utils.SecurityUtils;
import com.wyg.system.api.RemoteDeptService;
import com.wyg.system.api.domain.SysDept;
import com.wyg.teach.api.domain.TeachCollegeStage;
import com.wyg.teach.api.domain.TeachMajor;
import com.wyg.teach.api.domain.TeachStudent;
import com.wyg.teach.mapper.TeachStudentMapper;
import com.wyg.teach.service.ITeachClassStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.teach.mapper.TeachClassesMapper;
import com.wyg.teach.api.domain.TeachClasses;
import com.wyg.teach.service.ITeachClassesService;

/**
 * 班级信息Service业务层处理
 *
 * @author WorrilessGo
 * @date 2022-07-21
 */
@Service
public class TeachClassesServiceImpl implements ITeachClassesService {
    @Autowired
    private TeachClassesMapper teachClassesMapper;

    @Autowired
    ITeachClassStageService teachClassStageService;

    @Autowired
    TeachStudentMapper teachStudentMapper;

    @Autowired
    private RemoteDeptService remoteDeptService;

    /**
     * 查询班级信息
     *
     * @param id 班级信息主键
     * @return 班级信息
     */
    @Override
    public TeachClasses selectTeachClassesById(Long id) {
        TeachClasses classes= teachClassesMapper.selectTeachClassesById(id);
        SysDept sysDept = remoteDeptService.findById(classes.getCollegeId(), SecurityConstants.INNER).getData();
        classes.setCollege(sysDept);

        return classes;
    }

    /**
     * 查询班级信息列表
     *
     * @param teachClasses 班级信息
     * @return 班级信息
     */
    @Override
    @DataScope(primaryTableAlias = "t", deptFieldAlias = "college_id")
    public List<TeachClasses> selectTeachClassesList(TeachClasses teachClasses) {
        List<TeachClasses> classesList= teachClassesMapper.selectTeachClassesList(teachClasses);

        for (TeachClasses classes : classesList) {
            SysDept sysDept = remoteDeptService.findById(classes.getCollegeId(), SecurityConstants.INNER).getData();
            classes.setCollege(sysDept);
        }

        return classesList;

    }

    /**
     * 新增班级信息
     *
     * @param teachClasses 班级信息
     * @return 结果
     */
    @Override
    public int insertTeachClasses(TeachClasses teachClasses) {
        teachClasses.setCreateId(SecurityUtils.getUserId());
        teachClasses.setCreateBy(SecurityUtils.getUsername());
        teachClasses.setCreateTime(DateUtils.getNowDate());
        int result= teachClassesMapper.insertTeachClasses(teachClasses);

        teachClassStageService.addClassStage(teachClasses);

        return result;
    }

    /**
     * 修改班级信息
     *
     * @param teachClasses 班级信息
     * @return 结果
     */
    @Override
    public int updateTeachClasses(TeachClasses teachClasses) {
        teachClasses.setUpdateId(SecurityUtils.getUserId());
        teachClasses.setUpdateBy(SecurityUtils.getUsername());
        teachClasses.setUpdateTime(DateUtils.getNowDate());
        return teachClassesMapper.updateTeachClasses(teachClasses);
    }

    /**
     * 批量删除班级信息
     *
     * @param ids 需要删除的班级信息主键
     * @return 结果
     */
    @Override
    public int deleteTeachClassesByIds(Long[] ids) {
        return teachClassesMapper.deleteTeachClassesByIds(ids);
    }

    /**
     * 删除班级信息信息
     *
     * @param id 班级信息主键
     * @return 结果
     */
    @Override
    public int deleteTeachClassesById(Long id) {
        return teachClassesMapper.deleteTeachClassesById(id);
    }

    @Override
    public boolean updateStudentCounting(Long id) {

        TeachStudent teachStudent=new TeachStudent();
        teachStudent.setClassId(id);
        Map map= teachStudentMapper.peopleCounting(teachStudent);
        if(map==null) return false;

        TeachClasses classes=new TeachClasses();
        classes.setId(id);
        classes.setReadingCount(MapUtils.getInteger(map,"readingCount"));
        classes.setDropOutCount(MapUtils.getInteger(map,"dropOutCount"));
        classes.setAbsenceCount(MapUtils.getInteger(map,"absenceCount"));
        classes.setLevelCount(MapUtils.getInteger(map,"levelCount"));
        classes.setGraduateCount(MapUtils.getInteger(map,"graduateCount"));
        classes.setEmploymentCount(MapUtils.getInteger(map,"employmentCount"));

        return teachClassesMapper.updateTeachClasses(classes)>0;
    }
}
