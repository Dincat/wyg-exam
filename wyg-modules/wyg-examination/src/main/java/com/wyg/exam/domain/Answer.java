package com.wyg.exam.domain;

import java.util.Date;

import com.wyg.exam.domain.base.ExamBaseEntity;
import com.wyg.exam.domain.vo.SubjectVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.wyg.common.core.annotation.Excel;

/**
 * 答题对象 exam_answer
 *
 * @author WorrilessGo
 * @date 2022-04-06
 */
@ApiModel(description = "答题")
@Data
@ToString
public class Answer extends ExamBaseEntity<Answer> {
    private static final long serialVersionUID = 1L;

    /**
     * 考试记录id
     */
    @ApiModelProperty(value = "考试记录id", required = true)
    @Excel(name = "考试记录id")
    private Long examRecordId;

    /**
     * 题目ID
     */
    @ApiModelProperty(value = "题目ID", required = true)
    @Excel(name = "题目ID")
    private Long subjectId;

    /**
     * 题目类型
     */
    @ApiModelProperty(value = "题目类型", required = false)
    @Excel(name = "题目类型")
    private Integer type;

    /**
     * 答案
     */
    @ApiModelProperty(value = "答案", required = false)
    @Excel(name = "答案")
    private String answer;

    /**
     * 答题类型，0：正确，1：错误
     */
    @ApiModelProperty(value = "答题类型，0：正确，1：错误", required = true)
    @Excel(name = "答题类型，0：正确，1：错误")
    private Integer answerType;

    /**
     * 实际得分
     */
    @ApiModelProperty(value = "实际得分", required = false)
    @Excel(name = "实际得分")
    private Double score;

    /**
     * 批改状态
     */
    @ApiModelProperty(value = "批改状态", required = false)
    @Excel(name = "批改状态")
    private Integer markStatus;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;


}