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
 * 考试信息对象 exam_examination
 *
 * @author WorrilessGo
 * @date 2022-04-06
 */
@ApiModel(description = "考试信息")
@Data
@ToString
public class Examination  extends ExamBaseEntity<Examination> {
    private static final long serialVersionUID = 1L;


    /**
     * 考试名称
     */
    @ApiModelProperty(value = "考试名称", required = false)
    @Excel(name = "考试名称")
    private String examinationName;

    /**
     * 考试类型
     */
    @ApiModelProperty(value = "考试类型", required = false)
    @Excel(name = "考试类型")
    private Integer type;

    /**
     * 考试注意事项
     */
    @ApiModelProperty(value = "考试注意事项", required = false)
    @Excel(name = "考试注意事项")
    private String attention;

    /**
     * 考试开始时间
     */
    @ApiModelProperty(value = "考试开始时间", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考试开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 考试结束时间
     */
    @ApiModelProperty(value = "考试结束时间", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考试结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 考试结束时间
     */
    @ApiModelProperty(value = "考试时长(分钟)", required = false)
    @Excel(name = "考试时长(分钟)")
    private Integer duration;



    /**
     * 总分
     */
    @ApiModelProperty(value = "总分", required = true)
    @Excel(name = "总分")
    private Long totalScore;

    /**
     * 试卷状态
     */
    @ApiModelProperty(value = "试卷状态", required = false)
    @Excel(name = "试卷状态")
    private String status;

    /**
     * 图片ID
     */
    @ApiModelProperty(value = "图片ID", required = false)
    @Excel(name = "图片ID")
    private Long avatarId;

    /**
     * 课程
     */
    @ApiModelProperty(value = "课程", required = false)
    @Excel(name = "课程")
    private Long courseId;


    /**
     * 试卷状态
     */
    @ApiModelProperty(value = "能否重复做题", required = false)
    @Excel(name = "能否重复做题")
    private String canRepeat;


    /**
     * 备注
     */
    private String remark;

    private Course course;

    /**
     * 封面地址
     */
    private String logoUrl;

    private Integer subjectCount;

    private double rating;

}