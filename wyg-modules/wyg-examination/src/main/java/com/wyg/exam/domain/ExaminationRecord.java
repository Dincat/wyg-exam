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
 * 考试记录对象 exam_examination_record
 *
 * @author WorrilessGo
 * @date 2022-04-06
 */
@ApiModel(description = "考试记录")
@Data
@ToString
public class ExaminationRecord extends ExamBaseEntity<ExaminationRecord> {
    private static final long serialVersionUID = 1L;


    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", required = true)
    @Excel(name = "用户id")
    private Long userId;

    /** 考生姓名 */
    @ApiModelProperty(value = "考生姓名", required =false)
    @Excel(name = "考生姓名")
    private String userName;

    /**
     * 考试ID
     */
    @ApiModelProperty(value = "考试ID", required = true)
    @Excel(name = "试卷ID")
    private Long examinationId;

    /** 考试名称 */
    @ApiModelProperty(value = "考试名称", required =false)
    @Excel(name = "试卷名称")
    private String examinationName;

    /**
     * 考试类型
     */
    @ApiModelProperty(value = "考试类型", required = false)
    @Excel(name = "考试类型")
    private Integer examType;


    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 成绩
     */
    @ApiModelProperty(value = "成绩", required = false)
    @Excel(name = "成绩")
    private Double score;

    /**
     * 正确题目数量
     */
    @ApiModelProperty(value = "正确题目数量", required = false)
    @Excel(name = "正确题目数量")
    private Integer correctNumber;

    /**
     * 错误题目数量
     */
    @ApiModelProperty(value = "错误题目数量", required = false)
    @Excel(name = "错误题目数量")
    private Integer incorrectNumber;

    /**
     * 提交状态
     */
    @ApiModelProperty(value = "提交状态", required = false)
    @Excel(name = "提交状态")
    private Integer submitStatus;

    /** 考生所属部门 */
    @ApiModelProperty(value = "考生所属部门", required =false)
    @Excel(name = "考生所属部门")
    private String deptName;

}