package com.wyg.system.api.factory;


import com.wyg.common.core.domain.R;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.system.api.RemoteConfigService;
import com.wyg.system.api.domain.SysConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RemoteConfigFallbackFactory implements FallbackFactory<RemoteConfigService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteConfigFallbackFactory.class);

    @Override
    public RemoteConfigService create(Throwable throwable)
    {
        log.error("系统参数调用失败:{}", throwable.getMessage());
        return new RemoteConfigService() {
            @Override
            public AjaxResult getConfigKey(String configKey) {return AjaxResult.success("系统参数调用失败");}

            @Override
            public R<String> getConfigValue(String configKey) {
              return   R.fail("系统参数调用失败:" + throwable.getMessage());
            }

            @Override
            public AjaxResult add(SysConfig config) {
                return AjaxResult.success("系统参数调用失败");
            }
        };

    }
}
