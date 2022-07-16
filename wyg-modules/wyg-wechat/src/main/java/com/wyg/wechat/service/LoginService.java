package com.wyg.wechat.service;

import com.wyg.common.core.exception.ServiceException;
import com.wyg.wechat.config.WxMpConfiguration;
import com.wyg.wechat.domain.WxUser;
import com.wyg.wechat.handler.SubscribeHandler;
import com.wyg.wechat.service.impl.WxUserServiceImpl;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    @Autowired
    private WxMpConfiguration wxMpConfiguration;

    @Autowired
    WxUserServiceImpl wxUserService;




    public String getWebAuthUrl(String wxConfigCode, String redirectUrl, String scope, String state) {
        // 传入当前的商户账号id，获取公众号账号信息
        WxMpService wxMpService = wxMpConfiguration.wxMpService(wxConfigCode);
        String response = wxMpService.getOAuth2Service().buildAuthorizationUrl(redirectUrl, scope, state);
        //System.out.println(response);
        return response;
    }

    public String getQRAuthUrl(String wxConfigCode, String redirectUrl, String scope, String state) {
        // 传入当前的商户账号id，获取公众号账号信息
        WxMpService wxMpService = wxMpConfiguration.wxMpService(wxConfigCode);
        String response = wxMpService.buildQrConnectUrl(redirectUrl, scope, state);
        //System.out.println(response);
        return response;
    }

    public WxOAuth2AccessToken getOpenId(String wxConfigCode, String code) {
        // 传入当前的商户账号id，获取公众号账号信息
        WxMpService wxMpService = wxMpConfiguration.wxMpService(wxConfigCode);
        WxOAuth2AccessToken token = null;
        try {
            token = wxMpService.getOAuth2Service().getAccessToken(code);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
        //System.out.println(token);
        return token;
    }

    /**
     * 根据openId获取微信用户信息
     *
     * @param wxConfigCodes
     * @param openId
     * @return
     */
    public WxMpUser getUserInfo(String wxConfigCode, String code) {
        // 传入当前的商户账号id，获取公众号账号信息
        WxMpService wxMpService = wxMpConfiguration.wxMpService(wxConfigCode);
        WxOAuth2AccessToken token = null;
        WxMpUser wxMpUser=new WxMpUser();
        try {
            token = wxMpService.getOAuth2Service().getAccessToken(code);

            if(token==null || token.getAccessToken().equals(null)){
                throw new ServiceException("登录失败：获取微信OpenID失败。");
            }

            WxOAuth2UserInfo oAuth2UserInfo = wxMpService.getOAuth2Service().getUserInfo(token,null);
            wxMpUser.setOpenId(oAuth2UserInfo.getOpenid());
            wxMpUser.setNickname(oAuth2UserInfo.getNickname());

            wxMpUser.setHeadImgUrl(oAuth2UserInfo.getHeadImgUrl());
            wxMpUser.setUnionId(oAuth2UserInfo.getUnionId());
            wxMpUser.setPrivileges(oAuth2UserInfo.getPrivileges());

            if(wxMpUser==null || StringUtils.isEmpty(wxMpUser.getOpenId())){
                throw new ServiceException("登录失败：获取微信用户信息失败。");
            }


            WxUser wxUser=new WxUser();
            SubscribeHandler.setWxUserValue(wxUser,wxMpUser);

            WxUser dbUser=  wxUserService.getByOpenId(wxMpUser.getOpenId());
            if(dbUser==null || StringUtils.isEmpty(dbUser.getOpenId())){
                wxUserService.save(wxUser);
            }else{
                wxUser.setId(dbUser.getId());
                wxUserService.updateById(wxUser);
            }

        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        return wxMpUser;
    }


}
