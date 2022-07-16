package com.wyg.file;

import com.wyg.common.security.annotation.EnableCustomConfig;
import com.wyg.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.wyg.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.context.annotation.ComponentScan;

/**
 * 文件服务
 * 
 * @author wyg
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication(scanBasePackages = "com.wyg")
public class WorrilessGoFileApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(WorrilessGoFileApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  文件服务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "      __          __             _ _                  _____                                        \n" +
                "      \\ \\        / /            (_) |                / ____|                                     \n" +
                "       \\ \\  /\\  / /__  _ __ _ __ _| | ___  ___ ___  | |  __  ___                                \n" +
                "        \\ \\/  \\/ / _ \\| '__| '__| | |/ _ \\/ __/ __| | | |_ |/ _ \\                            \n" +
                "         \\  /\\  / (_) | |  | |  | | |  __/\\__ \\__ \\ | |__| | (_) |                            \n" +
                "          \\/  \\/ \\___/|_|  |_|  |_|_|\\___||___/___/  \\_____|\\___/                            \n");

    }
}
