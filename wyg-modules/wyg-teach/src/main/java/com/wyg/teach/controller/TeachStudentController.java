package com.wyg.teach.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.wyg.common.core.domain.R;
import com.wyg.common.security.annotation.InnerAuth;
import com.wyg.teach.api.domain.TeachTeacher;
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
import com.wyg.teach.api.domain.TeachStudent;
import com.wyg.teach.service.ITeachStudentService;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 学生信息Controller
 * 
 * @author WorrilessGo
 * @date 2022-07-28
 */
@Api(value = "学生信息",description="学生信息",tags = "学生信息")
@RestController
@RequestMapping("/student")
public class TeachStudentController extends BaseController
{
    @Autowired
    private ITeachStudentService teachStudentService;

    /**
     * 查询学生信息列表
     */
    @RequiresPermissions("teach:student:list")
    @GetMapping("/list")
    @ApiOperation( value = "获取学生信息列表",notes = "",httpMethod = "GET" )
    public TableDataInfo list(TeachStudent teachStudent)
    {
        startPage();
        List<TeachStudent> list = teachStudentService.selectTeachStudentList(teachStudent);
        return getDataTable(list);
    }

    /**
     * 导出学生信息列表
     */
    @RequiresPermissions("teach:student:export")
    @Log(title = "导出学生信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation( value = "导出学生信息列表",notes = "",httpMethod = "POST" )
    public void export(HttpServletResponse response, TeachStudent teachStudent) throws IOException
    {
        List<TeachStudent> list = teachStudentService.selectTeachStudentList(teachStudent);
        ExcelUtil<TeachStudent> util = new ExcelUtil<TeachStudent>(TeachStudent.class);
        util.exportExcel(response, list, "学生信息数据");
    }

    /**
     * 获取学生信息详细信息
     */
    @RequiresPermissions("teach:student:query")
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取学生信息列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teachStudentService.selectTeachStudentById(id));
    }

    /**
     * 新增学生信息
     */
    @RequiresPermissions("teach:student:add")
    @Log(title = "学生信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation( value = "新增学生信息",notes = "",httpMethod = "POST" )
    public AjaxResult add(@RequestBody TeachStudent teachStudent)
    {
        return toAjax(teachStudentService.insertTeachStudent(teachStudent));
    }

    /**
     * 修改学生信息
     */
    @RequiresPermissions("teach:student:edit")
    @Log(title = "学生信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation( value = "修改学生信息",notes = "",httpMethod = "PUT" )
    public AjaxResult edit(@RequestBody TeachStudent teachStudent)
    {
        return toAjax(teachStudentService.updateTeachStudent(teachStudent));
    }

    /**
     * 删除学生信息
     */
    @RequiresPermissions("teach:student:remove")
    @Log(title = "学生信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation( value = "批量删除学生信息",notes = "删除单个或多个学生信息，传入ID数组。",httpMethod = "DELETE" )
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teachStudentService.deleteTeachStudentByIds(ids));
    }


    /**
     * 获取学生信息详细信息
     */
    @RequiresPermissions("teach:student:query")
    @GetMapping(value = "/getInfoByIdNumber/{id}")
    @ApiOperation( value = "获取学生信息列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfoByIdNumber(@PathVariable("idNumber") String idNumber)
    {
        TeachStudent student=teachStudentService.getStudentByIdNumber(idNumber);
        return AjaxResult.success(student);
    }

    @InnerAuth
    @GetMapping("/getTeacherByIdNumber/{idNumber}")
    public R<TeachStudent> getTeacherByIdNumber(@PathVariable("idNumber") String idNumber) {
        TeachStudent student = teachStudentService.getStudentByIdNumber(idNumber);
        if (student == null || student.getId() < 1) {
            return R.fail("学生信息不存在");
        }

        return R.ok(student);
    }

}
