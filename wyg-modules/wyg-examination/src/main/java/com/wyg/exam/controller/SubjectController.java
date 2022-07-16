package com.wyg.exam.controller;

import com.wyg.exam.domain.Examination;
import com.wyg.exam.domain.ExaminationSubject;
import com.wyg.exam.domain.base.ExamBaseEntity;
import com.wyg.exam.domain.vo.AnswerVO;
import com.wyg.exam.domain.vo.SubjectVO;
import com.wyg.exam.service.IAnswerService;
import com.wyg.exam.service.IExaminationSubjectService;
import com.wyg.exam.service.ISubjectService;
import com.wyg.exam.service.impl.SubjectService;
import com.wyg.common.core.constant.Constants;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.web.page.TableDataInfo;
import com.wyg.common.log.annotation.Log;
import com.wyg.common.log.enums.BusinessType;
import com.wyg.common.security.annotation.RequiresPermissions;
import com.wyg.common.security.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Api(value = "题目信息管理", description = "题目信息管理", tags = "题目信息管理")
@RestController
@RequestMapping("/subject")
public class SubjectController extends BaseController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    IAnswerService answerService;

    @Autowired
    IExaminationSubjectService examinationSubjectService;

    /**
     * 获取考试题目详细信息
     */
    @RequiresPermissions("exam:subject:query")
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取考试题目列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id, @RequestParam Integer type)
    {
        return AjaxResult.success(subjectService.getSubject(id, type));
    }

    /**
     * 查询考试题目列表
     */
    @RequiresPermissions("exam:subject:list")
    @GetMapping("/subjectList")
    @ApiOperation(value = "获取考试题目列表", notes = "", httpMethod = "GET")
    public TableDataInfo list(ExaminationSubject examinationSubject) {
        startPage();
        List<ExaminationSubject> list = examinationSubjectService.selectExaminationSubjectList(examinationSubject);
        TableDataInfo data = getDataTable(list);
//        List<SubjectVO> subjectlist = subjectService.getSubjectList(list);
//
//        TableDataInfo result = getDataTable(subjectlist);
//        result.setTotal(data.getTotal());
        return data;
    }


    /**
     * 新增考试信息
     */
    @RequiresPermissions("exam:examination:add")
    @Log(title = "创建题目", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation( value = "创建题目",notes = "",httpMethod = "POST" )
    public AjaxResult add(@RequestBody  @Valid SubjectVO subject)
    {
        subject.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(),SecurityUtils.getTenantCode());
        if (!(subjectService.insert(subject) > 0)) {
            subject = null;
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }


    /**
     * 修改考试题目
     */
    @RequiresPermissions("exam:subject:edit")
    @Log(title = "修改考试题目", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation( value = "修改考试题目",notes = "",httpMethod = "PUT" )
    public AjaxResult edit(@RequestBody  @Valid SubjectVO subject)
    {
        if (!(subjectService.update(subject) > 0)) {
            subject = null;
            return AjaxResult.error();
        }

       return AjaxResult.success();
    }

    /**
     * 根据考试ID查询题目id列表
     *
     * @param examinationId examinationId
     * @return ResponseBean
     * @author tangyi
     * @date 2019/06/18 14:31
     */
    @ApiImplicitParam(name = "examinationId", value = "考试ID", required = true, paramType = "path")
    @GetMapping("/subjectIds/{examinationId}")
    public AjaxResult findExaminationSubjectIds(@PathVariable Long examinationId) {
        ExaminationSubject examinationSubject=new ExaminationSubject();
        examinationSubject.setExaminationId(examinationId);
        examinationSubject.getParams().put(Constants.ORDER_BY_COLUMN,"subject_id");
        examinationSubject.getParams().put(Constants.IS_ASC,"asc");
        List<ExaminationSubject> subjects = examinationSubjectService.selectExaminationSubjectList(examinationSubject);
        subjects.forEach(ExamBaseEntity::clearCommonValue);
        return AjaxResult.success(subjects);
    }

    /**
     * 删除
     *
     * @param id id
     * @return ResponseBean
     * @author tangyi
     * @date 2018/11/10 21:43
     */
    @DeleteMapping("{id}")
    @ApiOperation(value = "删除题目", notes = "根据ID删除题目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "题目ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "type", value = "题目类型", required = true, dataType = "Integer")})
    @Log(title = "删除题目", businessType = BusinessType.DELETE)
    public AjaxResult deleteSubject(@PathVariable Long id, @RequestParam Integer type) {
        boolean success = false;
        SubjectVO subject = subjectService.getSubject(id, type);
        if (subject != null) {
            subject.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(), SecurityUtils.getTenantCode());
            success = subjectService.physicalDelete(subject) > 0;
            return  AjaxResult.success();
        }
        return AjaxResult.error("请求参数错误");
    }

    /**
     * 查询题目和答题
     *
     * @param answerVO 下一题的类型，选择题、判断题
     * @return ResponseBean
     * @author tangyi
     * @date 2019/01/16 22:25
     */
    @PostMapping("subjectAnswer")
    @ApiOperation(value = "查询题目和答题", notes = "根据题目id查询题目和答题")
    @ApiImplicitParams({@ApiImplicitParam(name = "subjectId", value = "题目ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "examRecordId", value = "考试记录ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "String"),
            @ApiImplicitParam(name = "nextType", value = "-1：当前题目，0：下一题，1：上一题", dataType = "Integer")})
    public AjaxResult subjectAnswer(@RequestBody  @Valid AnswerVO answerVO) {
        return AjaxResult.success(answerService.subjectAnswer(answerVO));
    }


}
