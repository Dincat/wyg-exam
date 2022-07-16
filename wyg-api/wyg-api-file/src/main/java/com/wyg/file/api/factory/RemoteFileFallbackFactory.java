package com.wyg.file.api.factory;

import com.wyg.file.api.RemoteFileService;
import com.wyg.file.api.domain.SysFile;
import com.wyg.file.api.domain.SysMaterial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.wyg.common.core.domain.R;

/**
 * 文件服务降级处理
 *
 * @author wyg
 */
@Component
public class RemoteFileFallbackFactory implements FallbackFactory<RemoteFileService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteFileFallbackFactory.class);

    @Override
    public RemoteFileService create(Throwable throwable) {
        log.error("文件服务调用失败:{}", throwable.getMessage());
        return new RemoteFileService() {
            @Override
            public R<SysMaterial> upload(MultipartFile file, String baseDir, Long folderId) {
                return R.fail("上传文件失败:" + throwable.getMessage());
            }


            @Override
            public R<SysMaterial> getMaterialInfo(Long id, String source) {
                return R.fail("获取文件信息失败:" + throwable.getMessage());
            }

        };
    }
}
