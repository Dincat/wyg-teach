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
import com.wyg.teach.api.domain.TeachMajor;
import com.wyg.teach.service.ITeachMajorService;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 专业信息Controller
 * 
 * @author WorrilessGo
 * @date 2022-07-21
 */
@Api(value = "专业信息",description="专业信息",tags = "专业信息")
@RestController
@RequestMapping("/major")
public class TeachMajorController extends BaseController
{
    @Autowired
    private ITeachMajorService teachMajorService;

    /**
     * 查询专业信息列表
     */
    @RequiresPermissions("teach:major:list")
    @GetMapping("/list")
    @ApiOperation( value = "获取专业信息列表",notes = "",httpMethod = "GET" )
    public TableDataInfo list(TeachMajor teachMajor)
    {
        startPage();
        List<TeachMajor> list = teachMajorService.selectTeachMajorList(teachMajor);
        return getDataTable(list);
    }

    /**
     * 导出专业信息列表
     */
    @RequiresPermissions("teach:major:export")
    @Log(title = "导出专业信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation( value = "导出专业信息列表",notes = "",httpMethod = "POST" )
    public void export(HttpServletResponse response, TeachMajor teachMajor) throws IOException
    {
        List<TeachMajor> list = teachMajorService.selectTeachMajorList(teachMajor);
        ExcelUtil<TeachMajor> util = new ExcelUtil<TeachMajor>(TeachMajor.class);
        util.exportExcel(response, list, "专业信息数据");
    }

    /**
     * 获取专业信息详细信息
     */
    @RequiresPermissions("teach:major:query")
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取专业信息列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teachMajorService.selectTeachMajorById(id));
    }

    /**
     * 新增专业信息
     */
    @RequiresPermissions("teach:major:add")
    @Log(title = "专业信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation( value = "新增专业信息",notes = "",httpMethod = "POST" )
    public AjaxResult add(@RequestBody TeachMajor teachMajor)
    {
        return toAjax(teachMajorService.insertTeachMajor(teachMajor));
    }

    /**
     * 修改专业信息
     */
    @RequiresPermissions("teach:major:edit")
    @Log(title = "专业信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation( value = "修改专业信息",notes = "",httpMethod = "PUT" )
    public AjaxResult edit(@RequestBody TeachMajor teachMajor)
    {
        return toAjax(teachMajorService.updateTeachMajor(teachMajor));
    }

    /**
     * 删除专业信息
     */
    @RequiresPermissions("teach:major:remove")
    @Log(title = "专业信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation( value = "批量删除专业信息",notes = "删除单个或多个专业信息，传入ID数组。",httpMethod = "DELETE" )
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teachMajorService.deleteTeachMajorByIds(ids));
    }
}
