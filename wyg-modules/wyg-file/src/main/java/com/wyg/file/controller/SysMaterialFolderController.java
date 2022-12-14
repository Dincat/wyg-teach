package com.wyg.file.controller;

import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.log.annotation.Log;
import com.wyg.common.log.enums.BusinessType;
import com.wyg.file.api.domain.SysMaterialFolder;
import com.wyg.file.service.ISysMaterialFolderService;
import com.wyg.file.service.ISysMaterialService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 素材分类Controller
 *
 * @author xueyi
 */
@RestController
@RequestMapping("/folder")
@Api(tags = "文件素材分类")
public class SysMaterialFolderController extends BaseController {

    @Autowired
    private ISysMaterialFolderService materialFolderService;

    @Autowired
    private ISysMaterialService materialService;

    /**
     * 查询素材分类列表
     */
    @GetMapping("/list")
    public AjaxResult list(SysMaterialFolder materialFolder) {
        List<SysMaterialFolder> tree = materialFolderService.selectMaterialFolderList(materialFolder);
        return AjaxResult.success(tree);
    }

    /**
     * 查询素材分类所有父级
     */
    @GetMapping("/parent")
    public AjaxResult parent(Long folderId) {
        if (folderId != null) {
            List<SysMaterialFolder> list = new ArrayList<>();
            SysMaterialFolder mainFolder = new SysMaterialFolder();
            mainFolder.setFolderId(0L);
            mainFolder.setFolderName("默认文件夹");
            list.add(mainFolder);
            if (!folderId.equals(0L)) {
                SysMaterialFolder folder = materialFolderService.selectMaterialFolderById(folderId);
                if (!folder.getAncestors().equals("0") && folder.getAncestors() != null) {
                    list.addAll(materialFolderService.selectParentMaterialFolderByAncestors(folder.getAncestors()));
                }
                list.add(folder);
            }
            return AjaxResult.success(list);
        }
        return AjaxResult.success();
    }

    /**
     * 新增素材分类
     */
    //@RequiresPermissions("system:material:add")
    @Log(title = "素材分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysMaterialFolder folder) {
        SysMaterialFolder materialFolder = new SysMaterialFolder();
        materialFolder.setParentId(folder.getFolderId());
        materialFolder.setFolderName("新建文件夹");
        if (materialFolder.getParentId().equals(0L)) {
            materialFolder.setAncestors("0");
        } else {
            SysMaterialFolder parentFolder = materialFolderService.selectMaterialFolderById(materialFolder.getParentId());
            materialFolder.setAncestors(parentFolder.getAncestors() + "," + parentFolder.getFolderId());
        }
        return toAjax(materialFolderService.insertMaterialFolder(materialFolder));
    }

    /**
     * 修改素材分类
     */
    //@RequiresPermissions("system:material:edit")
    @Log(title = "素材分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysMaterialFolder materialFolder) {
        return toAjax(materialFolderService.updateMaterialFolder(materialFolder));
    }

    /**
     * 删除素材分类
     */
    //@RequiresPermissions("system:material:remove")
    @Log(title = "素材分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{folderId}")
    public AjaxResult remove(@PathVariable Long folderId) {
        List<SysMaterialFolder> materialFolderList = materialFolderService.selectChildrenMaterialFolderById(folderId);
        Long[] folderIds = null;
        folderIds = new Long[materialFolderList.size() + 1];
        folderIds[0] = folderId;
        for (int i = 1; i <= materialFolderList.size(); i++) {
            folderIds[i] = materialFolderList.get(i - 1).getFolderId();
        }
        materialService.deleteMaterialByFolderIds(folderIds);
        return toAjax(materialFolderService.deleteMaterialFolderByIds(folderIds));
    }
}