package com.wyg.teach.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.wyg.common.core.utils.poi.ExcelUtil;
import com.wyg.teach.api.domain.TeachCourse;
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
import com.wyg.teach.service.ITeachCourseService;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 课程信息Controller
 * 
 * @author WorrilessGo
 * @date 2022-07-21
 */
@Api(value = "课程信息",description="课程信息",tags = "课程信息")
@RestController
@RequestMapping("/course")
public class TeachCourseController extends BaseController
{
    @Autowired
    private ITeachCourseService teachCourseService;

    /**
     * 查询课程信息列表
     */
    @RequiresPermissions("teach:course:list")
    @GetMapping("/list")
    @ApiOperation( value = "获取课程信息列表",notes = "",httpMethod = "GET" )
    public TableDataInfo list(TeachCourse teachCourse)
    {
        startPage();
        List<TeachCourse> list = teachCourseService.selectTeachCourseList(teachCourse);
        return getDataTable(list);
    }

    /**
     * 导出课程信息列表
     */
    @RequiresPermissions("teach:course:export")
    @Log(title = "导出课程信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation( value = "导出课程信息列表",notes = "",httpMethod = "POST" )
    public void export(HttpServletResponse response, TeachCourse teachCourse) throws IOException
    {
        List<TeachCourse> list = teachCourseService.selectTeachCourseList(teachCourse);
        ExcelUtil<TeachCourse> util = new ExcelUtil<TeachCourse>(TeachCourse.class);
        util.exportExcel(response, list, "课程信息数据");
    }

    /**
     * 获取课程信息详细信息
     */
    @RequiresPermissions("teach:course:query")
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取课程信息列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teachCourseService.selectTeachCourseById(id));
    }

    /**
     * 新增课程信息
     */
    @RequiresPermissions("teach:course:add")
    @Log(title = "课程信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation( value = "新增课程信息",notes = "",httpMethod = "POST" )
    public AjaxResult add(@RequestBody TeachCourse teachCourse)
    {
        return toAjax(teachCourseService.insertTeachCourse(teachCourse));
    }

    /**
     * 修改课程信息
     */
    @RequiresPermissions("teach:course:edit")
    @Log(title = "课程信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation( value = "修改课程信息",notes = "",httpMethod = "PUT" )
    public AjaxResult edit(@RequestBody TeachCourse teachCourse)
    {
        return toAjax(teachCourseService.updateTeachCourse(teachCourse));
    }

    /**
     * 删除课程信息
     */
    @RequiresPermissions("teach:course:remove")
    @Log(title = "课程信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation( value = "批量删除课程信息",notes = "删除单个或多个课程信息，传入ID数组。",httpMethod = "DELETE" )
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teachCourseService.deleteTeachCourseByIds(ids));
    }
}
