package com.wyg.wechat.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;


import com.wyg.common.log.annotation.Log;
import com.wyg.common.log.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wyg.common.security.annotation.RequiresPermissions;
import com.wyg.wechat.domain.WxConfig;
import com.wyg.wechat.service.IWxConfigService;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 微信配置Controller
 * 
 * @author WorrilessGo
 * @date 2022-07-02
 */
@Api(value = "微信配置",description="微信配置",tags = "微信配置")
@RestController
@RequestMapping("/config")
public class WxConfigController extends BaseController
{
    @Autowired
    private IWxConfigService wxConfigService;

    /**
     * 查询微信配置列表
     */
    @RequiresPermissions("wechat:config:list")
    @GetMapping("/list")
    @ApiOperation( value = "获取微信配置列表",notes = "",httpMethod = "GET" )
    public TableDataInfo list(WxConfig wxConfig)
    {
        startPage();
        List<WxConfig> list = wxConfigService.selectWxConfigList(wxConfig);
        return getDataTable(list);
    }

    /**
     * 导出微信配置列表
     */
    @RequiresPermissions("wechat:config:export")
    @Log(title = "导出微信配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation( value = "导出微信配置列表",notes = "",httpMethod = "POST" )
    public void export(HttpServletResponse response, WxConfig wxConfig) throws IOException
    {
        List<WxConfig> list = wxConfigService.selectWxConfigList(wxConfig);
        ExcelUtil<WxConfig> util = new ExcelUtil<WxConfig>(WxConfig.class);
        util.exportExcel(response, list, "微信配置数据");
    }

    /**
     * 获取微信配置详细信息
     */
    @RequiresPermissions("wechat:config:query")
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取微信配置列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxConfigService.selectWxConfigById(id));
    }

    /**
     * 新增微信配置
     */
    @RequiresPermissions("wechat:config:add")
    @Log(title = "微信配置", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation( value = "新增微信配置",notes = "",httpMethod = "POST" )
    public AjaxResult add(@RequestBody WxConfig wxConfig)
    {
        return toAjax(wxConfigService.insertWxConfig(wxConfig));
    }

    /**
     * 修改微信配置
     */
    @RequiresPermissions("wechat:config:edit")
    @Log(title = "微信配置", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation( value = "修改微信配置",notes = "",httpMethod = "PUT" )
    public AjaxResult edit(@RequestBody WxConfig wxConfig)
    {
        return toAjax(wxConfigService.updateWxConfig(wxConfig));
    }

    /**
     * 删除微信配置
     */
    @RequiresPermissions("wechat:config:remove")
    @Log(title = "微信配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation( value = "批量删除微信配置",notes = "删除单个或多个微信配置，传入ID数组。",httpMethod = "DELETE" )
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxConfigService.deleteWxConfigByIds(ids));
    }
}
