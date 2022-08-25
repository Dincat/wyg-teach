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
import com.wyg.teach.api.domain.TeachCollegeStage;
import com.wyg.teach.service.ITeachCollegeStageService;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 学期阶段Controller
 * 
 * @author WorrilessGo
 * @date 2022-07-21
 */
@Api(value = "学期阶段",description="学期阶段",tags = "学期阶段")
@RestController
@RequestMapping("/stage")
public class TeachCollegeStageController extends BaseController
{
    @Autowired
    private ITeachCollegeStageService teachCollegeStageService;

    /**
     * 查询学期阶段列表
     */
    @RequiresPermissions("teach:stage:list")
    @GetMapping("/list")
    @ApiOperation( value = "获取学期阶段列表",notes = "",httpMethod = "GET" )
    public TableDataInfo list(TeachCollegeStage teachCollegeStage)
    {
        startPage();
        List<TeachCollegeStage> list = teachCollegeStageService.selectTeachCollegeStageList(teachCollegeStage);
        return getDataTable(list);
    }

    /**
     * 导出学期阶段列表
     */
    @RequiresPermissions("teach:stage:export")
    @Log(title = "导出学期阶段", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation( value = "导出学期阶段列表",notes = "",httpMethod = "POST" )
    public void export(HttpServletResponse response, TeachCollegeStage teachCollegeStage) throws IOException
    {
        List<TeachCollegeStage> list = teachCollegeStageService.selectTeachCollegeStageList(teachCollegeStage);
        ExcelUtil<TeachCollegeStage> util = new ExcelUtil<TeachCollegeStage>(TeachCollegeStage.class);
        util.exportExcel(response, list, "学期阶段数据");
    }

    /**
     * 获取学期阶段详细信息
     */
    @RequiresPermissions("teach:stage:query")
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取学期阶段列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teachCollegeStageService.selectTeachCollegeStageById(id));
    }

    /**
     * 新增学期阶段
     */
    @RequiresPermissions("teach:stage:add")
    @Log(title = "学期阶段", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation( value = "新增学期阶段",notes = "",httpMethod = "POST" )
    public AjaxResult add(@RequestBody TeachCollegeStage teachCollegeStage)
    {
        return toAjax(teachCollegeStageService.insertTeachCollegeStage(teachCollegeStage));
    }

    /**
     * 修改学期阶段
     */
    @RequiresPermissions("teach:stage:edit")
    @Log(title = "学期阶段", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation( value = "修改学期阶段",notes = "",httpMethod = "PUT" )
    public AjaxResult edit(@RequestBody TeachCollegeStage teachCollegeStage)
    {
        return toAjax(teachCollegeStageService.updateTeachCollegeStage(teachCollegeStage));
    }

    /**
     * 删除学期阶段
     */
    @RequiresPermissions("teach:stage:remove")
    @Log(title = "学期阶段", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation( value = "批量删除学期阶段",notes = "删除单个或多个学期阶段，传入ID数组。",httpMethod = "DELETE" )
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teachCollegeStageService.deleteTeachCollegeStageByIds(ids));
    }
}
