package com.wyg.teach.service;

import java.util.List;
import com.wyg.teach.api.domain.TeachMajor;

/**
 * 专业信息Service接口
 * 
 * @author WorrilessGo
 * @date 2022-07-21
 */
public interface ITeachMajorService 
{
    /**
     * 查询专业信息
     * 
     * @param id 专业信息主键
     * @return 专业信息
     */
    public TeachMajor selectTeachMajorById(Long id);

    /**
     * 查询专业信息列表
     * 
     * @param teachMajor 专业信息
     * @return 专业信息集合
     */
    public List<TeachMajor> selectTeachMajorList(TeachMajor teachMajor);

    /**
     * 新增专业信息
     * 
     * @param teachMajor 专业信息
     * @return 结果
     */
    public int insertTeachMajor(TeachMajor teachMajor);

    /**
     * 修改专业信息
     * 
     * @param teachMajor 专业信息
     * @return 结果
     */
    public int updateTeachMajor(TeachMajor teachMajor);

    /**
     * 批量删除专业信息
     * 
     * @param ids 需要删除的专业信息主键集合
     * @return 结果
     */
    public int deleteTeachMajorByIds(Long[] ids);

    /**
     * 删除专业信息信息
     * 
     * @param id 专业信息主键
     * @return 结果
     */
    public int deleteTeachMajorById(Long id);
}
