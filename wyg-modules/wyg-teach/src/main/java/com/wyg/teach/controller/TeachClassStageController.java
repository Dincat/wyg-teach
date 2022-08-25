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
import com.wyg.teach.api.domain.TeachClassStage;
import com.wyg.teach.service.ITeachClassStageService;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 班级阶段Controller
 * 
 * @author WorrilessGo
 * @date 2022-07-29
 */
@Api(value = "班级阶段",description="班级阶段",tags = "班级阶段")
@RestController
@RequestMapping("/classes/stage")
public class TeachClassStageController extends BaseController
{
    @Autowired
    private ITeachClassStageService teachClassStageService;

    /**
     * 查询班级阶段列表
     */
    @RequiresPermissions("teach:classes:list")
    @GetMapping("/list")
    @ApiOperation( value = "获取班级阶段列表",notes = "",httpMethod = "GET" )
    public TableDataInfo list(TeachClassStage teachClassStage)
    {
        startPage();
        List<TeachClassStage> list = teachClassStageService.selectTeachClassStageList(teachClassStage);
        return getDataTable(list);
    }

    /**
     * 导出班级阶段列表
     */
    @RequiresPermissions("teach:classes:export")
    @Log(title = "导出班级阶段", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation( value = "导出班级阶段列表",notes = "",httpMethod = "POST" )
    public void export(HttpServletResponse response, TeachClassStage teachClassStage) throws IOException
    {
        List<TeachClassStage> list = teachClassStageService.selectTeachClassStageList(teachClassStage);
        ExcelUtil<TeachClassStage> util = new ExcelUtil<TeachClassStage>(TeachClassStage.class);
        util.exportExcel(response, list, "班级阶段数据");
    }

    /**
     * 获取班级阶段详细信息
     */
    @RequiresPermissions("teach:classes:query")
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取班级阶段列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teachClassStageService.selectTeachClassStageById(id));
    }

    /**
     * 新增班级阶段
     */
    @RequiresPermissions("teach:classes:add")
    @Log(title = "班级阶段", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation( value = "新增班级阶段",notes = "",httpMethod = "POST" )
    public AjaxResult add(@RequestBody TeachClassStage teachClassStage)
    {
        return toAjax(teachClassStageService.insertTeachClassStage(teachClassStage));
    }

    /**
     * 修改班级阶段
     */
    @RequiresPermissions("teach:classes:edit")
    @Log(title = "班级阶段", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation( value = "修改班级阶段",notes = "",httpMethod = "PUT" )
    public AjaxResult edit(@RequestBody TeachClassStage teachClassStage)
    {
        return toAjax(teachClassStageService.updateTeachClassStage(teachClassStage));
    }

    /**
     * 删除班级阶段
     */
    @RequiresPermissions("teach:classes:remove")
    @Log(title = "班级阶段", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation( value = "批量删除班级阶段",notes = "删除单个或多个班级阶段，传入ID数组。",httpMethod = "DELETE" )
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teachClassStageService.deleteTeachClassStageByIds(ids));
    }
}
