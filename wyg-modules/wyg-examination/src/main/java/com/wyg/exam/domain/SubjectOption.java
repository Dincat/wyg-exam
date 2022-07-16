package com.wyg.exam.domain;

import java.util.Date;
import java.util.List;

import com.wyg.exam.domain.base.ExamBaseEntity;
import com.wyg.exam.domain.vo.SubjectVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.wyg.common.core.annotation.Excel;

/**
 * 选择题选项对象 exam_subject_option
 *
 * @author WorrilessGo
 * @date 2022-04-06
 */
@ApiModel(description = "选择题选项")
@Data
@ToString
public class SubjectOption extends ExamBaseEntity<SubjectOption> {
    private static final long serialVersionUID = 1L;

    /**
     * 选择题ID
     */
    @ApiModelProperty(value = "选择题ID", required = true)
    @Excel(name = "选择题ID")
    private Long subjectChoicesId;

    /**
     * 选项名称
     */
    @ApiModelProperty(value = "选项名称", required = true)
    @Excel(name = "选项名称")
    private String optionName;

    /**
     * 选项内容
     */
    @ApiModelProperty(value = "选项内容", required = true)
    @Excel(name = "选项内容")
    private String optionContent;

    /**
     * 是否正确
     */
    private String right;

    /**
     * 词汇ID
     */
    @ApiModelProperty(value = "词汇ID", required = false)
    @Excel(name = "词汇ID")
    private Long wordId;

}