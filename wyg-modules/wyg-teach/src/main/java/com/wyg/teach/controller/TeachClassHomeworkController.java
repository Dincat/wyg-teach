package com.wyg.teach.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.wyg.teach.api.domain.TeachClassHomework;
import com.wyg.teach.service.ITeachClassHomeworkService;
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
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 班级作业Controller
 * 
 * @author WorrilessGo
 * @date 2022-08-12
 */
@Api(value = "班级作业",description="班级作业",tags = "班级作业")
@RestController
@RequestMapping("/class/homework")
public class TeachClassHomeworkController extends BaseController
{
    @Autowired
    private ITeachClassHomeworkService teachClassHomeworkService;

    /**
     * 查询班级作业列表
     */
    @RequiresPermissions("teach:class:homework:list")
    @GetMapping("/list")
    @ApiOperation( value = "获取班级作业列表",notes = "",httpMethod = "GET" )
    public TableDataInfo list(TeachClassHomework teachClassHomework)
    {
        startPage();
        List<TeachClassHomework> list = teachClassHomeworkService.selectTeachClassHomeworkList(teachClassHomework);
        return getDataTable(list);
    }

    /**
     * 导出班级作业列表
     */
    @RequiresPermissions("teach:class:homework:export")
    @Log(title = "导出班级作业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation( value = "导出班级作业列表",notes = "",httpMethod = "POST" )
    public void export(HttpServletResponse response, TeachClassHomework teachClassHomework) throws IOException
    {
        List<TeachClassHomework> list = teachClassHomeworkService.selectTeachClassHomeworkList(teachClassHomework);
        ExcelUtil<TeachClassHomework> util = new ExcelUtil<TeachClassHomework>(TeachClassHomework.class);
        util.exportExcel(response, list, "班级作业数据");
    }

    /**
     * 获取班级作业详细信息
     */
    @RequiresPermissions("teach:class:homework:query")
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取班级作业列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teachClassHomeworkService.selectTeachClassHomeworkById(id));
    }

    /**
     * 新增班级作业
     */
    @RequiresPermissions("teach:class:homework:add")
    @Log(title = "班级作业", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation( value = "新增班级作业",notes = "",httpMethod = "POST" )
    public AjaxResult add(@RequestBody TeachClassHomework teachClassHomework)
    {
        return toAjax(teachClassHomeworkService.insertTeachClassHomework(teachClassHomework));
    }

    /**
     * 修改班级作业
     */
    @RequiresPermissions("teach:class:homework:edit")
    @Log(title = "班级作业", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation( value = "修改班级作业",notes = "",httpMethod = "PUT" )
    public AjaxResult edit(@RequestBody TeachClassHomework teachClassHomework)
    {
        return toAjax(teachClassHomeworkService.updateTeachClassHomework(teachClassHomework));
    }

    /**
     * 删除班级作业
     */
    @RequiresPermissions("teach:class:homework:remove")
    @Log(title = "班级作业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation( value = "批量删除班级作业",notes = "删除单个或多个班级作业，传入ID数组。",httpMethod = "DELETE" )
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teachClassHomeworkService.deleteTeachClassHomeworkByIds(ids));
    }

    @RequiresPermissions("teach:class:homework:edit")
    @GetMapping(value = "/publish/{id}")
    @ApiOperation( value = "布置作业",notes = "",httpMethod = "GET" )
    public AjaxResult publish(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teachClassHomeworkService.publishHomework(id));
    }


}
