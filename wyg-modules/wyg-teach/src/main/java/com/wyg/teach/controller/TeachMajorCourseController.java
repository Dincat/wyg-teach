package com.wyg.teach.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
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
import com.wyg.teach.api.domain.TeachMajorCourse;
import com.wyg.teach.service.ITeachMajorCourseService;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 专业课程Controller
 * 
 * @author WorrilessGo
 * @date 2022-07-29
 */
@Api(value = "专业课程",description="专业课程",tags = "专业课程")
@RestController
@RequestMapping("/major/course")
public class TeachMajorCourseController extends BaseController
{
    @Autowired
    private ITeachMajorCourseService teachMajorCourseService;

    /**
     * 查询专业课程列表
     */
    @RequiresPermissions("teach:course:list")
    @GetMapping("/list")
    @ApiOperation( value = "获取专业课程列表",notes = "",httpMethod = "GET" )
    public TableDataInfo list(TeachMajorCourse teachMajorCourse)
    {
        startPage();
        List<TeachMajorCourse> list = teachMajorCourseService.selectTeachMajorCourseList(teachMajorCourse);
        return getDataTable(list);
    }

    /**
     * 导出专业课程列表
     */
    @RequiresPermissions("teach:course:export")
    @Log(title = "导出专业课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation( value = "导出专业课程列表",notes = "",httpMethod = "POST" )
    public void export(HttpServletResponse response, TeachMajorCourse teachMajorCourse) throws IOException
    {
        List<TeachMajorCourse> list = teachMajorCourseService.selectTeachMajorCourseList(teachMajorCourse);
        ExcelUtil<TeachMajorCourse> util = new ExcelUtil<TeachMajorCourse>(TeachMajorCourse.class);
        util.exportExcel(response, list, "专业课程数据");
    }

    /**
     * 获取专业课程详细信息
     */
    @RequiresPermissions("teach:course:query")
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取专业课程列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teachMajorCourseService.selectTeachMajorCourseById(id));
    }

    /**
     * 新增专业课程
     */
    @RequiresPermissions("teach:course:add")
    @Log(title = "专业课程", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation( value = "新增专业课程",notes = "",httpMethod = "POST" )
    public AjaxResult add(@RequestBody TeachMajorCourse teachMajorCourse)
    {
        return toAjax(teachMajorCourseService.insertTeachMajorCourse(teachMajorCourse));
    }

    /**
     * 修改专业课程
     */
    @RequiresPermissions("teach:course:edit")
    @Log(title = "专业课程", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation( value = "修改专业课程",notes = "",httpMethod = "PUT" )
    public AjaxResult edit(@RequestBody TeachMajorCourse teachMajorCourse)
    {
        return toAjax(teachMajorCourseService.updateTeachMajorCourse(teachMajorCourse));
    }

    /**
     * 删除专业课程
     */
    @RequiresPermissions("teach:course:remove")
    @Log(title = "专业课程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation( value = "批量删除专业课程",notes = "删除单个或多个专业课程，传入ID数组。",httpMethod = "DELETE" )
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teachMajorCourseService.deleteTeachMajorCourseByIds(ids));
    }
}
