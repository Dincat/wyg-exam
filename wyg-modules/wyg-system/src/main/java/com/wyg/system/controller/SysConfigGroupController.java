package com.wyg.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wyg.common.log.annotation.Log;
import com.wyg.common.log.enums.BusinessType;
import com.wyg.common.security.annotation.RequiresPermissions;
import com.wyg.system.domain.SysConfigGroup;
import com.wyg.system.service.ISysConfigGroupService;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 参数分组Controller
 * 
 * @author WorrilessGo
 * @date 2022-07-13
 */
@Api(value = "参数分组",description="参数分组",tags = "参数分组")
@RestController
@RequestMapping("/config/group")
public class SysConfigGroupController extends BaseController
{
    @Autowired
    private ISysConfigGroupService sysConfigGroupService;

    /**
     * 查询参数分组列表
     */
    @RequiresPermissions("system:config:group:list")
    @GetMapping("/list")
    @ApiOperation( value = "获取参数分组列表",notes = "",httpMethod = "GET" )
    public TableDataInfo list(SysConfigGroup sysConfigGroup)
    {
        startPage();
        List<SysConfigGroup> list = sysConfigGroupService.selectSysConfigGroupList(sysConfigGroup);
        return getDataTable(list);
    }

    /**
     * 导出参数分组列表
     */
    @RequiresPermissions("system:config:group:export")
    @Log(title = "导出参数分组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation( value = "导出参数分组列表",notes = "",httpMethod = "POST" )
    public void export(HttpServletResponse response, SysConfigGroup sysConfigGroup) throws IOException
    {
        List<SysConfigGroup> list = sysConfigGroupService.selectSysConfigGroupList(sysConfigGroup);
        ExcelUtil<SysConfigGroup> util = new ExcelUtil<SysConfigGroup>(SysConfigGroup.class);
        util.exportExcel(response, list, "参数分组数据");
    }

    /**
     * 获取参数分组详细信息
     */
    @RequiresPermissions("system:config:group:query")
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取参数分组列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysConfigGroupService.selectSysConfigGroupById(id));
    }

    /**
     * 新增参数分组
     */
    @RequiresPermissions("system:config:group:add")
    @Log(title = "参数分组", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation( value = "新增参数分组",notes = "",httpMethod = "POST" )
    public AjaxResult add(@RequestBody SysConfigGroup sysConfigGroup)
    {
        return toAjax(sysConfigGroupService.insertSysConfigGroup(sysConfigGroup));
    }

    /**
     * 修改参数分组
     */
    @RequiresPermissions("system:config:group:edit")
    @Log(title = "参数分组", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation( value = "修改参数分组",notes = "",httpMethod = "PUT" )
    public AjaxResult edit(@RequestBody SysConfigGroup sysConfigGroup)
    {
        return toAjax(sysConfigGroupService.updateSysConfigGroup(sysConfigGroup));
    }

    /**
     * 删除参数分组
     */
    @RequiresPermissions("system:config:group:remove")
    @Log(title = "参数分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation( value = "批量删除参数分组",notes = "删除单个或多个参数分组，传入ID数组。",httpMethod = "DELETE" )
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysConfigGroupService.deleteSysConfigGroupByIds(ids));
    }
}
