package com.wyg.system.service;

import java.util.List;
import com.wyg.system.domain.SysConfigGroup;

/**
 * 参数分组Service接口
 * 
 * @author WorrilessGo
 * @date 2022-07-13
 */
public interface ISysConfigGroupService 
{
    /**
     * 查询参数分组
     * 
     * @param id 参数分组主键
     * @return 参数分组
     */
    public SysConfigGroup selectSysConfigGroupById(Long id);

    /**
     * 查询参数分组列表
     * 
     * @param sysConfigGroup 参数分组
     * @return 参数分组集合
     */
    public List<SysConfigGroup> selectSysConfigGroupList(SysConfigGroup sysConfigGroup);

    /**
     * 新增参数分组
     * 
     * @param sysConfigGroup 参数分组
     * @return 结果
     */
    public int insertSysConfigGroup(SysConfigGroup sysConfigGroup);

    /**
     * 修改参数分组
     * 
     * @param sysConfigGroup 参数分组
     * @return 结果
     */
    public int updateSysConfigGroup(SysConfigGroup sysConfigGroup);

    /**
     * 批量删除参数分组
     * 
     * @param ids 需要删除的参数分组主键集合
     * @return 结果
     */
    public int deleteSysConfigGroupByIds(Long[] ids);

    /**
     * 删除参数分组信息
     * 
     * @param id 参数分组主键
     * @return 结果
     */
    public int deleteSysConfigGroupById(Long id);
}
