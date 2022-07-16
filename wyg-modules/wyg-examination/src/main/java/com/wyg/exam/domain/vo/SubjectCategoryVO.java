package com.wyg.exam.domain.vo;

import com.wyg.exam.domain.SubjectCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wyg.common.core.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SubjectCategoryVO extends com.wyg.exam.domain.vo.TreeEntity<SubjectCategoryVO> {

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类描述
     */
    private String categoryDesc;

    /**
     * 分类状态
     */
    @ApiModelProperty(value = "分类状态", required = false)
    @Excel(name = "分类状态")
    private String status;

    /**
     * 父分类id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long parentId;

    public SubjectCategoryVO(SubjectCategory subjectCategory) {
        this.id = subjectCategory.getId();
        this.categoryName = subjectCategory.getCategoryName();
        this.categoryDesc = subjectCategory.getCategoryDesc();
        this.parentId = subjectCategory.getParentId();
        this.sort = subjectCategory.getSort();
        this.status=subjectCategory.getStatus();
    }

    @Override
    public Long getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
