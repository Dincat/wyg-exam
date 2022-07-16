package com.wyg.exam;

import com.wyg.common.core.utils.SpringContextHolder;
import com.wyg.common.security.annotation.EnableCustomConfig;
import com.wyg.common.security.annotation.EnableRyFeignClients;
import com.wyg.common.swagger.annotation.EnableCustomSwagger2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
@Import(SpringContextHolder.class)
public class WorrilessGoExamApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorrilessGoExamApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  考试服务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "      __          __             _ _                  _____                                        \n" +
                "      \\ \\        / /            (_) |                / ____|                                     \n" +
                "       \\ \\  /\\  / /__  _ __ _ __ _| | ___  ___ ___  | |  __  ___                                \n" +
                "        \\ \\/  \\/ / _ \\| '__| '__| | |/ _ \\/ __/ __| | | |_ |/ _ \\                            \n" +
                "         \\  /\\  / (_) | |  | |  | | |  __/\\__ \\__ \\ | |__| | (_) |                            \n" +
                "          \\/  \\/ \\___/|_|  |_|  |_|_|\\___||___/___/  \\_____|\\___/                            \n");
    }
}
