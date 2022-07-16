package com.wyg.exam.domain;

import java.util.Date;
import java.util.List;

import com.wyg.exam.domain.base.ExamBaseEntity;
import com.wyg.exam.domain.vo.SubjectOptionVO;
import com.wyg.exam.domain.vo.SubjectVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.wyg.common.core.annotation.Excel;

/**
 * 选择题对象 exam_subject_choices
 *
 * @author WorrilessGo
 * @date 2022-04-06
 */
@ApiModel(description = "选择题")
@Data
@ToString
public class SubjectChoices extends ExamBaseEntity<SubjectChoices> {
    private static final long serialVersionUID = 1L;


    /**
     * $column.columnComment
     */
    @ApiModelProperty(value = "${comment}", required = false)
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long categoryId;

    /**
     * 题目名称
     */
    @ApiModelProperty(value = "题目名称", required = true)
    @Excel(name = "题目名称")
    private String subjectName;

    /**
     * 题目类型
     */
    @ApiModelProperty(value = "题目类型", required = true)
    @Excel(name = "题目类型")
    private Integer choicesType;

    /**
     * 参考答案
     */
    @ApiModelProperty(value = "参考答案", required = true)
    @Excel(name = "参考答案")
    private String answer;

    /**
     * 题目分值
     */
    @ApiModelProperty(value = "题目分值", required = true)
    @Excel(name = "题目分值")
    private Double score;

    /**
     * 解析
     */
    @ApiModelProperty(value = "解析", required = false)
    @Excel(name = "解析")
    private String analysis;

    /**
     * 难度等级
     */
    @ApiModelProperty(value = "难度等级", required = true)
    @Excel(name = "难度等级")
    private Integer level;

    private Long examinationId;


    /**
     * 选项列表
     */
    private List<SubjectOption> options;

    /** 正确数 */
    @ApiModelProperty(value = "正确数", required =false)
    @Excel(name = "正确数")
    private Integer correctCount;

    /** 错误数 */
    @ApiModelProperty(value = "错误数", required =false)
    @Excel(name = "错误数")
    private Integer incorrectCount;
}