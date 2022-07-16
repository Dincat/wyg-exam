package com.wyg.exam.config;

import com.wyg.common.core.exception.ServiceException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 通用映射配置
 *
 * @author wyg
 */
@Data
@Component
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
    /**
     * 上传文件存储在本地的根路径
     */
    @Value("${exam.file.path}")
    private String localFilePath;

    /**
     * 当前服务的端口
     */
    @Value("${server.port}")
    private int serverPort;

    @Value("${exam.file.domain}")
    private String schemaUrl;

    public String getSchemaUrl() {
        try {
//            InetAddress localHost = InetAddress.getLocalHost();
//            String hostAddress = localHost.getHostAddress();
//            String url = "http://" + hostAddress + ":" + serverPort+"/";

            String url=this.schemaUrl;
            if(!url.endsWith("/")) url+="/";

            return url;

        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }

    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /** 本地文件上传路径 */
        registry.addResourceHandler("/**")
                .addResourceLocations("file:" + localFilePath + File.separator);
    }

    /**
     * 开启跨域
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // 设置允许跨域的路由
//        registry.addMapping("/**")
//                // 设置允许跨域请求的域名
//                .allowedOrigins("*")
//                // 设置允许的方法
//                .allowedMethods("GET");
//    }

}
