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
 * 题目分类对象 exam_subject_category
 *
 * @author WorrilessGo
 * @date 2022-04-06
 */
@ApiModel(description = "题目分类")
@Data
@ToString
public class SubjectCategory extends ExamBaseEntity<SubjectCategory> {
    private static final long serialVersionUID = 1L;


    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称", required = false)
    @Excel(name = "分类名称")
    private String categoryName;

    /**
     * 分类描述
     */
    @ApiModelProperty(value = "分类描述", required = false)
    @Excel(name = "分类描述")
    private String categoryDesc;

    /**
     * 父分类ID
     */
    @ApiModelProperty(value = "父分类ID", required = false)
    @Excel(name = "父分类ID")
    private Long parentId;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号", required = false)
    @Excel(name = "排序号")
    private Long sort;

    /**
     * 类型: 0-私共,1-公有
     */
    @ApiModelProperty(value = "类型: 0-私共,1-公有", required = true)
    @Excel(name = "类型: 0-私共,1-公有")
    private Integer type;

    /**
     * 起始页
     */
    @ApiModelProperty(value = "起始页", required = false)
    @Excel(name = "起始页")
    private Long startPage;

    /**
     * 结束页
     */
    @ApiModelProperty(value = "结束页", required = false)
    @Excel(name = "结束页")
    private Long endPage;

    /**
     * 书本ID
     */
    @ApiModelProperty(value = "书本ID", required = false)
    @Excel(name = "书本ID")
    private Long bookId;

    /**
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID", required = false)
    @Excel(name = "课程ID")
    private Long courseId;

    /**
     * 所有父级
     */
    @ApiModelProperty(value = "所有父级", required = false)
    @Excel(name = "所有父级")
    private String levels;


    /**
     * 分类状态
     */
    @ApiModelProperty(value = "分类状态", required = false)
    @Excel(name = "分类状态")
    private String status;

}