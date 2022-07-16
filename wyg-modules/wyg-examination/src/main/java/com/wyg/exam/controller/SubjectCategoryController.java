package com.wyg.exam.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.wyg.exam.domain.vo.SubjectCategoryVO;
import com.wyg.common.core.constant.Constants;
import com.wyg.common.core.constant.UserConstants;
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
import com.wyg.exam.domain.SubjectCategory;
import com.wyg.exam.service.ISubjectCategoryService;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 题目分类Controller
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
@Api(value = "题目分类",description="题目分类",tags = "题目分类")
@RestController
@RequestMapping("/subjectCategory")
public class SubjectCategoryController extends BaseController
{
    @Autowired
    private ISubjectCategoryService subjectCategoryService;

    /**
     * 查询题目分类列表
     */
    @RequiresPermissions("exam:category:list")
    @GetMapping("/list")
    @ApiOperation( value = "获取题目分类列表",notes = "",httpMethod = "GET" )
    public TableDataInfo list(SubjectCategory subjectCategory)
    {
        startPage();
        List<SubjectCategory> list = subjectCategoryService.selectSubjectCategoryList(subjectCategory);
        return getDataTable(list);
    }

    /**
     * 导出题目分类列表
     */
    @RequiresPermissions("exam:category:export")
    @Log(title = "导出题目分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation( value = "导出题目分类列表",notes = "",httpMethod = "POST" )
    public void export(HttpServletResponse response, SubjectCategory subjectCategory) throws IOException
    {
        List<SubjectCategory> list = subjectCategoryService.selectSubjectCategoryList(subjectCategory);
        ExcelUtil<SubjectCategory> util = new ExcelUtil<SubjectCategory>(SubjectCategory.class);
        util.exportExcel(response, list, "题目分类数据");
    }

    /**
     * 获取题目分类详细信息
     */
    @RequiresPermissions("exam:category:query")
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取题目分类列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(subjectCategoryService.selectSubjectCategoryById(id));
    }

    /**
     * 新增题目分类
     */
    @RequiresPermissions("exam:category:add")
    @Log(title = "题目分类", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation( value = "新增题目分类",notes = "",httpMethod = "POST" )
    public AjaxResult add(@RequestBody SubjectCategory subjectCategory)
    {
        return toAjax(subjectCategoryService.insertSubjectCategory(subjectCategory));
    }

    /**
     * 修改题目分类
     */
    @RequiresPermissions("exam:category:edit")
    @Log(title = "题目分类", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation( value = "修改题目分类",notes = "",httpMethod = "PUT" )
    public AjaxResult edit(@RequestBody SubjectCategory subjectCategory)
    {
        return toAjax(subjectCategoryService.updateSubjectCategory(subjectCategory));
    }

    /**
     * 删除题目分类
     */
    @RequiresPermissions("exam:category:remove")
    @Log(title = "题目分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation( value = "批量删除题目分类",notes = "删除单个或多个题目分类，传入ID数组。",httpMethod = "DELETE" )
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(subjectCategoryService.deleteSubjectCategoryByIds(ids));
    }

    /**
     * 返回树形分类集合
     *
     * @return List
     * @author tangyi
     * @date 2018/12/04 22:03
     */
    @RequiresPermissions("exam:category:list")
    @GetMapping(value = "categories")
    @ApiOperation(value = "获取分类列表")
    public AjaxResult menus() {
        List<SubjectCategoryVO> result=subjectCategoryService.menus();
        return AjaxResult.success(result);
    }

    /**
     * 返回树形分类集合
     *
     * @return List
     * @author tangyi
     * @date 2018/12/04 22:03
     */
    @GetMapping(value = "web/categories")
    @ApiOperation(value = "获取分类列表")
    public AjaxResult webMenus() {
        List<SubjectCategoryVO> result=subjectCategoryService.menus(UserConstants.YES);
        return AjaxResult.success(result);
    }

}
