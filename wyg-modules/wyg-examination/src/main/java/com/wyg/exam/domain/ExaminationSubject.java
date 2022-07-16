package com.wyg.exam.domain;

import com.wyg.exam.domain.base.ExamBaseEntity;
import com.wyg.exam.domain.vo.SubjectVO;
import lombok.Data;
import lombok.ToString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.wyg.common.core.annotation.Excel;

/**
 * 考试题目对象 exam_examination_subject
 *
 * @author WorrilessGo
 * @date 2022-04-06
 */
@ApiModel(description = "考试题目")
@Data
@ToString
public class ExaminationSubject extends ExamBaseEntity<ExaminationSubject> {
    private static final long serialVersionUID = 1L;


    /**
     * 考试ID
     */
    @ApiModelProperty(value = "考试ID", required = false)
    @Excel(name = "考试ID")
    private Long examinationId;

    /**
     * 分类
     */
    @ApiModelProperty(value = "分类", required = false)
    @Excel(name = "分类")
    private Long categoryId;

    /**
     * 题目ID
     */
    @ApiModelProperty(value = "题目ID", required = true)
    @Excel(name = "题目ID")
    private Long subjectId;

    /**
     * 类型 0-选择题，1-简答题
     */
    @ApiModelProperty(value = "类型 0-选择题，1-简答题", required = true)
    @Excel(name = "类型 0-选择题，1-简答题")
    private Integer type;

    /** 题目名称 */
    @ApiModelProperty(value = "题目名称", required =false)
    @Excel(name = "题目名称")
    private String subjectName;

    /** 分值 */
    @ApiModelProperty(value = "分值", required =false)
    @Excel(name = "分值")
    private Double score;

    /** 正确数 */
    @ApiModelProperty(value = "正确数", required =false)
    @Excel(name = "正确数")
    private Integer correctCount;

    /** 错误数 */
    @ApiModelProperty(value = "错误数", required =false)
    @Excel(name = "错误数")
    private Integer incorrectCount;

    /**
     * 词汇ID
     */
    @ApiModelProperty(value = "词汇ID", required = false)
    @Excel(name = "词汇ID")
    private Long wordId;

}