package com.wyg.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.wyg.common.core.domain.R;
import com.wyg.common.core.utils.UrlUtils;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.system.api.RemoteConfigService;
import com.wyg.wechat.domain.WxConfig;
import com.wyg.wechat.mapper.WxConfigMapper;
import com.wyg.wechat.service.IWxConfigService;
import com.wyg.wechat.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "微信登录")
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class LoginController extends BaseController {

    @Autowired
    LoginService loginService;

    @Autowired
    IWxConfigService wxConfigService;

    @Autowired
    RemoteConfigService remoteConfigService;



    /**
     * 前端获取网页认证的链接
     *
     * @return 认证授权url
     */
    @ApiOperation(value = "前端获取网页认证的链接", notes = "前端获取网页认证的链接", httpMethod = "GET")
    @GetMapping(value = {"/getWebAuthUrl","/getWebAuthUrl/{wxConfigCode}"})
    public R<String> getWebAuthUrl(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable(value = "wxConfigCode",required = false) String wxConfigCode,
                                   @RequestParam(required = false) String scope) {

        if (StringUtils.isEmpty(scope)) scope = "snsapi_base";

        String state = wxConfigCode+"_"+scope;

        String referer = request.getHeader("Referer");
        String toPath= UrlUtils.getParams(referer,"redirect");

        String domain=remoteConfigService.getConfigValue("domainUrl").getData();
        String redirectUrl = domain+"/wxLogin";

        if(StringUtils.isNotEmpty(toPath))
            redirectUrl+="?redirect="+toPath;

        String authUrl = loginService.getWebAuthUrl(wxConfigCode, redirectUrl, scope, state);

        return R.ok(authUrl);
    }


    /**
     * 前端获取扫码登录认证的链接
     *
     * @return 认证授权url
     */
    @ApiOperation(value = "前端获取网页认证的链接", notes = "前端获取网页认证的链接", httpMethod = "GET")
    @GetMapping(value = {"/getQRAuthUrl","/getQRAuthUrl/{wxConfigCode}"})
    public R<String> getQRAuthUrl(HttpServletRequest request, HttpServletResponse response,
                                  @PathVariable(value = "wxConfigCode",required = false) String wxConfigCode,
                                  @RequestParam(required = false) String scope) {

        if (StringUtils.isEmpty(scope)) scope = "snsapi_base";

        String state = wxConfigCode+"_"+scope;

        String referer = request.getHeader("Referer");
        String toPath= UrlUtils.getParams(referer,"redirect");

        String redirectUrl = remoteConfigService.getConfigKey("domainUrl")+"/wxLogin";

        if(StringUtils.isNotEmpty(toPath))
            redirectUrl+="?redirect="+toPath;

        String authUrl = loginService.getQRAuthUrl(wxConfigCode, redirectUrl, scope, state);

        return R.ok(authUrl);
    }



    /**
     * 根据 code 获取 openid
     *
     * @param code 微信临时 code
     * @return
     */
    @GetMapping(value={"/getOpenId/{code}","/getOpenId/{wxConfigCode}/{code}"})
    @ApiOperation("getOpenId")
    public R<WxOAuth2AccessToken> getOpenId(@PathVariable(value = "wxConfigCode",required = false) String wxConfigCode, @PathVariable String code) {
        WxOAuth2AccessToken token = loginService.getOpenId(wxConfigCode, code);
        return R.ok(token);
    }


    /**
     * 根据 code 获取 openid
     *
     * @param code 微信临时 code
     * @return
     */
    @GetMapping(value={"/getWxUserInfo/{code}","/getWxUserInfo/{wxConfigCode}/{code}"})
    @ApiOperation("getWxUserInfo")
    public R<WxMpUser> getWxUserInfo(@PathVariable(value = "wxConfigCode",required = false) String wxConfigCode, @PathVariable String code) {
        WxMpUser wxMpUser = loginService.getUserInfo(wxConfigCode, code);
        return R.ok(wxMpUser);
    }


}
