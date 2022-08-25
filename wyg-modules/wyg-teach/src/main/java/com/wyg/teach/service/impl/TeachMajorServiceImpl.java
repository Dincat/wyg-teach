package com.wyg.teach.service.impl;

import java.util.List;

import com.wyg.common.core.constant.SecurityConstants;
import com.wyg.common.core.utils.DateUtils;
import com.wyg.common.datascope.annotation.DataScope;
import com.wyg.common.security.utils.SecurityUtils;
import com.wyg.system.api.RemoteDeptService;
import com.wyg.system.api.domain.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.teach.mapper.TeachMajorMapper;
import com.wyg.teach.api.domain.TeachMajor;
import com.wyg.teach.service.ITeachMajorService;

/**
 * 专业信息Service业务层处理
 *
 * @author WorrilessGo
 * @date 2022-07-21
 */
@Service
public class TeachMajorServiceImpl implements ITeachMajorService {
    @Autowired
    private TeachMajorMapper teachMajorMapper;

    @Autowired
    private RemoteDeptService remoteDeptService;

    /**
     * 查询专业信息
     *
     * @param id 专业信息主键
     * @return 专业信息
     */
    @Override
    public TeachMajor selectTeachMajorById(Long id) {
        TeachMajor major = teachMajorMapper.selectTeachMajorById(id);
        SysDept sysDept = remoteDeptService.findById(major.getCollegeId(), SecurityConstants.INNER).getData();
        major.setCollege(sysDept);
        return major;
    }

    /**
     * 查询专业信息列表
     *
     * @param teachMajor 专业信息
     * @return 专业信息
     */
    @Override
    @DataScope(primaryTableAlias = "m", deptFieldAlias = "college_id")
    public List<TeachMajor> selectTeachMajorList(TeachMajor teachMajor) {
        List<TeachMajor> majorList = teachMajorMapper.selectTeachMajorList(teachMajor);

        for (TeachMajor major : majorList) {
            SysDept sysDept = remoteDeptService.findById(major.getCollegeId(), SecurityConstants.INNER).getData();
            major.setCollege(sysDept);
        }

        return majorList;
    }

    /**
     * 新增专业信息
     *
     * @param teachMajor 专业信息
     * @return 结果
     */
    @Override
    public int insertTeachMajor(TeachMajor teachMajor) {
        teachMajor.setCreateId(SecurityUtils.getUserId());
        teachMajor.setCreateBy(SecurityUtils.getUsername());
        teachMajor.setCreateTime(DateUtils.getNowDate());
        return teachMajorMapper.insertTeachMajor(teachMajor);
    }

    /**
     * 修改专业信息
     *
     * @param teachMajor 专业信息
     * @return 结果
     */
    @Override
    public int updateTeachMajor(TeachMajor teachMajor) {
        teachMajor.setUpdateId(SecurityUtils.getUserId());
        teachMajor.setUpdateBy(SecurityUtils.getUsername());
        teachMajor.setUpdateTime(DateUtils.getNowDate());
        return teachMajorMapper.updateTeachMajor(teachMajor);
    }

    /**
     * 批量删除专业信息
     *
     * @param ids 需要删除的专业信息主键
     * @return 结果
     */
    @Override
    public int deleteTeachMajorByIds(Long[] ids) {
        return teachMajorMapper.deleteTeachMajorByIds(ids);
    }

    /**
     * 删除专业信息信息
     *
     * @param id 专业信息主键
     * @return 结果
     */
    @Override
    public int deleteTeachMajorById(Long id) {
        return teachMajorMapper.deleteTeachMajorById(id);
    }
}
