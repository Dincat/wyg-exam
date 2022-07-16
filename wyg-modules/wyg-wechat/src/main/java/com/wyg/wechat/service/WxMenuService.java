/*
MIT License

Copyright (c) 2020 www.joolun.com

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.wyg.wechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyg.wechat.domain.WxMenu;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 自定义菜单
 *
 * @author www.joolun.com
 * @date 2019-03-27 16:52:10
 */
public interface WxMenuService extends IService<WxMenu> {

	/***
	 * 获取WxApp下的菜单
	 * @return
	 */
	String getWxMenuButton();

	/**
	 * 保存并发布菜单
	 * @param
	 */
	void saveAndRelease(String strWxMenu) throws WxErrorException;

}
