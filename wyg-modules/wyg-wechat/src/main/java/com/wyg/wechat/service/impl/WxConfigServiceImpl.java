package com.wyg.wechat.service.impl;

import java.util.List;

import com.wyg.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.wechat.mapper.WxConfigMapper;
import com.wyg.wechat.domain.WxConfig;
import com.wyg.wechat.service.IWxConfigService;
import org.springframework.util.CollectionUtils;

/**
 * 微信配置Service业务层处理
 * 
 * @author WorrilessGo
 * @date 2022-07-02
 */
@Service
public class WxConfigServiceImpl implements IWxConfigService 
{
    @Autowired
    private WxConfigMapper wxConfigMapper;

    /**
     * 查询微信配置
     * 
     * @param id 微信配置主键
     * @return 微信配置
     */
    @Override
    public WxConfig selectWxConfigById(Long id)
    {
        return wxConfigMapper.selectWxConfigById(id);
    }

    /**
     * 查询微信配置列表
     * 
     * @param wxConfig 微信配置
     * @return 微信配置
     */
    @Override
    public List<WxConfig> selectWxConfigList(WxConfig wxConfig)
    {
        return wxConfigMapper.selectWxConfigList(wxConfig);
    }

    /**
     * 新增微信配置
     * 
     * @param wxConfig 微信配置
     * @return 结果
     */
    @Override
    public int insertWxConfig(WxConfig wxConfig)
    {
        wxConfig.setCreateTime(DateUtils.getNowDate());
        return wxConfigMapper.insertWxConfig(wxConfig);
    }

    /**
     * 修改微信配置
     * 
     * @param wxConfig 微信配置
     * @return 结果
     */
    @Override
    public int updateWxConfig(WxConfig wxConfig)
    {
        wxConfig.setUpdateTime(DateUtils.getNowDate());
        return wxConfigMapper.updateWxConfig(wxConfig);
    }

    /**
     * 批量删除微信配置
     * 
     * @param ids 需要删除的微信配置主键
     * @return 结果
     */
    @Override
    public int deleteWxConfigByIds(Long[] ids)
    {
        return wxConfigMapper.deleteWxConfigByIds(ids);
    }

    /**
     * 删除微信配置信息
     *
     * @param id 微信配置主键
     * @return 结果
     */
    @Override
    public int deleteWxConfigById(Long id)
    {
        return wxConfigMapper.deleteWxConfigById(id);
    }

    @Override
    public WxConfig selectWxConfigByCode(String wxConfigCode){
        return wxConfigMapper.selectWxConfigByCode(wxConfigCode);
    }

    /**
     * 查询微信默认配置
     *
     * @return 微信配置
     */
    @Override
    public WxConfig selectDefaultWxConfig(){
        List<WxConfig> configList=wxConfigMapper.selectDefaultWxConfig();
        if(CollectionUtils.isEmpty(configList)) return null;
        return configList.get(0);
    }

}
