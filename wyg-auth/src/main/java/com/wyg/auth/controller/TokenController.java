package com.wyg.auth.controller;

import javax.servlet.http.HttpServletRequest;

import com.wyg.common.core.constant.Constants;
import com.wyg.common.core.constant.HttpStatus;
import com.wyg.common.core.constant.SecurityConstants;
import com.wyg.common.core.exception.ServiceException;
import com.wyg.common.core.exception.auth.NotLoginException;
import com.wyg.common.core.utils.PasswordUtils;
import com.wyg.system.api.RemoteUserService;
import com.wyg.system.api.domain.SysUser;
import com.wyg.wechat.api.RemoteWechatMPService;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wyg.auth.form.LoginBody;
import com.wyg.auth.form.RegisterBody;
import com.wyg.auth.service.SysLoginService;
import com.wyg.common.core.domain.R;
import com.wyg.common.core.utils.JwtUtils;
import com.wyg.common.core.utils.StringUtils;
import com.wyg.common.security.auth.AuthUtil;
import com.wyg.common.security.service.TokenService;
import com.wyg.common.security.utils.SecurityUtils;
import com.wyg.system.api.model.LoginUser;

import java.util.Map;

/**
 * token 控制
 * 
 * @author wyg
 */
@RestController
public class TokenController
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private RemoteWechatMPService remoteWechatMPService;


    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form)
    {
        // 用户登录
        LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());
        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }

    @PostMapping("/web/login")
    public R<?> webLogin(@RequestBody LoginBody form)
    {
        // 用户登录
        LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());

        // 获取登录token
        Map<String, Object> token = tokenService.createToken(userInfo);

        return R.ok(token);
    }

    //微信公众号登录
    @ApiOperation( value = "微信公众号登录", notes = "微信公众号登录", httpMethod = "POST" )
    @GetMapping(value = "/wxLogin/{openId}")
    public R<Map> wxLogin(@PathVariable("openId") String openId)
    {
        //通过openId获取 用户信息
        LoginUser userInfo = remoteUserService.selectByWxOpenId(openId, SecurityConstants.INNER).getData();

        if(userInfo == null){
            return R.fail(HttpStatus.NO_CONTENT,"非法用户");
        }

        // 获取登录token
        Map<String, Object> token = tokenService.createToken(userInfo);

        return R.ok(token);
    }

    //获取微信用户信息并注册为系统用户，且登录
    @ApiOperation( value = "微信公众号登录", notes = "微信公众号登录", httpMethod = "POST" )
    @GetMapping(value = "/wxLoginByCode/{wxConfigCode}/{code}")
    public R<Map> wxLoginByCode(@PathVariable(value = "wxConfigCode",required = false) String wxConfigCode,@PathVariable("code") String code)
    {
        R<WxMpUser> wxMpUserR=  remoteWechatMPService.getWxUserInfo(wxConfigCode,code);

        WxMpUser wxMpUser=wxMpUserR.getData();
        if(wxMpUser==null || StringUtils.isEmpty(wxMpUser.getOpenId())){
            throw new NotLoginException("微信登录(code)登录失败");
        }

        //通过openId获取 用户信息
        LoginUser userInfo = remoteUserService.selectByWxOpenId(wxMpUser.getOpenId(), SecurityConstants.INNER).getData();

        if(userInfo==null || userInfo.getUserid()==null || userInfo.getUserid().longValue()<1) {

            SysUser sysUser = new SysUser();
            sysUser.setUserName(wxMpUser.getOpenId());
            sysUser.setNickName(wxMpUser.getNickname());
            //sysUser.setLoginPower(UserConstants.COMMON_NO);
            String pwd=SecurityUtils.encryptPassword(PasswordUtils.randomPassword(8));
            sysUser.setPassword(pwd);
            if (StringUtils.isEmpty(wxMpUser.getNickname())) sysUser.setNickName(wxMpUser.getOpenId());

//            String sex = wxMpUser.getSex().toString();
//            sysUser.setSex(sex);
            sysUser.setAvatar(wxMpUser.getHeadImgUrl());
            sysUser.setWxOpenId(wxMpUser.getOpenId());
            sysUser.setUserType("07");
            sysUser = remoteUserService.addUser(sysUser, SecurityConstants.INNER).getData();

            if (sysUser == null || sysUser.getUserId() < 1) {
                throw new ServiceException("微信登录(code)注册失败");
            }
        }

        //通过openId获取 用户信息
        userInfo = remoteUserService.selectByWxOpenId(wxMpUser.getOpenId(), SecurityConstants.INNER).getData();

        if(userInfo == null){
            return R.fail(HttpStatus.NO_CONTENT,"用户不存在");
        }

        // 获取登录token
        Map<String, Object> token = tokenService.createToken(userInfo);

        return R.ok(token);
    }


    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request)
    {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }

    @PostMapping("register")
    public R<?> register(@RequestBody RegisterBody registerBody)
    {
        // 用户注册
        sysLoginService.register(registerBody.getUsername(), registerBody.getPassword());
        return R.ok();
    }

    @PostMapping("/web/register")
    public R<?> webRegister(@RequestBody RegisterBody registerBody)
    {
        registerBody.setLoginPower(Constants.COMMON_NO);
        // 用户注册
        R<Boolean> result= sysLoginService.register(registerBody);
        return result;
    }
}
