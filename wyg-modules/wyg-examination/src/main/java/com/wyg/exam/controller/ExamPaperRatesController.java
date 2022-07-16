package com.wyg.exam.com.wyg.system.com.foxinmy.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.wyg.exam.domain.ExaminationRecord;
import com.wyg.exam.service.IExaminationRecordService;
import com.wyg.common.core.exception.CheckedException;
import com.wyg.common.security.utils.SecurityUtils;
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
import com.wyg.exam.domain.ExamPaperRates;
import com.wyg.exam.service.IExamPaperRatesService;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 试卷评价Controller
 *
 * @author WorrilessGo
 * @date 2022-05-30
 */
@Api(value = "试卷评价", description = "试卷评价", tags = "试卷评价")
@RestController
@RequestMapping("/examRates")
public class ExamPaperRatesController extends BaseController {
    @Autowired
    private IExamPaperRatesService examPaperRatesService;

    @Autowired
    private IExaminationRecordService recordService;

    /**
     * 查询试卷评价列表
     */
    @RequiresPermissions("exam:rates:list")
    @GetMapping("/list")
    @ApiOperation(value = "获取试卷评价列表", notes = "", httpMethod = "GET")
    public TableDataInfo list(ExamPaperRates examPaperRates) {
        startPage();
        List<ExamPaperRates> list = examPaperRatesService.selectExamPaperRatesList(examPaperRates);
        return getDataTable(list);
    }

    /**
     * 导出试卷评价列表
     */
    @RequiresPermissions("exam:rates:export")
    @Log(title = "导出试卷评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出试卷评价列表", notes = "", httpMethod = "POST")
    public void export(HttpServletResponse response, ExamPaperRates examPaperRates) throws IOException {
        List<ExamPaperRates> list = examPaperRatesService.selectExamPaperRatesList(examPaperRates);
        ExcelUtil<ExamPaperRates> util = new ExcelUtil<ExamPaperRates>(ExamPaperRates.class);
        util.exportExcel(response, list, "试卷评价数据");
    }

    /**
     * 获取试卷评价详细信息
     */
    @RequiresPermissions("exam:rates:query")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取试卷评价列详细信息", notes = "", httpMethod = "GET")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(examPaperRatesService.selectExamPaperRatesById(id));
    }

    /**
     * 新增试卷评价
     */
    @RequiresPermissions("exam:rates:add")
    @Log(title = "试卷评价", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增试卷评价", notes = "", httpMethod = "POST")
    public AjaxResult add(@RequestBody ExamPaperRates examPaperRates) {
        return toAjax(examPaperRatesService.insertExamPaperRates(examPaperRates));
    }

    /**
     * 修改试卷评价
     */
    @RequiresPermissions("exam:rates:edit")
    @Log(title = "试卷评价", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改试卷评价", notes = "", httpMethod = "PUT")
    public AjaxResult edit(@RequestBody ExamPaperRates examPaperRates) {
        return toAjax(examPaperRatesService.updateExamPaperRates(examPaperRates));
    }

    /**
     * 删除试卷评价
     */
    @RequiresPermissions("exam:rates:remove")
    @Log(title = "试卷评价", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation(value = "批量删除试卷评价", notes = "删除单个或多个试卷评价，传入ID数组。", httpMethod = "DELETE")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(examPaperRatesService.deleteExamPaperRatesByIds(ids));
    }

    @Log(title = "用户评价", businessType = BusinessType.UPDATE)
    @PostMapping("userRate")
    @ApiOperation(value = "新增试卷用户评价", notes = "", httpMethod = "POST")
    public AjaxResult userRate(@RequestBody ExamPaperRates examPaperRates) {

        if (examPaperRates.getPaperId() == null || examPaperRates.getPaperId().longValue() < 1) {
            throw new CheckedException("获取评价数据失败。");
        }

        examPaperRates.setUserId(SecurityUtils.getUserId());
        ExaminationRecord query = new ExaminationRecord();
        query.setExaminationId(examPaperRates.getPaperId());
        query.setUserId(SecurityUtils.getUserId());
        List<ExaminationRecord> recordList = recordService.selectExaminationRecordList(query);

        if (recordList.size() < 1) {
            throw new CheckedException("请选答卷再进行评价。");
        }

        Double avgRating=examPaperRatesService.rate(examPaperRates);
        AjaxResult ajaxResult= AjaxResult.success();
        ajaxResult.put("rating",avgRating);

        return ajaxResult;

    }

    @GetMapping("/noauth/list")
    @ApiOperation(value = "获取试卷评价列表", notes = "", httpMethod = "GET")
    public TableDataInfo webList(ExamPaperRates examPaperRates) {
        startPage();
        List<ExamPaperRates> list = examPaperRatesService.selectExamPaperRatesList(examPaperRates);
        return getDataTable(list);
    }

}
