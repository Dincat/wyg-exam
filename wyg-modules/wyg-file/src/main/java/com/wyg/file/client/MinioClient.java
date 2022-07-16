package com.wyg.file.client;

import com.wyg.file.config.MinioConfig;
import com.wyg.file.config.OssConfig;
import com.wyg.file.exception.OSSException;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;


public class MinioClient extends AbstractOSSClient {
    private MinioConfig minioConfig;

    @Autowired
    private io.minio.MinioClient client;

    @Override
    public void init() {

    }

    @Override
    public void init(String propertiesName) {

    }

    @Override
    public void init(OssConfig config) {
        minioConfig=config.getMinio();
    }


    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            UploadObjectArgs args=UploadObjectArgs.builder().filename(path).build();
            client.putObject(
                         PutObjectArgs.builder().bucket(minioConfig.getBucketName()).object(path).stream(
                                 inputStream, inputStream.available(), -1).build());

            return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + path;
        }catch (Exception ex){
            throw new OSSException("上传文件失败", ex);
        }

    }


    @Override
    public String upload(MultipartFile file, String path) {
        try {
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(minioConfig.getBucketName())
                    .object(path)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();
            client.putObject(args);
            return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + path;
        }catch (Exception ex){
            throw new OSSException("上传文件失败", ex);
        }

    }

    @Override
    public void delete(String filename) {

    }
}
