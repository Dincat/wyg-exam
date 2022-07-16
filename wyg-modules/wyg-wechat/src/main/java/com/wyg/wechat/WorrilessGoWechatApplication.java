package com.wyg.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.wyg.common.security.annotation.EnableCustomConfig;
import com.wyg.common.security.annotation.EnableRyFeignClients;
import com.wyg.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 定时任务
 * 
 * @author wyg
 */
@EnableCustomConfig
@EnableCustomSwagger2   
@EnableRyFeignClients
@SpringBootApplication
public class WorrilessGoWechatApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(WorrilessGoWechatApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  微信服务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n\n" +
                "      __          __             _ _                  _____                                        \n" +
                "      \\ \\        / /            (_) |                / ____|                                     \n" +
                "       \\ \\  /\\  / /__  _ __ _ __ _| | ___  ___ ___  | |  __  ___                                \n" +
                "        \\ \\/  \\/ / _ \\| '__| '__| | |/ _ \\/ __/ __| | | |_ |/ _ \\                            \n" +
                "         \\  /\\  / (_) | |  | |  | | |  __/\\__ \\__ \\ | |__| | (_) |                            \n" +
                "          \\/  \\/ \\___/|_|  |_|  |_|_|\\___||___/___/  \\_____|\\___/                            \n");
    }
}
