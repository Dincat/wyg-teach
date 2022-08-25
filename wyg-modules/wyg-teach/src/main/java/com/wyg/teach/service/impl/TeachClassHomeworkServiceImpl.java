package com.wyg.teach.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wyg.common.core.constant.CacheConstants;
import com.wyg.common.core.constant.Constants;
import com.wyg.common.core.constant.SecurityConstants;
import com.wyg.common.core.enums.PublishStatus;
import com.wyg.common.core.exception.ServiceException;
import com.wyg.common.datascope.annotation.DataScope;
import com.wyg.common.redis.service.RedisService;
import com.wyg.common.security.utils.SecurityUtils;
import com.wyg.common.core.utils.DateUtils;
import com.wyg.system.api.RemoteDeptService;
import com.wyg.system.api.domain.SysDept;
import com.wyg.teach.api.domain.*;
import com.wyg.teach.mapper.TeachStudentHomeworkMapper;
import com.wyg.teach.mapper.TeachStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.teach.mapper.TeachClassHomeworkMapper;
import com.wyg.teach.service.ITeachClassHomeworkService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 班级作业Service业务层处理
 *
 * @author WorrilessGo
 * @date 2022-08-12
 */
@Service
public class TeachClassHomeworkServiceImpl implements ITeachClassHomeworkService {
    @Autowired
    private TeachClassHomeworkMapper teachClassHomeworkMapper;

    @Autowired
    private TeachStudentMapper teachStudentMapper;

    @Autowired
    private TeachStudentHomeworkMapper studentHomeworkMapper;


    @Autowired
    private RemoteDeptService remoteDeptService;

    @Autowired
    private RedisService redisService;

    /**
     * 查询班级作业
     *
     * @param id 班级作业主键
     * @return 班级作业
     */
    @Override
    @DataScope(primaryTableAlias = "t", deptFieldAlias = "college_id")
    public TeachClassHomework selectTeachClassHomeworkById(Long id) {
        TeachClassHomework homework= teachClassHomeworkMapper.selectTeachClassHomeworkById(id);

        SysDept sysDept = remoteDeptService.findById(homework.getCollegeId(), SecurityConstants.INNER).getData();
        homework.setCollege(sysDept);

        return homework;
    }

    /**
     * 查询班级作业列表
     *
     * @param teachClassHomework 班级作业
     * @return 班级作业
     */
    @Override
    @DataScope(primaryTableAlias = "t", deptFieldAlias = "college_id")
    public List<TeachClassHomework> selectTeachClassHomeworkList(TeachClassHomework teachClassHomework) {
        List<TeachClassHomework> homeworkList= teachClassHomeworkMapper.selectTeachClassHomeworkList(teachClassHomework);

        for (TeachClassHomework homework : homeworkList) {
            SysDept sysDept = remoteDeptService.findById(homework.getCollegeId(), SecurityConstants.INNER).getData();
            homework.setCollege(sysDept);
        }

        return homeworkList;
    }

    /**
     * 新增班级作业
     *
     * @param teachClassHomework 班级作业
     * @return 结果
     */
    @Override
    @Transactional
    public int insertTeachClassHomework(TeachClassHomework teachClassHomework) {

        if(teachClassHomework.getClassId()==null || teachClassHomework.getClassId().longValue()<1){
            throw new ServiceException("班级ID不正确");
        }

        String teachKey=  CacheConstants.TEACH_USER_KEY+SecurityUtils.getToken();
        TeachTeacher teacher=redisService.getCacheObject(teachKey);
        if(teacher==null){
            throw new ServiceException("当前账号不能添加作业");
        }


        TeachStudent stuQuery=new TeachStudent();
        stuQuery.setClassId(teachClassHomework.getClassId());
        List<TeachStudent> studentList= teachStudentMapper.selectTeachStudentList(stuQuery);


        teachClassHomework.setTeacherId(teacher.getId());
        teachClassHomework.setStudentCount(studentList.size());
        teachClassHomework.setCreateId(SecurityUtils.getUserId());
        teachClassHomework.setCreateBy(SecurityUtils.getUsername());
        teachClassHomework.setCreateTime(DateUtils.getNowDate());

        int result= teachClassHomeworkMapper.insertTeachClassHomework(teachClassHomework);

        if(result<1) return result;

        if(teachClassHomework.getPublishStatus().equals(Constants.COMMON_YES)){
           publishHomework(teachClassHomework,studentList);
        }

        return result;

    }

    @Override
    @Transactional
    public int publishHomework(Long id){
        TeachClassHomework teachClassHomework=teachClassHomeworkMapper.selectTeachClassHomeworkById(id);
        if(teachClassHomework==null){
            throw new ServiceException("获取班级作业信息失败");
        }

        TeachStudent stuQuery=new TeachStudent();
        stuQuery.setClassId(teachClassHomework.getClassId());
        List<TeachStudent> studentList= teachStudentMapper.selectTeachStudentList(stuQuery);

        if(teachClassHomework.getPublishStatus().equals(PublishStatus.Published.getCode())) {
            throw new ServiceException("该作业已布置，不能重复布置");
        }

        return publishHomework(teachClassHomework,studentList);

    }

    public int publishHomework(TeachClassHomework teachClassHomework,List<TeachStudent> studentList){

        List<TeachStudentHomework> homeworkList=new ArrayList<>();

        for(TeachStudent student:studentList){
            TeachStudentHomework homework=new TeachStudentHomework();
            homework.setCollegeId(teachClassHomework.getCollegeId());
            homework.setClassId(teachClassHomework.getClassId());
            homework.setStageId(teachClassHomework.getStageId());
            homework.setCourseId(teachClassHomework.getCourseId());
            homework.setTeacherId(SecurityUtils.getUserId());
            homework.setStudentId(student.getId());
            homework.setCreateTime(new Date());
            homeworkList.add(homework);
        }

        int sc=studentHomeworkMapper.insertBatch(homeworkList);

        if(sc<studentList.size()){
            throw new ServiceException("添加班级作业失败");
        }

        if(teachClassHomework.getPublishStatus().equals(PublishStatus.Draft.getCode())) {
            teachClassHomework.setPublishStatus(PublishStatus.Published.getCode());
            teachClassHomework.setPublishTime(new Date());
            teachClassHomeworkMapper.updateTeachClassHomework(teachClassHomework);
        }
        return sc;
    }


    /**
     * 修改班级作业
     *
     * @param teachClassHomework 班级作业
     * @return 结果
     */
    @Override
    public int updateTeachClassHomework(TeachClassHomework teachClassHomework) {
        teachClassHomework.setCreateId(SecurityUtils.getUserId());
        teachClassHomework.setCreateBy(SecurityUtils.getUsername());
        teachClassHomework.setUpdateTime(DateUtils.getNowDate());
        return teachClassHomeworkMapper.updateTeachClassHomework(teachClassHomework);
    }

    /**
     * 批量删除班级作业
     *
     * @param ids 需要删除的班级作业主键
     * @return 结果
     */
    @Override
    public int deleteTeachClassHomeworkByIds(Long[] ids) {
        return teachClassHomeworkMapper.deleteTeachClassHomeworkByIds(ids);
    }

    /**
     * 删除班级作业信息
     *
     * @param id 班级作业主键
     * @return 结果
     */
    @Override
    public int deleteTeachClassHomeworkById(Long id) {
        return teachClassHomeworkMapper.deleteTeachClassHomeworkById(id);
    }
}
