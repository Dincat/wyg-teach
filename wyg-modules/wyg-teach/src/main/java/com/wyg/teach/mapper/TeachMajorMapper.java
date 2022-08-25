package com.wyg.teach.mapper;

import java.util.List;
import com.wyg.teach.api.domain.TeachMajor;

/**
 * 专业信息Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-07-21
 */
public interface TeachMajorMapper 
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
     * 删除专业信息
     * 
     * @param id 专业信息主键
     * @return 结果
     */
    public int deleteTeachMajorById(Long id);

    /**
     * 批量删除专业信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachMajorByIds(Long[] ids);
}
