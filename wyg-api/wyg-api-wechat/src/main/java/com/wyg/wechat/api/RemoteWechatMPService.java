package com.wyg.wechat.api;

import com.wyg.common.core.constant.ServiceNameConstants;
import com.wyg.common.core.domain.R;
import com.wyg.wechat.api.factory.RemoteWechatMPFallbackFactory;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//公众号接口
@FeignClient(contextId = "wechatMPService", value = ServiceNameConstants.WORRILESSGO_WECHAT,fallbackFactory = RemoteWechatMPFallbackFactory.class)
public interface RemoteWechatMPService {

    //创建订单 发起支付
    @GetMapping("/auth/getWxUserInfo/{wxCode}/{code}")
    R<WxMpUser> getWxUserInfo(@PathVariable("wxCode") String wxCode, @PathVariable("code") String code);
}
