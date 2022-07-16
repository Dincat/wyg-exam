package com.wyg.auth.form;

import com.wyg.common.core.constant.Constants;
import lombok.Data;

/**
 * 用户注册对象
 * 
 * @author wyg
 */

@Data
public class RegisterBody extends LoginBody
{
    private String email;

    private String phone;

    private String smsCode;

    /** 是否拥有访问后台权限 **/
    private String loginPower= Constants.COMMON_YES;
}
