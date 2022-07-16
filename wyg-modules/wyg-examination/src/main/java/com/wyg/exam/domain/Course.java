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
 * 课程对象 exam_course
 *
 * @author WorrilessGo
 * @date 2022-04-06
 */
@ApiModel(description = "课程")
@Data
@ToString
public class Course extends ExamBaseEntity<Course> {
    private static final long serialVersionUID = 1L;


    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称", required = true)
    @Excel(name = "课程名称")
    private String courseName;

    /**
     * 学院
     */
    @ApiModelProperty(value = "学院", required = false)
    @Excel(name = "学院")
    private String college;

    /**
     * 专业
     */
    @ApiModelProperty(value = "专业", required = false)
    @Excel(name = "专业")
    private String major;

    /**
     * 老师
     */
    @ApiModelProperty(value = "老师", required = false)
    @Excel(name = "老师")
    private String teacher;

    /**
     * 课程描述
     */
    @ApiModelProperty(value = "课程描述", required = false)
    @Excel(name = "课程描述")
    private String courseDescription;

    /**
     * 课程封面
     */
    @ApiModelProperty(value = "课程封面", required = false)
    @Excel(name = "课程封面")
    private String cover;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = false)
    @Excel(name = "排序")
    private Long sequence;

    /**
     * 点击数
     */
    @ApiModelProperty(value = "点击数", required = false)
    @Excel(name = "点击数")
    private Long hits;

    /**
     * 评分
     */
    @ApiModelProperty(value = "评分", required = false)
    @Excel(name = "评分")
    private Long rating;

    /**
     * 购买数
     */
    @ApiModelProperty(value = "购买数", required = false)
    @Excel(name = "购买数")
    private Long buyCount;

    /**
     * 课时数
     */
    @ApiModelProperty(value = "课时数", required = false)
    @Excel(name = "课时数")
    private Long period;

    /**
     * 首页显示
     */
    @ApiModelProperty(value = "首页显示", required = false)
    @Excel(name = "首页显示")
    private String showIndex;

    /**
     * 是否显示
     */
    @ApiModelProperty(value = "是否显示", required = false)
    @Excel(name = "是否显示")
    private String isShow;

    /**
     * 课程介绍
     */
    @ApiModelProperty(value = "课程介绍", required = false)
    @Excel(name = "课程介绍")
    private String introduce;

    /**
     * 课程安排
     */
    @ApiModelProperty(value = "课程安排", required = false)
    @Excel(name = "课程安排")
    private String plan;

}