package com.wyg.teach.mapper;

import java.util.List;
import com.wyg.teach.api.domain.TeachClasses;

/**
 * 班级信息Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-07-21
 */
public interface TeachClassesMapper 
{
    /**
     * 查询班级信息
     * 
     * @param id 班级信息主键
     * @return 班级信息
     */
    public TeachClasses selectTeachClassesById(Long id);

    /**
     * 查询班级信息列表
     * 
     * @param teachClasses 班级信息
     * @return 班级信息集合
     */
    public List<TeachClasses> selectTeachClassesList(TeachClasses teachClasses);

    /**
     * 新增班级信息
     * 
     * @param teachClasses 班级信息
     * @return 结果
     */
    public int insertTeachClasses(TeachClasses teachClasses);

    /**
     * 修改班级信息
     * 
     * @param teachClasses 班级信息
     * @return 结果
     */
    public int updateTeachClasses(TeachClasses teachClasses);

    /**
     * 删除班级信息
     * 
     * @param id 班级信息主键
     * @return 结果
     */
    public int deleteTeachClassesById(Long id);

    /**
     * 批量删除班级信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachClassesByIds(Long[] ids);
}
