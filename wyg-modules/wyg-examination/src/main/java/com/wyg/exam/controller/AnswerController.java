package com.wyg.exam.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.wyg.exam.domain.ExaminationSubject;
import com.wyg.exam.domain.vo.AnswerVO;
import com.wyg.exam.domain.vo.SubjectVO;
import com.wyg.exam.service.IExaminationSubjectService;
import com.wyg.exam.service.impl.SubjectService;
import com.github.pagehelper.PageInfo;
import com.wyg.common.security.utils.SecurityUtils;
import io.swagger.annotations.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wyg.common.log.annotation.Log;
import com.wyg.common.log.enums.BusinessType;
import com.wyg.common.security.annotation.RequiresPermissions;
import com.wyg.exam.domain.Answer;
import com.wyg.exam.service.IAnswerService;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.utils.poi.ExcelUtil;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 答题Controller
 *
 * @author WorrilessGo
 * @date 2022-04-06
 */
@Api(value = "答题信息管理", description = "答题信息管理", tags = "答题信息管理")
@RestController
@RequestMapping("answer")
public class AnswerController extends BaseController {
    @Autowired
    private IAnswerService answerService;

    @Autowired
    SubjectService subjectService;


    @Autowired
    private IExaminationSubjectService examinationSubjectService;

    /**
     * 查询答题列表
     */
    @RequiresPermissions("exam:answer:list")
    @GetMapping("/list")
    @ApiOperation(value = "获取答题列表", notes = "", httpMethod = "GET")
    public TableDataInfo list(Answer answer) {
        startPage();
        List<Answer> list = answerService.selectAnswerList(answer);
        return getDataTable(list);
    }

    /**
     * 导出答题列表
     */
    @RequiresPermissions("exam:answer:export")
    @Log(title = "导出答题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出答题列表", notes = "", httpMethod = "POST")
    public void export(HttpServletResponse response, Answer answer) throws IOException {
        List<Answer> list = answerService.selectAnswerList(answer);
        ExcelUtil<Answer> util = new ExcelUtil<Answer>(Answer.class);
        util.exportExcel(response, list, "答题数据");
    }

    /**
     * 获取答题详细信息
     */
    @RequiresPermissions("exam:answer:query")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取答题列详细信息", notes = "", httpMethod = "GET")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(answerService.selectAnswerById(id));
    }

    /**
     * 新增答题
     */
    @RequiresPermissions("exam:answer:add")
    @Log(title = "答题", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增答题", notes = "", httpMethod = "POST")
    public AjaxResult add(@RequestBody Answer answer) {
        return toAjax(answerService.insertAnswer(answer));
    }

    /**
     * 修改答题
     */
    @RequiresPermissions("exam:answer:edit")
    @Log(title = "答题", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改答题", notes = "", httpMethod = "PUT")
    public AjaxResult edit(@RequestBody Answer answer) {
        return toAjax(answerService.updateAnswer(answer));
    }

    /**
     * 删除答题
     */
    @RequiresPermissions("exam:answer:remove")
    @Log(title = "答题", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation(value = "批量删除答题", notes = "删除单个或多个答题，传入ID数组。", httpMethod = "DELETE")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(answerService.deleteAnswerByIds(ids));
    }


    /**
     * 获取答题详细信息
     */
    @RequiresPermissions("exam:answer:query")
    @GetMapping("/record/answerInfo/{recordId}")
    @ApiOperation(value = "获取答题列详细信息", notes = "", httpMethod = "GET")
    public AjaxResult answerInfo(@PathVariable Long recordId,
                                 @RequestParam(required = false) Long currentSubjectId,
                                 @RequestParam(required = false) Integer nextSubjectType,
                                 @RequestParam(required = false) Integer nextType) {
        return AjaxResult.success(answerService.answerInfo(recordId, currentSubjectId, nextSubjectType, nextType));
    }


    /**
     * 批改答题
     *
     * @param answer answer
     * @return ResponseBean
     * @author tangyi
     * @date 2020/02/22 14:47
     */
    @PutMapping("mark")
    @ApiOperation(value = "批改答题", notes = "根据答题id批改答题")
    @ApiImplicitParam(name = "answer", value = "答题实体answer", required = true, dataType = "Answer")
    //@Log("批改答题")
    public AjaxResult markAnswer(@RequestBody @Valid Answer answer) {
        answer.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(), SecurityUtils.getTenantCode());
        return toAjax(answerService.updateScore(answer));
    }

    /**
     * 保存答题，返回下一题信息
     *
     * @param answer          answer
     * @param nextType        0：下一题，1：上一题，2：提交
     * @param nextSubjectId   nextSubjectId
     * @param nextSubjectType 下一题的类型，选择题、判断题
     * @return ResponseBean
     * @author tangyi
     * @date 2019/04/30 18:06
     */
    @PostMapping("saveAndNext")
    @ApiOperation(value = "保存答题", notes = "保存答题")
    @ApiImplicitParam(name = "answer", value = "答题信息", dataType = "Answer")
    public AjaxResult saveAndNext(@RequestBody AnswerVO answer,
                                  @RequestParam Integer nextType,
                                  @RequestParam(required = false) Long nextSubjectId,
                                  @RequestParam(required = false) Integer nextSubjectType) {
        return AjaxResult.success(answerService.saveAndNext(answer, nextType, nextSubjectId, nextSubjectType));
    }


    /**
     * 提交答卷
     *
     * @param answer answer
     * @return ResponseBean
     * @author tangyi
     * @date 2018/12/24 20:44
     */
    @PostMapping("submit")
    @ApiOperation(value = "提交答卷", notes = "提交答卷")
    @ApiImplicitParam(name = "answer", value = "答卷信息", dataType = "Answer")
    @Log(title = "提交答题", businessType = BusinessType.UPDATE)
    public AjaxResult submit(@RequestBody Answer answer) {
      return   AjaxResult.success(answerService.submitAsync(answer));
    }


    /**
     * 提交答卷
     *
     * @param answer answer
     * @return ResponseBean
     * @author tangyi
     * @date 2018/12/24 20:44
     */
    @PostMapping("/noauth/submit")
    @ApiOperation(value = "提交答卷", notes = "提交答卷")
    @ApiImplicitParam(name = "answer", value = "答卷信息", dataType = "Answer")
    @Log(title = "提交答题", businessType = BusinessType.UPDATE)
    public AjaxResult anonymousUserSubmit(@RequestBody Answer answer) {
        return   AjaxResult.success(answerService.submitAsync(answer));
    }


    /**
     * 查询答题列表
     */
    //@RequiresPermissions("exam:answer:list")
    @GetMapping("/record/answerListInfo/{paperId}")
    @ApiOperation(value = "获取答题列表", notes = "", httpMethod = "GET")
    public TableDataInfo answerListInfo(@PathVariable Long paperId,Answer answer) {
        startPage();

        ExaminationSubject examinationSubject=new ExaminationSubject();
        examinationSubject.setExaminationId(paperId);
        List<ExaminationSubject> examinationSubjectList= examinationSubjectService.selectExaminationSubjectList(examinationSubject);
        TableDataInfo tbi= getDataTable(examinationSubjectList);

        List<Answer> answerList=new ArrayList<>();

        List<SubjectVO> subjectVOList= subjectService.getSubjectList(examinationSubjectList);
        List<Long> subjectIds=new ArrayList<>();
        if(CollectionUtils.isNotEmpty(subjectVOList)){
            subjectIds= subjectVOList.stream().map(SubjectVO::getId).collect(Collectors.toList());
            answer.getParams().put("subjectIdList",subjectIds);
            answerList = answerService.selectAnswerList(answer);
        }


        List<AnswerVO> answerVOList=answerService.toAnswerVO(subjectVOList,answerList);
        TableDataInfo result=new TableDataInfo(answerVOList,tbi.getTotal());

        return result;
    }


    /**
     * 获取排名数据，成绩由高到底排序，返回姓名、头像、分数信息
     * @param recordId recordId
     * @return ResponseBean
     * @author tangyi
     * @date 2019/12/8 23:32
     */
    @GetMapping("/record/rankInfo/{recordId}")
    @ApiOperation(value = "排名列表", notes = "排名列表")
    @ApiImplicitParam(name = "recordId", value = "考试记录id", dataType = "Long")
    public AjaxResult rankInfo(@PathVariable Long recordId) {
        return AjaxResult.success(answerService.getRankInfo(recordId));
    }

}
