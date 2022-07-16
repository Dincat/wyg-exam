package com.wyg.exam.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.wyg.exam.domain.Examination;
import com.wyg.exam.domain.base.ExamBaseEntity;
import com.wyg.exam.domain.vo.SubjectVO;
import com.wyg.exam.service.impl.SubjectService;
import com.wyg.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiImplicitParam;
import org.apache.commons.collections4.CollectionUtils;
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
import com.wyg.exam.domain.ExaminationSubject;
import com.wyg.exam.service.IExaminationSubjectService;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 考试题目Controller
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
@Api(value = "考试题目",description="考试题目",tags = "考试题目")
@RestController
@RequestMapping("/examinationSubject")
public class ExaminationSubjectController extends BaseController
{
    @Autowired
    private IExaminationSubjectService examinationSubjectService;

    @Autowired
    SubjectService subjectService;

    /**
     * 查询考试题目列表
     */
    @RequiresPermissions("exam:subject:list")
    @GetMapping("/list")
    @ApiOperation( value = "获取考试题目列表",notes = "",httpMethod = "GET" )
    public TableDataInfo list(ExaminationSubject examinationSubject)
    {
        startPage();
        List<ExaminationSubject> list = examinationSubjectService.selectExaminationSubjectList(examinationSubject);
        return getDataTable(list);
    }

    /**
     * 导出考试题目列表
     */
    @RequiresPermissions("exam:subject:export")
    @Log(title = "导出考试题目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation( value = "导出考试题目列表",notes = "",httpMethod = "POST" )
    public void export(HttpServletResponse response, ExaminationSubject examinationSubject) throws IOException
    {
        List<ExaminationSubject> list = examinationSubjectService.selectExaminationSubjectList(examinationSubject);
        ExcelUtil<ExaminationSubject> util = new ExcelUtil<ExaminationSubject>(ExaminationSubject.class);
        util.exportExcel(response, list, "考试题目数据");
    }

    /**
     * 获取考试题目详细信息
     */
    @RequiresPermissions("exam:subject:query")
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取考试题目列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(examinationSubjectService.selectExaminationSubjectById(id));
    }

    /**
     * 新增考试题目
     */
    @RequiresPermissions("exam:subject:add")
    @Log(title = "考试题目", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation( value = "新增考试题目",notes = "",httpMethod = "POST" )
    public AjaxResult add(@RequestBody ExaminationSubject examinationSubject)
    {
        return toAjax(examinationSubjectService.insertExaminationSubject(examinationSubject));
    }

    /**
     * 修改考试题目
     */
    @RequiresPermissions("exam:subject:edit")
    @Log(title = "考试题目", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation( value = "修改考试题目",notes = "",httpMethod = "PUT" )
    public AjaxResult edit(@RequestBody ExaminationSubject examinationSubject)
    {
        return toAjax(examinationSubjectService.updateExaminationSubject(examinationSubject));
    }

    /**
     * 删除考试题目
     */
    @RequiresPermissions("exam:subject:remove")
    @Log(title = "考试题目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation( value = "批量删除考试题目",notes = "删除单个或多个考试题目，传入ID数组。",httpMethod = "DELETE" )
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(examinationSubjectService.deleteExaminationSubjectByIds(ids));
    }




}
