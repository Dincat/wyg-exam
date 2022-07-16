package com.wyg.modules.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * 监控中心
 * 
 * @author wyg
 */
@EnableAdminServer
@SpringBootApplication
public class WorrilessGoMonitorApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(WorrilessGoMonitorApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  监控中心启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "      __          __             _ _                  _____                                        \n" +
                "      \\ \\        / /            (_) |                / ____|                                     \n" +
                "       \\ \\  /\\  / /__  _ __ _ __ _| | ___  ___ ___  | |  __  ___                                \n" +
                "        \\ \\/  \\/ / _ \\| '__| '__| | |/ _ \\/ __/ __| | | |_ |/ _ \\                            \n" +
                "         \\  /\\  / (_) | |  | |  | | |  __/\\__ \\__ \\ | |__| | (_) |                            \n" +
                "          \\/  \\/ \\___/|_|  |_|  |_|_|\\___||___/___/  \\_____|\\___/                            \n");
    }
}
