package com.wyg.teach.service.impl;

import java.util.List;

import com.wyg.common.core.constant.SecurityConstants;
import com.wyg.common.core.utils.DateUtils;
import com.wyg.common.datascope.annotation.DataScope;
import com.wyg.system.api.RemoteDeptService;
import com.wyg.system.api.domain.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.teach.mapper.TeachStudentHomeworkMapper;
import com.wyg.teach.api.domain.TeachStudentHomework;
import com.wyg.teach.service.ITeachStudentHomeworkService;

/**
 * 学生作业Service业务层处理
 *
 * @author WorrilessGo
 * @date 2022-08-12
 */
@Service
public class TeachStudentHomeworkServiceImpl implements ITeachStudentHomeworkService {
    @Autowired
    private TeachStudentHomeworkMapper teachStudentHomeworkMapper;

    @Autowired
    private RemoteDeptService remoteDeptService;

    /**
     * 查询学生作业
     *
     * @param id 学生作业主键
     * @return 学生作业
     */
    @Override
    @DataScope(primaryTableAlias = "t", deptFieldAlias = "college_id")
    public TeachStudentHomework selectTeachStudentHomeworkById(Long id) {
        TeachStudentHomework homework = teachStudentHomeworkMapper.selectTeachStudentHomeworkById(id);

        SysDept sysDept = remoteDeptService.findById(homework.getCollegeId(), SecurityConstants.INNER).getData();
        homework.setCollege(sysDept);

        return homework;
    }

    /**
     * 查询学生作业列表
     *
     * @param teachStudentHomework 学生作业
     * @return 学生作业
     */
    @Override
    @DataScope(primaryTableAlias = "t", deptFieldAlias = "college_id")
    public List<TeachStudentHomework> selectTeachStudentHomeworkList(TeachStudentHomework teachStudentHomework) {
        List<TeachStudentHomework> homeworkList = teachStudentHomeworkMapper.selectTeachStudentHomeworkList(teachStudentHomework);

        for (TeachStudentHomework homework : homeworkList) {
            SysDept sysDept = remoteDeptService.findById(homework.getCollegeId(), SecurityConstants.INNER).getData();
            homework.setCollege(sysDept);
        }

        return homeworkList;
    }

    /**
     * 新增学生作业
     *
     * @param teachStudentHomework 学生作业
     * @return 结果
     */
    @Override
    public int insertTeachStudentHomework(TeachStudentHomework teachStudentHomework) {


        teachStudentHomework.setCreateTime(DateUtils.getNowDate());

        return teachStudentHomeworkMapper.insertTeachStudentHomework(teachStudentHomework);
    }

    /**
     * 修改学生作业
     *
     * @param teachStudentHomework 学生作业
     * @return 结果
     */
    @Override
    public int updateTeachStudentHomework(TeachStudentHomework teachStudentHomework) {
        teachStudentHomework.setUpdateTime(DateUtils.getNowDate());
        return teachStudentHomeworkMapper.updateTeachStudentHomework(teachStudentHomework);
    }

    /**
     * 批量删除学生作业
     *
     * @param ids 需要删除的学生作业主键
     * @return 结果
     */
    @Override
    public int deleteTeachStudentHomeworkByIds(Long[] ids) {
        return teachStudentHomeworkMapper.deleteTeachStudentHomeworkByIds(ids);
    }

    /**
     * 删除学生作业信息
     *
     * @param id 学生作业主键
     * @return 结果
     */
    @Override
    public int deleteTeachStudentHomeworkById(Long id) {
        return teachStudentHomeworkMapper.deleteTeachStudentHomeworkById(id);
    }
}
