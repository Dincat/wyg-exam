package com.wyg.system.service.impl;

import java.util.List;
import com.wyg.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.system.mapper.SysConfigGroupMapper;
import com.wyg.system.domain.SysConfigGroup;
import com.wyg.system.service.ISysConfigGroupService;

/**
 * 参数分组Service业务层处理
 * 
 * @author WorrilessGo
 * @date 2022-07-13
 */
@Service
public class SysConfigGroupServiceImpl implements ISysConfigGroupService 
{
    @Autowired
    private SysConfigGroupMapper sysConfigGroupMapper;

    /**
     * 查询参数分组
     * 
     * @param id 参数分组主键
     * @return 参数分组
     */
    @Override
    public SysConfigGroup selectSysConfigGroupById(Long id)
    {
        return sysConfigGroupMapper.selectSysConfigGroupById(id);
    }

    /**
     * 查询参数分组列表
     * 
     * @param sysConfigGroup 参数分组
     * @return 参数分组
     */
    @Override
    public List<SysConfigGroup> selectSysConfigGroupList(SysConfigGroup sysConfigGroup)
    {
        return sysConfigGroupMapper.selectSysConfigGroupList(sysConfigGroup);
    }

    /**
     * 新增参数分组
     * 
     * @param sysConfigGroup 参数分组
     * @return 结果
     */
    @Override
    public int insertSysConfigGroup(SysConfigGroup sysConfigGroup)
    {
        sysConfigGroup.setCreateTime(DateUtils.getNowDate());
        return sysConfigGroupMapper.insertSysConfigGroup(sysConfigGroup);
    }

    /**
     * 修改参数分组
     * 
     * @param sysConfigGroup 参数分组
     * @return 结果
     */
    @Override
    public int updateSysConfigGroup(SysConfigGroup sysConfigGroup)
    {
        sysConfigGroup.setUpdateTime(DateUtils.getNowDate());
        return sysConfigGroupMapper.updateSysConfigGroup(sysConfigGroup);
    }

    /**
     * 批量删除参数分组
     * 
     * @param ids 需要删除的参数分组主键
     * @return 结果
     */
    @Override
    public int deleteSysConfigGroupByIds(Long[] ids)
    {
        return sysConfigGroupMapper.deleteSysConfigGroupByIds(ids);
    }

    /**
     * 删除参数分组信息
     *
     * @param id 参数分组主键
     * @return 结果
     */
    @Override
    public int deleteSysConfigGroupById(Long id)
    {
        return sysConfigGroupMapper.deleteSysConfigGroupById(id);
    }
}
