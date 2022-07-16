package com.wyg.exam.domain.vo;

import com.wyg.exam.domain.Answer;
import com.wyg.exam.domain.SubjectOption;
import com.wyg.exam.domain.base.ExamBaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wyg.common.core.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SubjectVO extends ExamBaseEntity<SubjectVO>{
    /**
     * 考试ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long examinationId;

    /**
     * 考试记录ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long examinationRecordId;

    /**
     * 题目分类ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long categoryId;

    /**
     * 题目名称
     */
    private String subjectName;

    /**
     * 题目类型
     */
    private Integer type;

    /**
     * 选择题类型
     */
    private Integer choicesType;

    /**
     * 分值
     */
    private Double score;


    /**
     * 解析
     */
    private String analysis;

    /**
     * 难度等级
     */
    private Integer level;

    /**
     * 答题
     */
    private Answer answer;

    /** 正确数 */
    @ApiModelProperty(value = "正确数", required =false)
    @Excel(name = "正确数")
    private Integer correctCount;

    /** 错误数 */
    @ApiModelProperty(value = "错误数", required =false)
    @Excel(name = "错误数")
    private Integer incorrectCount;

    /**
     * 选项列表
     */
    private List<SubjectOptionVO> options;

    /**
     * 词汇ID
     */
    @ApiModelProperty(value = "词汇ID", required = false)
    @Excel(name = "词汇ID")
    private Long wordId;

}
