package com.wyg.exam.domain.base;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BaseService<D extends BaseMapper<T>, T extends ExamBaseEntity<T>> {

    @Autowired
    protected D dao;

    /**
     * 查询列表
     *
     * @param ids ids
     * @return List
     */
    public List<T> findListByIds(Long[] ids) {
        if (ids == null || ids.length == 0)
            return new ArrayList<>();
        return dao.findListByIds(ids);
    }
}
