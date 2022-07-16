package com.wyg.file.api;

import com.wyg.common.core.constant.SecurityConstants;
import com.wyg.file.api.domain.SysFile;
import com.wyg.file.api.domain.SysMaterial;
import com.wyg.file.api.factory.RemoteFileFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.wyg.common.core.constant.ServiceNameConstants;
import com.wyg.common.core.domain.R;

/**
 * 文件服务
 *
 * @author wyg
 */
@FeignClient(contextId = "remoteFileService", value = ServiceNameConstants.FILE_SERVICE, fallbackFactory = RemoteFileFallbackFactory.class)
public interface RemoteFileService {
    /**
     * 上传文件
     *
     * @param file 文件信息
     * @return 结果
     */
    @PostMapping(value = "/upload/{baseDir}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<SysMaterial> upload(@RequestPart(value = "file") MultipartFile file,
                                 @PathVariable(value = "baseDir", required = false) String baseDir,
                                 @RequestParam(value = "folderId", required = false) Long folderId);

    @GetMapping(value = "/material/findById/{id}")
    public R<SysMaterial> getMaterialInfo(@PathVariable(value = "id") Long id, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

}
