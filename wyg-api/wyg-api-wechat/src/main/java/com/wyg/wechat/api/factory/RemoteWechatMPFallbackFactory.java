package com.wyg.wechat.api.factory;

import com.wyg.common.core.domain.R;
import com.wyg.wechat.api.RemoteWechatMPService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RemoteWechatMPFallbackFactory implements FallbackFactory<RemoteWechatMPService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteWechatMPFallbackFactory.class);

    @Override
    public RemoteWechatMPService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteWechatMPService()
        {
            @Override
            public R<WxMpUser> getWxUserInfo(String shopAccountId, String code) {
                return R.fail("获取微信用户失败:" + throwable.getMessage());
            }
        };
    }
}
