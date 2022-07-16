package com.wyg.wechat.mapper;

import java.util.List;
import com.wyg.wechat.domain.WxConfig;

/**
 * 微信配置Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-07-02
 */
public interface WxConfigMapper 
{
    /**
     * 查询微信配置
     * 
     * @param id 微信配置主键
     * @return 微信配置
     */
    public WxConfig selectWxConfigById(Long id);

    /**
     * 查询微信配置列表
     * 
     * @param wxConfig 微信配置
     * @return 微信配置集合
     */
    public List<WxConfig> selectWxConfigList(WxConfig wxConfig);

    /**
     * 新增微信配置
     * 
     * @param wxConfig 微信配置
     * @return 结果
     */
    public int insertWxConfig(WxConfig wxConfig);

    /**
     * 修改微信配置
     * 
     * @param wxConfig 微信配置
     * @return 结果
     */
    public int updateWxConfig(WxConfig wxConfig);

    /**
     * 删除微信配置
     * 
     * @param id 微信配置主键
     * @return 结果
     */
    public int deleteWxConfigById(Long id);

    /**
     * 批量删除微信配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxConfigByIds(Long[] ids);

    /**
     * 查询微信配置
     *
     * @param id 微信配置主键
     * @return 微信配置
     */
    public WxConfig selectWxConfigByCode(String wxCode);

    /**
     * 查询默认微信配置
     *
     * @return 微信配置
     */
    public List<WxConfig> selectDefaultWxConfig();

}
