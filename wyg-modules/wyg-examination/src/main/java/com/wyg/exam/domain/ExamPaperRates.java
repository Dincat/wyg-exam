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
 * 试卷评价对象 exam_paper_rates
 *
 * @author WorrilessGo
 * @date 2022-05-30
 */
@ApiModel(description = "试卷评价")
@Data
@ToString
public class ExamPaperRates extends BaseEntity {
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
     * 评分
     */
    @ApiModelProperty(value = "评分", required = false)
    @Excel(name = "评分")
    private Long rating;

    /**
     * 评论
     */
    @ApiModelProperty(value = "评论", required = false)
    @Excel(name = "评论")
    private String comment;

    private String userName;

}