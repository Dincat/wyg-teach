package com.wyg.teach.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;


import com.wyg.common.core.utils.poi.ExcelUtil;
import com.wyg.teach.api.domain.TeachStudentHomework;
import com.wyg.teach.service.ITeachStudentHomeworkService;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 学生作业Controller
 *
 * @author WorrilessGo
 * @date 2022-08-12
 */
@Api(value = "学生作业", description = "学生作业", tags = "学生作业")
@RestController
@RequestMapping("/student/homework")
public class TeachStudentHomeworkController extends BaseController {
    @Autowired
    private ITeachStudentHomeworkService teachStudentHomeworkService;

    /**
     * 查询学生作业列表
     */
    @RequiresPermissions("teach:student:homework:list")
    @GetMapping("/list")
    @ApiOperation(value = "获取学生作业列表", notes = "", httpMethod = "GET")
    public TableDataInfo list(TeachStudentHomework teachStudentHomework) {
        startPage();
        List<TeachStudentHomework> list = teachStudentHomeworkService.selectTeachStudentHomeworkList(teachStudentHomework);
        return getDataTable(list);
    }

    /**
     * 导出学生作业列表
     */
    @RequiresPermissions("teach:student:homework:export")
    @Log(title = "导出学生作业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出学生作业列表", notes = "", httpMethod = "POST")
    public void export(HttpServletResponse response, TeachStudentHomework teachStudentHomework) throws IOException {
        List<TeachStudentHomework> list = teachStudentHomeworkService.selectTeachStudentHomeworkList(teachStudentHomework);
        ExcelUtil<TeachStudentHomework> util = new ExcelUtil<TeachStudentHomework>(TeachStudentHomework.class);
        util.exportExcel(response, list, "学生作业数据");
    }

    /**
     * 获取学生作业详细信息
     */
    @RequiresPermissions("teach:student:homework:query")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取学生作业列详细信息", notes = "", httpMethod = "GET")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(teachStudentHomeworkService.selectTeachStudentHomeworkById(id));
    }

    /**
     * 新增学生作业
     */
    @RequiresPermissions("teach:student:homework:add")
    @Log(title = "学生作业", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增学生作业", notes = "", httpMethod = "POST")
    public AjaxResult add(@RequestBody TeachStudentHomework teachStudentHomework) {
        return toAjax(teachStudentHomeworkService.insertTeachStudentHomework(teachStudentHomework));
    }

    /**
     * 修改学生作业
     */
    @RequiresPermissions("teach:student:homework:edit")
    @Log(title = "学生作业", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改学生作业", notes = "", httpMethod = "PUT")
    public AjaxResult edit(@RequestBody TeachStudentHomework teachStudentHomework) {
        return toAjax(teachStudentHomeworkService.updateTeachStudentHomework(teachStudentHomework));
    }

    /**
     * 删除学生作业
     */
    @RequiresPermissions("teach:student:homework:remove")
    @Log(title = "学生作业", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation(value = "批量删除学生作业", notes = "删除单个或多个学生作业，传入ID数组。", httpMethod = "DELETE")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(teachStudentHomeworkService.deleteTeachStudentHomeworkByIds(ids));
    }
}
