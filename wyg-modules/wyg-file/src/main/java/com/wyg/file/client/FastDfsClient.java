package com.wyg.file.client;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.wyg.file.config.FastDfsConfig;
import com.wyg.file.config.OssConfig;
import com.wyg.file.exception.OSSException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class FastDfsClient extends AbstractOSSClient {

    private FastDfsConfig fastDfsConfig;

    @Autowired
    private FastFileStorageClient storageClient;

    @Override
    public void init() {

    }

    @Override
    public void init(String propertiesName) {

    }

    @Override
    public void init(OssConfig config) {
        fastDfsConfig=config.getFastDfsConfig();
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try{
            StorePath storePath = storageClient.uploadFile(inputStream, inputStream.available(),
                    FilenameUtils.getExtension(path), null);
            return fastDfsConfig.getDomain() + "/" + storePath.getFullPath();
        }catch (Exception ex){
            throw new OSSException("上传文件失败", ex);
        }
    }

    @Override
    public String upload(MultipartFile file, String path) {
        try{
        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return fastDfsConfig.getDomain() + "/" + storePath.getFullPath();
        }catch (Exception ex){
            throw new OSSException("上传文件失败", ex);
        }
    }

    @Override
    public void delete(String filename) {
        storageClient.deleteFile(filename);
    }
}
