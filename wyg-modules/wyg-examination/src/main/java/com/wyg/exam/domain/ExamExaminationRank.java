package com.wyg.exam.domain;

import lombok.Data;
import lombok.ToString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wyg.common.core.annotation.Excel;
import com.wyg.common.core.web.domain.BaseEntity;

/**
 * 考试成绩排行对象 exam_examination_rank
 *
 * @author WorrilessGo
 * @date 2022-05-25
 */
@ApiModel(description = "考试成绩排行")
@Data
@ToString
public class ExamExaminationRank extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 试卷ID
     */
    @ApiModelProperty(value = "试卷ID", required = false)
    @Excel(name = "试卷ID")
    private Long paperId;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", required = false)
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = false)
    @Excel(name = "用户名")
    private String userName;

    /**
     * 成绩
     */
    @ApiModelProperty(value = "成绩", required = false)
    @Excel(name = "成绩")
    private Double score;

    /**
     * 正确数
     */
    @ApiModelProperty(value = "正确数", required = false)
    @Excel(name = "正确数")
    private Long correctNumber;

    /**
     * 错题数
     */
    @ApiModelProperty(value = "错题数", required = false)
    @Excel(name = "错题数")
    private Long incorrectNumber;

    /**
     * 排行
     */
    @ApiModelProperty(value = "排行", required = false)
    @Excel(name = "排行")
    private Long rank;

    /**
     * 用时
     */
    @ApiModelProperty(value = "用时", required = false)
    @Excel(name = "用时")
    private String elapsedTime;

}