package com.wyg.file.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * All rights Reserved, Designed By www.sunseagear.com
 *
 * @version V1.0
 * @package com.wyg.oss.config
 * @title:
 * @description: 阿里云配置 * @date: 2018/4/26 9:39
 * @copyright: 2017 www.sunseagear.com Inc. All rights reserved.
 */
public class LocalConfig {
    //外部访问域名
    String domain = "";
    //上传路径
    String uploadFilePath = "";

    String localFilePrefix="";

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUploadFilePath() {
        return uploadFilePath;
    }

    public void setUploadFilePath(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }

    public String getLocalFilePrefix() {
        return localFilePrefix;
    }

    public void setLocalFilePrefix(String localFilePrefix) {
        this.localFilePrefix = localFilePrefix;
    }
}
