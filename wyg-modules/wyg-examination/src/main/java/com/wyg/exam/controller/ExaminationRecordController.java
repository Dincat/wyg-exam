package com.wyg.exam.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

import com.wyg.exam.domain.ExaminationSubject;
import com.wyg.exam.domain.vo.ExaminationRecordVO;
import com.wyg.exam.service.IAnswerService;
import com.wyg.common.core.constant.Constants;
import com.wyg.common.security.utils.SecurityUtils;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wyg.common.log.annotation.Log;
import com.wyg.common.log.enums.BusinessType;
import com.wyg.common.security.annotation.RequiresPermissions;
import com.wyg.exam.domain.ExaminationRecord;
import com.wyg.exam.service.IExaminationRecordService;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 考试记录Controller
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
@Api(value = "考试记录",description="考试记录",tags = "考试记录")
@RestController
@RequestMapping("/record")
public class ExaminationRecordController extends BaseController
{
    @Autowired
    private IExaminationRecordService examinationRecordService;

    @Autowired
    IAnswerService answerService;

    /**
     * 查询考试记录列表
     */
    @RequiresPermissions("exam:record:list")
    @GetMapping("/list")
    @ApiOperation( value = "获取考试记录列表",notes = "",httpMethod = "GET" )
    public TableDataInfo list(ExaminationRecord examinationRecord)
    {
        startPage();
        List<ExaminationRecord> list = examinationRecordService.selectExaminationRecordList(examinationRecord);
        return getDataTable(list);
    }

    /**
     * 导出考试记录列表
     */
    @RequiresPermissions("exam:record:export")
    @Log(title = "导出考试记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation( value = "导出考试记录列表",notes = "",httpMethod = "POST" )
    public void export(HttpServletResponse response, ExaminationRecord examinationRecord) throws IOException
    {
        List<ExaminationRecord> list = examinationRecordService.selectExaminationRecordList(examinationRecord);
        ExcelUtil<ExaminationRecord> util = new ExcelUtil<ExaminationRecord>(ExaminationRecord.class);
        util.exportExcel(response, list, "考试记录数据");
    }

    /**
     * 获取考试记录详细信息
     */
    @RequiresPermissions("exam:record:query")
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取考试记录列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(examinationRecordService.selectExaminationRecordById(id));
    }

    /**
     * 新增考试记录
     */
    @RequiresPermissions("exam:record:add")
    @Log(title = "考试记录", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation( value = "新增考试记录",notes = "",httpMethod = "POST" )
    public AjaxResult add(@RequestBody ExaminationRecord examinationRecord)
    {
        return toAjax(examinationRecordService.insertExaminationRecord(examinationRecord));
    }

    /**
     * 修改考试记录
     */
    @RequiresPermissions("exam:record:edit")
    @Log(title = "考试记录", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation( value = "修改考试记录",notes = "",httpMethod = "PUT" )
    public AjaxResult edit(@RequestBody ExaminationRecord examinationRecord)
    {
        return toAjax(examinationRecordService.updateExaminationRecord(examinationRecord));
    }

    /**
     * 删除考试记录
     */
    @RequiresPermissions("exam:record:remove")
    @Log(title = "考试记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation( value = "批量删除考试记录",notes = "删除单个或多个考试记录，传入ID数组。",httpMethod = "DELETE" )
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(examinationRecordService.deleteExaminationRecordByIds(ids));
    }

    /**
     * 成绩详情
     * @param id id
     * @return ResponseBean
     * @author tangyi
     * @date 2020/2/20 23:54
     */
    @GetMapping("/{id}/details")
    @ApiOperation(value = "成绩详情", notes = "根据考试记录id获取成绩详情")
    @ApiImplicitParam(name = "id", value = "考试记录ID", required = true, dataType = "Long", paramType = "path")
    public AjaxResult details(@PathVariable Long id) {
        return AjaxResult.success(examinationRecordService.details(id));
    }


    /**
     * 完成批改
     *
     * @param examRecord examRecord
     * @return ResponseBean
     * @author tangyi
     * @date 2019/06/19 14:33
     */
    @PutMapping("completeMarking")
    public AjaxResult completeMarking(@RequestBody ExaminationRecord examRecord) {
        return toAjax(answerService.completeMarking(examRecord));
    }


    /**
     * 开始考试
     *
     * @param examRecord examRecord
     * @return ResponseBean
     * @author tangyi
     * @date 2019/04/30 16:45
     */
    @PostMapping("start")
    @Log(title = "开始考试", businessType = BusinessType.INSERT)
    public AjaxResult start(@RequestBody ExaminationRecord examRecord) {
        return AjaxResult.success(answerService.start(examRecord));
    }


    /**
     * 查询考试监控数据
     *
     * @return ResponseBean
     * @author tangyi
     * @date 2019/10/27 20:07:38
     */
    @GetMapping("/noauth/dashboard")
    public AjaxResult findExamDashboardData() {
        String tenantCode=SecurityUtils.getTenantCode();
        return AjaxResult.success(examinationRecordService.findExamDashboardData(tenantCode));
    }

    /**
     * 查询过去n天的考试记录数据
     * @param tenantCode tenantCode
     * @param pastDays pastDays
     * @return ResponseBean
     * @author tangyi
     * @date 2020/1/31 5:46 下午
     */
    @GetMapping("dashboard/examRecordTendency")
    public AjaxResult findExamRecordTendency(@RequestParam @NotBlank String tenantCode,
                                                                        @RequestParam @NotBlank Integer pastDays) {
        return AjaxResult.success(examinationRecordService.findExamRecordTendency(tenantCode, pastDays));
    }


    /**
     * 查询考试记录列表
     */
    @GetMapping("/my/list")
    @ApiOperation( value = "获取考试记录列表",notes = "",httpMethod = "GET" )
    public TableDataInfo myList(ExaminationRecord examinationRecord)
    {
        if(examinationRecord==null) examinationRecord=new ExaminationRecord();
        examinationRecord.setUserId(SecurityUtils.getLoginUser().getSysUser().getUserId());
        examinationRecord.setDelFlag(Constants.NORMAL);

        startPage();
        List<ExaminationRecord> list = examinationRecordService.selectExaminationRecordList(examinationRecord);
        return getDataTable(list);
    }


}
