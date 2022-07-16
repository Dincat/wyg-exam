package com.wyg.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.wyg.common.security.annotation.EnableRyFeignClients;

/**
 * 认证授权中心
 * 
 * @author wyg
 */
@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class WorrilessGoAuthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(WorrilessGoAuthApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  认证授权中心启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "      __          __             _ _                  _____                                        \n" +
                "      \\ \\        / /            (_) |                / ____|                                     \n" +
                "       \\ \\  /\\  / /__  _ __ _ __ _| | ___  ___ ___  | |  __  ___                                \n" +
                "        \\ \\/  \\/ / _ \\| '__| '__| | |/ _ \\/ __/ __| | | |_ |/ _ \\                            \n" +
                "         \\  /\\  / (_) | |  | |  | | |  __/\\__ \\__ \\ | |__| | (_) |                            \n" +
                "          \\/  \\/ \\___/|_|  |_|  |_|_|\\___||___/___/  \\_____|\\___/                            \n");
    }
}
