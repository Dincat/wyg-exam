package com.wyg.exam.domain.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapper<T> {
    /**
     * 根据id获取列表数据
     *
     * @param ids ids
     * @return List
     */
    List<T> findListByIds(@Param("ids") Long[] ids);

    /**
     * 物理删除
     *
     * @param subject
     * @return int
     * @author tangyi
     * @date 2019/06/16 22:44
     */
    int physicalDelete(Long id);

    /**
     * 物理批量删除
     *
     * @param ids ids
     * @return int
     * @author tangyi
     * @date 2019/06/16 22:44
     */
    int physicalDeleteAll(Long[] ids);

}
