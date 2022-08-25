package com.wyg.teach.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.wyg.common.core.domain.R;
import com.wyg.common.security.annotation.InnerAuth;
import com.wyg.system.api.domain.SysUser;
import com.wyg.teach.api.domain.TeachStudent;
import org.apache.commons.collections4.CollectionUtils;
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
import com.wyg.teach.api.domain.TeachTeacher;
import com.wyg.teach.service.ITeachTeacherService;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 教职工信息Controller
 * 
 * @author WorrilessGo
 * @date 2022-07-21
 */
@Api(value = "教职工信息",description="教职工信息",tags = "教职工信息")
@RestController
@RequestMapping("/teacher")
public class TeachTeacherController extends BaseController
{
    @Autowired
    private ITeachTeacherService teachTeacherService;

    /**
     * 查询教职工信息列表
     */
    @RequiresPermissions("teach:teacher:list")
    @GetMapping("/list")
    @ApiOperation( value = "获取教职工信息列表",notes = "",httpMethod = "GET" )
    public TableDataInfo list(TeachTeacher teachTeacher)
    {
        startPage();
        List<TeachTeacher> list = teachTeacherService.selectTeachTeacherList(teachTeacher);
        return getDataTable(list);
    }

    /**
     * 导出教职工信息列表
     */
    @RequiresPermissions("teach:teacher:export")
    @Log(title = "导出教职工信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation( value = "导出教职工信息列表",notes = "",httpMethod = "POST" )
    public void export(HttpServletResponse response, TeachTeacher teachTeacher) throws IOException
    {
        List<TeachTeacher> list = teachTeacherService.selectTeachTeacherList(teachTeacher);
        ExcelUtil<TeachTeacher> util = new ExcelUtil<TeachTeacher>(TeachTeacher.class);
        util.exportExcel(response, list, "教职工信息数据");
    }

    /**
     * 获取教职工信息详细信息
     */
    @RequiresPermissions("teach:teacher:query")
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取教职工信息列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teachTeacherService.selectTeachTeacherById(id));
    }

    /**
     * 新增教职工信息
     */
    @RequiresPermissions("teach:teacher:add")
    @Log(title = "教职工信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation( value = "新增教职工信息",notes = "",httpMethod = "POST" )
    public AjaxResult add(@RequestBody TeachTeacher teachTeacher)
    {
        return toAjax(teachTeacherService.insertTeachTeacher(teachTeacher));
    }

    /**
     * 修改教职工信息
     */
    @RequiresPermissions("teach:teacher:edit")
    @Log(title = "教职工信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation( value = "修改教职工信息",notes = "",httpMethod = "PUT" )
    public AjaxResult edit(@RequestBody TeachTeacher teachTeacher)
    {
        return toAjax(teachTeacherService.updateTeachTeacher(teachTeacher));
    }

    /**
     * 删除教职工信息
     */
    @RequiresPermissions("teach:teacher:remove")
    @Log(title = "教职工信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation( value = "批量删除教职工信息",notes = "删除单个或多个教职工信息，传入ID数组。",httpMethod = "DELETE" )
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teachTeacherService.deleteTeachTeacherByIds(ids));
    }

    /**
     * 获取教职工信息详细信息
     */
    @RequiresPermissions("teach:teacher:query")
    @GetMapping(value = "/getInfoByIdNumber/{idNumber}")
    @ApiOperation( value = "获取教职工信息列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfoByIdNumber(@PathVariable("idNumber") String idNumber)
    {
        TeachTeacher teacher=teachTeacherService.getTeacherByIdNumber(idNumber);
        return AjaxResult.success(teacher);
    }

    @InnerAuth
    @GetMapping("/getTeacherByIdNumber/{idNumber}")
    public R<TeachTeacher> getTeacherByIdNumber(@PathVariable("idNumber") String idNumber) {
        TeachTeacher teacher = teachTeacherService.getTeacherByIdNumber(idNumber);
        if (teacher == null || teacher.getId() < 1) {
            return R.fail("教职工信息不存在");
        }

        return R.ok(teacher);
    }

}
