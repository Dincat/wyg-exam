package com.wyg.file.controller;

import com.wyg.common.core.domain.R;
import com.wyg.common.core.utils.StringUtils;
import com.wyg.common.core.utils.file.FileUtils;
import com.wyg.file.api.domain.SysMaterial;
import com.wyg.file.client.OSSClientFactory;
import com.wyg.file.utils.OSSClientHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.wyg.file.api.domain.SysFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件请求处理
 * 
 * @author wyg
 */
@RestController
public class SysFileController
{
    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);

    @Autowired
    OSSClientFactory ossClientFactory;

    /**
     * 文件上传请求
     */
    @PostMapping(value={"upload","upload/{baseDir}"})
    public R<SysMaterial> upload(HttpServletRequest request, MultipartFile file,
                                 @PathVariable(value = "baseDir", required = false) String baseDir,
                                 @RequestParam(value = "folderId", required = false) Long folderId)
    {
        try
        {
            OSSClientHelper ossClientHelper=ossClientFactory.getOSSClientHelper();
            // 上传并返回访问地址
            SysMaterial sysFile = new SysMaterial();
            if(StringUtils.isEmpty(baseDir)){
                sysFile=ossClientHelper.upload(request,file,folderId);
            }else{
                sysFile=ossClientHelper.upload(request,file,baseDir,folderId);
            }

            return R.ok(sysFile);
        }
        catch (Exception e)
        {
            log.error("上传文件失败", e);
            return R.fail(e.getMessage());
        }
    }
}