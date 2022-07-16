package com.wyg.file.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wyg.common.core.utils.SnowflakeIdUtils;
import com.wyg.common.security.utils.SecurityUtils;
import com.wyg.file.api.domain.SysMaterial;
import com.wyg.file.api.domain.SysSearch;
import com.wyg.file.mapper.SysMaterialMapper;
import com.wyg.file.service.ISysMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.List;

/**
 * 素材信息 业务层处理
 *
 * @author xueyi
 */
@Service
@DS("#isolate")
public class SysMaterialServiceImpl implements ISysMaterialService {

    @Autowired
    private SysMaterialMapper materialMapper;

    /**
     * 查询所有素材信息集合
     *
     * @return 素材信息集合
     */
    @Override
    public List<SysMaterial> selectList() {
        SysSearch search = new SysSearch();
        return materialMapper.selectList(search);
    }

    /**
     * 查询素材信息列表
     *
     * @param material 素材信息
     * @return 素材信息
     */
    @Override
    public List<SysMaterial> selectMaterialList(SysMaterial material) {
        return materialMapper.selectMaterialList(material);
    }

    /**
     * 查询指定文件夹的素材信息列表
     *
     * @param folderId 素材分类Id
     * @return 素材信息
     */
    @Override
    public List<SysMaterial> selectMaterialListByFolderId(Long folderId) {
        SysSearch search = new SysSearch();
        search.getSearch().put("folderId", folderId);
        return materialMapper.selectMaterialListByFolderId(search);
    }

    /**
     * 查询素材信息
     *
     * @param materialId 素材信息Id
     * @return 素材信息
     */
    @Override
    public SysMaterial selectMaterialById(Long materialId) {
        SysSearch search = new SysSearch();
        search.getSearch().put("materialId", materialId);
        return materialMapper.selectMaterialById(search);
    }

    /**
     * 新增保存素材信息
     *
     * @param material 素材信息
     * @return 结果
     */
    @Override
    public int insertMaterial(SysMaterial material) {
        if(material.getMaterialId()==null || material.getMaterialId().longValue()<1) material.setMaterialId(SnowflakeIdUtils.getSnowflakeId());
        //material.setTenantId(SecurityUtils.getTenantCode());
        return materialMapper.insertMaterial(material);
    }

    /**
     * 修改素材信息
     *
     * @param material 素材信息
     * @return 结果
     */
    @Override
    public int updateMaterial(SysMaterial material) {
        return materialMapper.updateMaterial(material);
    }

    /**
     * 删除素材信息信息
     *
     * @param materialId 素材信息Id
     * @return 结果
     */
    @Override
    public int deleteMaterialById(Long materialId) {
        SysSearch search = new SysSearch();
        search.getSearch().put("materialId", materialId);
        return materialMapper.deleteMaterialById(search);
    }

    /**
     * 批量删除素材信息信息
     *
     * @param materialIds 素材信息Id集合
     * @return 结果
     */
    @Override
    public int deleteMaterialByIds(Long[] materialIds) {
        SysSearch search = new SysSearch();
        search.getSearch().put("materialIds", materialIds);
        return materialMapper.deleteMaterialByIds(search);
    }

    /**
     * 根据folderId删除素材信息
     *
     * @param folderId 素材分类Id
     * @return 结果
     */
    @Override
    public int deleteMaterialByFolderId(Long folderId) {
        SysSearch search = new SysSearch();
        search.getSearch().put("folderId", folderId);
        return materialMapper.deleteMaterialByFolderId(search);
    }

    /**
     * 根据folderId集合批量删除素材信息
     *
     * @param folderIds 素材分类Id集合
     * @return 结果
     */
    @Override
    public int deleteMaterialByFolderIds(Long[] folderIds) {
        SysSearch search = new SysSearch();
        search.getSearch().put("folderIds", folderIds);
        return materialMapper.deleteMaterialByFolderIds(search);
    }
}
