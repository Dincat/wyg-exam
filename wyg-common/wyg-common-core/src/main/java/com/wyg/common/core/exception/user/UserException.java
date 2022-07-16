package com.wyg.common.core.exception.user;

import com.wyg.common.core.exception.base.BaseException;

/**
 * 用户信息异常类
 * 
 * @author wyg
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
