package com.wyg.exam.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.wyg.exam.domain.ExaminationSubject;
import com.wyg.exam.domain.vo.PaperVO;
import com.wyg.exam.service.IExaminationSubjectService;
import com.wyg.exam.service.impl.SubjectService;
import com.wyg.common.core.constant.Constants;
import com.wyg.common.security.utils.SecurityUtils;
import io.swagger.annotations.ApiImplicitParam;
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
import com.wyg.exam.domain.Examination;
import com.wyg.exam.service.IExaminationService;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 考试信息Controller
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
@Api(value = "考试信息",description="考试信息",tags = "考试信息")
@RestController
@RequestMapping("/examination")
public class ExaminationController extends BaseController
{
    @Autowired
    private IExaminationService examinationService;

    @Autowired
    IExaminationSubjectService examinationSubjectService;

    @Autowired
    SubjectService subjectService;

    /**
     * 查询考试信息列表
     */
    @RequiresPermissions("exam:examination:list")
    @GetMapping("/list")
    @ApiOperation( value = "获取考试信息列表",notes = "",httpMethod = "GET" )
    public TableDataInfo list(Examination examination)
    {
        startPage();
        List<Examination> list = examinationService.selectExaminationList(examination);
        return getDataTable(list);
    }

    /**
     * 导出考试信息列表
     */
    @RequiresPermissions("exam:examination:export")
    @Log(title = "导出考试信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation( value = "导出考试信息列表",notes = "",httpMethod = "POST" )
    public void export(HttpServletResponse response, Examination examination) throws IOException
    {
        List<Examination> list = examinationService.selectExaminationList(examination);
        ExcelUtil<Examination> util = new ExcelUtil<Examination>(Examination.class);
        util.exportExcel(response, list, "考试信息数据");
    }

    /**
     * 获取考试信息详细信息
     */
    @RequiresPermissions("exam:examination:query")
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取考试信息列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(examinationService.selectExaminationById(id));
    }

    /**
     * 新增考试信息
     */
    @RequiresPermissions("exam:examination:add")
    @Log(title = "考试信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation( value = "新增考试信息",notes = "",httpMethod = "POST" )
    public AjaxResult add(@RequestBody Examination examination)
    {
        examination.setCommonValue();
        return toAjax(examinationService.insertExamination(examination));
    }

    /**
     * 修改考试信息
     */
    @RequiresPermissions("exam:examination:edit")
    @Log(title = "考试信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation( value = "修改考试信息",notes = "",httpMethod = "PUT" )
    public AjaxResult edit(@RequestBody Examination examination)
    {
        return toAjax(examinationService.updateExamination(examination));
    }

    /**
     * 删除考试信息
     */
    @RequiresPermissions("exam:examination:remove")
    @Log(title = "考试信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation( value = "批量删除考试信息",notes = "删除单个或多个考试信息，传入ID数组。",httpMethod = "DELETE" )
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(examinationService.deleteExaminationByIds(ids));
    }

    /**
     * 查询考试信息列表
     */
    @RequiresPermissions("exam:exam:subject")
    @GetMapping("/subjectList")
    @ApiOperation( value = "获取考试信息列表",notes = "",httpMethod = "GET" )
    public TableDataInfo subjectList(ExaminationSubject examinationSubject)
    {
        startPage();
        List<ExaminationSubject> examinationSubjects = examinationSubjectService.selectExaminationSubjectList(examinationSubject);
        TableDataInfo td= getDataTable(examinationSubjects);

        return td;

//        List<SubjectVO> subjectDtoList = new ArrayList<>();
//        if (CollectionUtils.isNotEmpty(examinationSubjects)) {
//            // 按题目类型分组，获取对应的ID集合
//            subjectDtoList = subjectService.findSubjectVOList(examinationSubjects);
//        }
//
//        TableDataInfo result= getDataTable(subjectDtoList);
//        result.setTotal(td.getTotal());
//        return  result;
    }


    /**
     * 查询考试信息列表
     */
    @RequiresPermissions("exam:examination:list")
    @GetMapping("/examinationList")
    @ApiOperation( value = "获取考试信息列表",notes = "",httpMethod = "GET" )
    public TableDataInfo examinationList(Examination examination)
    {
        startPage();
        List<Examination> list = examinationService.selectExaminationList(examination);
        return getDataTable(list);
    }


    /**
     * 查询考试信息列表
     */
    @GetMapping("/noauth/list")
    @ApiOperation( value = "获取考试信息列表",notes = "",httpMethod = "GET" )
    public TableDataInfo webList(Examination examination)
    {
        if(examination==null) examination=new Examination();
        examination.setDelFlag(Constants.NORMAL);
        examination.setStatus(Constants.NORMAL);

        startPage();
        List<Examination> list = examinationService.selectExaminationList(examination);
        return getDataTable(list);
    }

    @GetMapping(value = "/noauth/{id}")
    @ApiOperation( value = "获取考试信息列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getWebInfo(@PathVariable("id") Long id)
    {
        PaperVO examination=examinationService.selectExaminationById(id);

        if(examination==null || !examination.getDelFlag().equals(Constants.NORMAL) || !examination.getStatus().equals(Constants.NORMAL)){
            return AjaxResult.error("试卷不存在。");
        }

        return AjaxResult.success(examination);
    }



}
