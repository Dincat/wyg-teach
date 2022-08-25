package com.wyg.teach.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.wyg.teach.api.domain.TeachClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wyg.common.log.annotation.Log;
import com.wyg.common.log.enums.BusinessType;
import com.wyg.common.security.annotation.RequiresPermissions;
import com.wyg.teach.service.ITeachClassesService;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 班级信息Controller
 * 
 * @author WorrilessGo
 * @date 2022-07-21
 */
@Api(value = "班级信息",description="班级信息",tags = "班级信息")
@RestController
@RequestMapping("/classes")
public class TeachClassesController extends BaseController
{
    @Autowired
    private ITeachClassesService teachClassesService;

    /**
     * 查询班级信息列表
     */
    @RequiresPermissions("teach:classes:list")
    @GetMapping("/list")
    @ApiOperation( value = "获取班级信息列表",notes = "",httpMethod = "GET" )
    public TableDataInfo list(TeachClasses teachClasses)
    {
        startPage();
        List<TeachClasses> list = teachClassesService.selectTeachClassesList(teachClasses);
        return getDataTable(list);
    }

    /**
     * 导出班级信息列表
     */
    @RequiresPermissions("teach:classes:export")
    @Log(title = "导出班级信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation( value = "导出班级信息列表",notes = "",httpMethod = "POST" )
    public void export(HttpServletResponse response, TeachClasses teachClasses) throws IOException
    {
        List<TeachClasses> list = teachClassesService.selectTeachClassesList(teachClasses);
        ExcelUtil<TeachClasses> util = new ExcelUtil<TeachClasses>(TeachClasses.class);
        util.exportExcel(response, list, "班级信息数据");
    }

    /**
     * 获取班级信息详细信息
     */
    @RequiresPermissions("teach:classes:query")
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取班级信息列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teachClassesService.selectTeachClassesById(id));
    }

    /**
     * 新增班级信息
     */
    @RequiresPermissions("teach:classes:add")
    @Log(title = "班级信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation( value = "新增班级信息",notes = "",httpMethod = "POST" )
    public AjaxResult add(@RequestBody TeachClasses teachClasses)
    {
        return toAjax(teachClassesService.insertTeachClasses(teachClasses));
    }

    /**
     * 修改班级信息
     */
    @RequiresPermissions("teach:classes:edit")
    @Log(title = "班级信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation( value = "修改班级信息",notes = "",httpMethod = "PUT" )
    public AjaxResult edit(@RequestBody TeachClasses teachClasses)
    {
        return toAjax(teachClassesService.updateTeachClasses(teachClasses));
    }

    /**
     * 删除班级信息
     */
    @RequiresPermissions("teach:classes:remove")
    @Log(title = "班级信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation( value = "批量删除班级信息",notes = "删除单个或多个班级信息，传入ID数组。",httpMethod = "DELETE" )
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teachClassesService.deleteTeachClassesByIds(ids));
    }
}
