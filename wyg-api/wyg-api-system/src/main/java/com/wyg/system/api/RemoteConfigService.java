package com.wyg.system.api;


import com.wyg.common.core.constant.ServiceNameConstants;
import com.wyg.common.core.domain.R;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.system.api.domain.SysConfig;
import com.wyg.system.api.factory.RemoteConfigFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(contextId = "remoteConfigService", value = ServiceNameConstants.SYSTEM_SERVICE,fallbackFactory = RemoteConfigFallbackFactory.class)
public interface RemoteConfigService {

    /**
     * 通过key获取系统参数
     * @param configKey  key值
     * @return
    **/
    @GetMapping(value = "/config/configKey/{configKey}")
    public AjaxResult getConfigKey(@PathVariable("configKey") String configKey);

    /**
     * 通过key获取系统参数值
     * @param configKey  key值
     * @return
     **/
    @GetMapping(value = "/config/configValue/{configKey}")
    public R<String> getConfigValue(@PathVariable("configKey") String configKey);


    /**
     * 添加
     * @param config
     * @return
     */
    @PostMapping("/config")
    public AjaxResult add(@Validated @RequestBody SysConfig config);

}
