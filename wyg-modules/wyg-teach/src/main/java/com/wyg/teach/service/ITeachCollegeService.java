package com.wyg.teach.service;

import java.util.List;

import com.wyg.system.api.domain.SysDept;
import com.wyg.teach.domain.TeachCollege;
import com.wyg.teach.domain.vo.TreeSelect;

/**
 * 学院信息Service接口
 * 
 * @author WorrilessGo
 * @date 2022-07-21
 */
public interface ITeachCollegeService 
{
    /**
     * 查询学院信息
     * 
     * @param id 学院信息主键
     * @return 学院信息
     */
    public TeachCollege selectTeachCollegeById(Long id);

    /**
     * 查询学院信息列表
     * 
     * @param teachCollege 学院信息
     * @return 学院信息集合
     */
    public List<TeachCollege> selectTeachCollegeList(TeachCollege teachCollege);

    /**
     * 新增学院信息
     * 
     * @param teachCollege 学院信息
     * @return 结果
     */
    public int insertTeachCollege(TeachCollege teachCollege);

    /**
     * 修改学院信息
     * 
     * @param teachCollege 学院信息
     * @return 结果
     */
    public int updateTeachCollege(TeachCollege teachCollege);

    /**
     * 批量删除学院信息
     * 
     * @param ids 需要删除的学院信息主键集合
     * @return 结果
     */
    public int deleteTeachCollegeByIds(Long[] ids);

    /**
     * 删除学院信息信息
     * 
     * @param id 学院信息主键
     * @return 结果
     */
    public int deleteTeachCollegeById(Long id);

    List<TreeSelect> buildCollegeTreeSelect(List<TeachCollege> colleges);

    public List<TeachCollege> buildCollegeTree(List<TeachCollege> colleges);

}
