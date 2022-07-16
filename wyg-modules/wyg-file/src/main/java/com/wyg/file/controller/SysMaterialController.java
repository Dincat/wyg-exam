package com.wyg.file.controller;

import com.wyg.common.core.domain.R;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.log.annotation.Log;
import com.wyg.common.log.enums.BusinessType;
import com.wyg.common.security.annotation.InnerAuth;
import com.wyg.file.api.model.FileInfo;
import com.wyg.file.client.OSSClientFactory;
import com.wyg.file.service.*;
import com.wyg.file.api.domain.SysMaterial;
import com.wyg.file.api.domain.SysMaterialFolder;
import com.wyg.file.utils.OSSClientHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 素材信息 业务处理
 *
 * @author xueyi
 */
@RestController
@RequestMapping("/material")
@Api(tags = "文件素材")
public class SysMaterialController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);


    @Autowired
    private ISysMaterialService materialService;

    @Autowired
    private ISysMaterialFolderService materialFolderService;

    @Autowired
    OSSClientFactory ossClientFactory;

    /**
     * 查询素材信息列表
     */
    @GetMapping("/list")
    public AjaxResult list(SysMaterialFolder folder) {
        //1.判断是否有folderId传进来,如无则代表前端传值错误,直接做空返回
        if (folder.getFolderId() != null) {
            SysMaterialFolder materialFolder;
            List<SysMaterial> materialList;
            List<SysMaterialFolder> children;
            //2.当有名称搜索时，采用名称模糊查询
            if (folder.getFolderName() != null && !folder.getFolderName().equals("")) {
                materialFolder = new SysMaterialFolder();
                materialFolder.setFolderName("默认文件夹");

                SysMaterial materialListByNick = new SysMaterial();
                materialListByNick.setMaterialNick(folder.getFolderName());
                materialList = materialService.selectMaterialList(materialListByNick);

                SysMaterialFolder folderListByNick = new SysMaterialFolder();
                folderListByNick.setFolderName(folder.getFolderName());
                children = materialFolderService.selectMaterialFolderList(folderListByNick);
            } else {
                //3.当名称为null/""，且文件夹Id，进行首页查询
                if (folder.getFolderId().equals(0L)) {
                    materialFolder = new SysMaterialFolder();
                    materialFolder.setFolderName("默认文件夹");
                }
                //4.当名称为null/""，且文件夹Id不为0时，进行文件夹查询
                else {
                    materialFolder = materialFolderService.selectMaterialFolderById(folder.getFolderId());
                }
                materialList = materialService.selectMaterialListByFolderId(folder.getFolderId());
                children = materialFolderService.selectDirectChildrenMaterialFolderById(folder.getFolderId());
            }
            if (materialList.size() > 0) {
                List<Object> o = new ArrayList<>(materialList);
                materialFolder.setMaterialList(o);
            }
            if (children.size() > 0) {
                List<Object> c = new ArrayList<>(children);
                materialFolder.setChildren(c);
            }
            return AjaxResult.success(materialFolder);
        }
        return AjaxResult.success();
    }

    /**
     * 素材上传
     */
    /*@PostMapping("/upload")*/
    @PostMapping("/upload")
    public AjaxResult material(HttpServletRequest request, @RequestParam(value = "folderId", required = false) Long
                                       folderId, @RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {    return AjaxResult.error("上传图片异常，请稍后再试");}

        try {

            FileInfo fileInfo=null;//fileService.Upload(file);

            OSSClientHelper ossClientHelper=ossClientFactory.getOSSClientHelper();
            SysMaterial material = ossClientHelper.upload(request,file,folderId);

            return AjaxResult.success(material);
        }
        catch (Exception e)
        {
            log.error("上传文件失败", e);
            return AjaxResult.error(e.getMessage());
        }

    }

    /**
     * 修改素材信息
     */
    @PutMapping
    public AjaxResult edit(@RequestBody SysMaterial material) {
        return toAjax(materialService.updateMaterial(material));
    }

    /**
     * 删除素材信息
     */
    @Log(title = "素材信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{materialId}")
    public AjaxResult remove(@PathVariable Long materialId) {
        //删除素材文件
        SysMaterial material = materialService.selectMaterialById(materialId);
//        materialService.delete(material.getMaterialUrl());
//        remoteFileService.delete(material.getMaterialOriginalUrl());
        return toAjax(materialService.deleteMaterialById(materialId));
    }

    /**
     * 获取设备信息详细信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取设备信息列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        if(id==null) return AjaxResult.error();

        SysMaterial material = materialService.selectMaterialById(id);
        return AjaxResult.success(material);
    }

    @InnerAuth
    @GetMapping("/findById/{id}")
    public R<SysMaterial> findById(@PathVariable("id") Long id){
        SysMaterial sysMaterial=   materialService.selectMaterialById(id);
        return R.ok(sysMaterial);
    }
}
