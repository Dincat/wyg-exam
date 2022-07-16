package com.wyg.exam.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeEntity<T> {

    private static final long serialVersionUID = 7265456426423066026L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    protected Long id;


    /**
     * code
     */
    protected String code;

    /**
     * 父节点
     */
    protected T parent;

    /**
     * 父节点id
     */
    protected Long parentId;

    /**
     * 排序号
     */
    protected Long sort;

    /**
     * 子节点
     */
    protected List<TreeEntity> children = new ArrayList<>();

    public void add(TreeEntity node) {
        this.children.add(node);
    }
}
