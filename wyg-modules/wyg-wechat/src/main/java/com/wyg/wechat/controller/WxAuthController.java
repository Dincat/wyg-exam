package com.wyg.wechat.controller;

import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.wechat.config.WxMpConfiguration;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/wxauth")
@Api(value = "wxauth", tags = "微信验证服务")
public class WxAuthController  extends BaseController {

    @Autowired
    private WxMpConfiguration wxMpConfiguration;

    @GetMapping("/noauth/createJsapiSignature")
    public AjaxResult createJsapiSignature(@RequestParam String pageUrl) {

        System.out.println(pageUrl);
        // 传入当前的商户账号id，获取公众号账号信息
        WxMpService wxMpService = wxMpConfiguration.wxMpService();
        try {
            WxJsapiSignature jsapiSignature = wxMpService.createJsapiSignature(pageUrl);//当前html页面url,也是分享链接,与下面link保持一致,很坑
            return AjaxResult.success(jsapiSignature);
        } catch (WxErrorException e) {
            e.printStackTrace();
            return  AjaxResult.error(e.getMessage());
        }

    }

}
