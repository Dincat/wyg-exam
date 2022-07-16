package com.wyg.file.client;

import com.wyg.common.core.constant.Constants;
import com.wyg.common.core.utils.NumericUtils;
import com.wyg.common.redis.service.RedisService;
import com.wyg.common.security.utils.SecurityUtils;
import com.wyg.file.config.*;
import com.wyg.file.utils.OSSClientHelper;
import com.wyg.system.api.RemoteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OSSClientFactory {

    @Autowired
    private RemoteConfigService remoteConfigService;

    @Autowired
    private RedisService redisService;

    @Autowired
    ResourcesConfig resourcesConfig;

    private OssConfig initOssConfig(){
        String baseKey= "file.upload.";
        String clientType= remoteConfigService.getConfigValue(baseKey+"client.type").getData();

        String baseDir=remoteConfigService.getConfigValue(baseKey+"base.dir").getData();
        String allowedExtension=remoteConfigService.getConfigValue(baseKey+"allowed.extentsion").getData();
        String strMaxSize=remoteConfigService.getConfigValue(baseKey+"maxsize").getData();
        Long maxSize= NumericUtils.StringToLong(strMaxSize)*1024*1024;

        OssConfig ossConfig=new OssConfig();
        ossConfig.setBaseDir(baseDir);
        ossConfig.setClientType(clientType);
        ossConfig.setAllowedExtension(allowedExtension);
        ossConfig.setMaxSize(maxSize);


        AliyunConfig aliyunConfig=new AliyunConfig();
        aliyunConfig.setDomain(remoteConfigService.getConfigValue(baseKey+"aliyun.domain").getData());
        aliyunConfig.setEndpoint(remoteConfigService.getConfigValue(baseKey+"aliyun.endpoint").getData());
        aliyunConfig.setAccessKeyId(remoteConfigService.getConfigValue(baseKey+"aliyun.access.key").getData());
        aliyunConfig.setAccessKeySecret(remoteConfigService.getConfigValue(baseKey+"aliyun.access.secret").getData());
        aliyunConfig.setBucketName(remoteConfigService.getConfigValue(baseKey+"aliyun.bucket").getData());
        ossConfig.setAliyun(aliyunConfig);

        MinioConfig minioConfig=new MinioConfig();
        minioConfig.setUrl(remoteConfigService.getConfigValue(baseKey+"minio.url").getData());
        minioConfig.setAccessKey(remoteConfigService.getConfigValue(baseKey+"minio.access.key").getData());
        minioConfig.setSecretKey(remoteConfigService.getConfigValue(baseKey+"minio.access.secret").getData());
        minioConfig.setBucketName(remoteConfigService.getConfigValue(baseKey+"minio.bucket").getData());
        ossConfig.setMinio(minioConfig);

        FastDfsConfig fastDfsConfig=new FastDfsConfig();
        fastDfsConfig.setDomain(remoteConfigService.getConfigValue(baseKey+"fastdfs.domain").getData());
        ossConfig.setFastDfsConfig(fastDfsConfig);

        LocalConfig localConfig=new LocalConfig();
        localConfig.setDomain(remoteConfigService.getConfigValue(baseKey+"local.domain").getData());
        localConfig.setUploadFilePath(resourcesConfig.localFilePath);
        localConfig.setLocalFilePrefix(resourcesConfig.localFilePrefix);
        ossConfig.setLocal(localConfig);

        String cacheKey= Constants.SYS_FILE_KEY+ "tenant." + SecurityUtils.getTenantCode()+".oss.config";
        redisService.setCacheObject(cacheKey,ossConfig);
        return  ossConfig;
    }

    private OssConfig getOssConfig(){

        String cacheKey= Constants.SYS_FILE_KEY+ "tenant." + SecurityUtils.getTenantCode()+".oss.config";

        OssConfig ossConfig=redisService.getCacheObject(cacheKey);

        if(ossConfig==null) ossConfig=initOssConfig();

        return ossConfig;
    }


    public IOSSClient build() {
        OssConfig ossConfig=this.getOssConfig();

        return build(ossConfig);
    }

    public IOSSClient build(OssConfig ossConfig) {
        IOSSClient ossClient=null;

        switch (ossConfig.getClientType()){
            case "Local":
                ossClient = new LocalClient();
                break;
            case "Aliyun":
                ossClient = new AliyunOSSClient();
                break;
            case "Minio":
                ossClient = new MinioClient();
                break;
            case "FastDfs":
                ossClient = new FastDfsClient();
                break;
            case "QianNiu":
                ossClient = new QiniuOSSClient();
                break;
        }

        if(ossClient!=null) ossClient.init(ossConfig);

        return ossClient;
    }


    public OSSClientHelper getOSSClientHelper(){
        OssConfig ossConfig=this.getOssConfig();
        IOSSClient ossClient=build(ossConfig);
        OSSClientHelper ossClientHelper=new OSSClientHelper();
        ossClientHelper.init(ossConfig,ossClient);
        return ossClientHelper;
    }

}
