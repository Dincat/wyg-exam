package com.wyg.system.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import com.wyg.common.core.constant.SecurityConstants;
import com.wyg.common.core.constant.ServiceNameConstants;
import com.wyg.common.core.domain.R;
import com.wyg.system.api.domain.SysUser;
import com.wyg.system.api.factory.RemoteUserFallbackFactory;
import com.wyg.system.api.model.LoginUser;

/**
 * 用户服务
 * 
 * @author wyg
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/user/info/{username}")
    public R<LoginUser> getUserInfo(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping("/user/findById/{id}")
    public R<SysUser> findById(@PathVariable("id") Long id, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 注册用户信息
     *
     * @param sysUser 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/user/register")
    public R<Boolean> registerUserInfo(@RequestBody SysUser sysUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 是否自动添加用户
     */
    @PostMapping("/user/addUser")
    public R<SysUser> addUser(@RequestBody SysUser sysUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 通过微信OpenId获取用户信息0
     * @param wxOpenId
     * @param source
     * @return
     */
    @GetMapping("/user/selectByWxOpenId/{wxOpenId}")
    public R<LoginUser> selectByWxOpenId(@PathVariable("wxOpenId") String wxOpenId,@RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
