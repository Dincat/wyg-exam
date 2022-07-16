package com.wyg.exam.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.wyg.common.core.utils.EmojiFilter;
import com.wyg.common.core.utils.StringUtils;
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
import com.wyg.exam.domain.ExamExaminationRank;
import com.wyg.exam.service.IExamExaminationRankService;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 考试成绩排行Controller
 * 
 * @author WorrilessGo
 * @date 2022-05-25
 */
@Api(value = "考试成绩排行",description="考试成绩排行",tags = "考试成绩排行")
@RestController
@RequestMapping("/rank")
public class ExamExaminationRankController extends BaseController
{
    @Autowired
    private IExamExaminationRankService examExaminationRankService;

    /**
     * 查询考试成绩排行列表
     */
    @RequiresPermissions("exam:rank:list")
    @GetMapping("/list")
    @ApiOperation( value = "获取考试成绩排行列表",notes = "",httpMethod = "GET" )
    public TableDataInfo list(ExamExaminationRank examExaminationRank)
    {
        startPage();
        List<ExamExaminationRank> list = examExaminationRankService.selectExamExaminationRankList(examExaminationRank);
        return getDataTable(list);
    }

    /**
     * 导出考试成绩排行列表
     */
    @RequiresPermissions("exam:rank:export")
    @Log(title = "导出考试成绩排行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation( value = "导出考试成绩排行列表",notes = "",httpMethod = "POST" )
    public void export(HttpServletResponse response, ExamExaminationRank examExaminationRank) throws IOException
    {
        List<ExamExaminationRank> list = examExaminationRankService.selectExamExaminationRankList(examExaminationRank);
        ExcelUtil<ExamExaminationRank> util = new ExcelUtil<ExamExaminationRank>(ExamExaminationRank.class);
        util.exportExcel(response, list, "考试成绩排行数据");
    }

    /**
     * 获取考试成绩排行详细信息
     */
    @RequiresPermissions("exam:rank:query")
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取考试成绩排行列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(examExaminationRankService.selectExamExaminationRankById(id));
    }

    /**
     * 新增考试成绩排行
     */
    @RequiresPermissions("exam:rank:add")
    @Log(title = "考试成绩排行", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation( value = "新增考试成绩排行",notes = "",httpMethod = "POST" )
    public AjaxResult add(@RequestBody ExamExaminationRank examExaminationRank)
    {
        return toAjax(examExaminationRankService.insertExamExaminationRank(examExaminationRank));
    }

    /**
     * 修改考试成绩排行
     */
    @RequiresPermissions("exam:rank:edit")
    @Log(title = "考试成绩排行", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation( value = "修改考试成绩排行",notes = "",httpMethod = "PUT" )
    public AjaxResult edit(@RequestBody ExamExaminationRank examExaminationRank)
    {
        return toAjax(examExaminationRankService.updateExamExaminationRank(examExaminationRank));
    }

    /**
     * 删除考试成绩排行
     */
    @RequiresPermissions("exam:rank:remove")
    @Log(title = "考试成绩排行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation( value = "批量删除考试成绩排行",notes = "删除单个或多个考试成绩排行，传入ID数组。",httpMethod = "DELETE" )
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(examExaminationRankService.deleteExamExaminationRankByIds(ids));
    }


    @GetMapping("/noauth/list")
    @ApiOperation( value = "获取考试成绩排行列表",notes = "",httpMethod = "GET" )
    public TableDataInfo webList(ExamExaminationRank examExaminationRank)
    {
        List<ExamExaminationRank> list = examExaminationRankService.selectRankList(examExaminationRank);

        for(ExamExaminationRank rank : list ){
            rank.setUserId(null);
            String userName= EmojiFilter.filterEmoji(rank.getUserName());
            if(StringUtils.isEmpty(userName)){
                userName="_";
            }else{
                userName=StringUtils.mask(userName);
            }
            rank.setUserName(userName);
        }

        return getDataTable(list);
    }

}
