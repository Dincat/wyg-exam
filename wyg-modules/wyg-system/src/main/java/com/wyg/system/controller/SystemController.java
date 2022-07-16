package com.wyg.system.controller;


import com.wyg.common.core.utils.DateUtils;
import com.wyg.common.core.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/system")
@Api(tags = "系统接口")
public class SystemController {
    /**
     * 获取服务器当前时间
     *
     * @return ResponseBean
     * @author tangyi
     * @date 2019/05/07 22:03
     */
    @GetMapping("currentTime")
    @ApiOperation(value = "获取服务器当前时间", notes = "获取服务器当前时间")
    public AjaxResult currentTime() {
        String time= DateUtils.localDateToString(LocalDateTime.now());
        AjaxResult result= AjaxResult.success(time,time);
        return result;

    }

}
