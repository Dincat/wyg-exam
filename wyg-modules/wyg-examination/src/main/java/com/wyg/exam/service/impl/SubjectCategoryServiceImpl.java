package com.wyg.exam.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.wyg.exam.domain.vo.SubjectCategoryVO;
import com.wyg.exam.utils.TreeUtils;
import com.wyg.common.core.constant.Constants;
import com.wyg.common.core.constant.UserConstants;
import com.wyg.common.core.utils.StringUtils;
import com.wyg.common.redis.service.RedisService;
import com.wyg.common.security.utils.SecurityUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.exam.mapper.SubjectCategoryMapper;
import com.wyg.exam.domain.SubjectCategory;
import com.wyg.exam.service.ISubjectCategoryService;

/**
 * 题目分类Service业务层处理
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
@Service
public class SubjectCategoryServiceImpl implements ISubjectCategoryService 
{
    @Autowired
    private SubjectCategoryMapper subjectCategoryMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 查询题目分类
     * 
     * @param id 题目分类主键
     * @return 题目分类
     */
    @Override
    public SubjectCategory selectSubjectCategoryById(Long id)
    {
        return subjectCategoryMapper.selectSubjectCategoryById(id);
    }

    /**
     * 查询题目分类列表
     * 
     * @param subjectCategory 题目分类
     * @return 题目分类
     */
    @Override
    public List<SubjectCategory> selectSubjectCategoryList(SubjectCategory subjectCategory)
    {
        return subjectCategoryMapper.selectSubjectCategoryList(subjectCategory);
    }

    /**
     * 新增题目分类
     * 
     * @param subjectCategory 题目分类
     * @return 结果
     */
    @Override
    public int insertSubjectCategory(SubjectCategory subjectCategory)
    {
        subjectCategory.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(),SecurityUtils.getTenantCode());
        return subjectCategoryMapper.insertSubjectCategory(subjectCategory);
    }

    /**
     * 修改题目分类
     * 
     * @param subjectCategory 题目分类
     * @return 结果
     */
    @Override
    public int updateSubjectCategory(SubjectCategory subjectCategory)
    {
        subjectCategory.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(),SecurityUtils.getTenantCode());
        return subjectCategoryMapper.updateSubjectCategory(subjectCategory);
    }

    /**
     * 批量删除题目分类
     * 
     * @param ids 需要删除的题目分类主键
     * @return 结果
     */
    @Override
    public int deleteSubjectCategoryByIds(Long[] ids)
    {
        return subjectCategoryMapper.deleteSubjectCategoryByIds(ids);
    }

    /**
     * 删除题目分类信息
     *
     * @param id 题目分类主键
     * @return 结果
     */
    @Override
    public int deleteSubjectCategoryById(Long id)
    {
        return subjectCategoryMapper.deleteSubjectCategoryById(id);
    }


    /**
     * 返回树形分类集合
     *
     * @return List
     * @author tangyi
     * @date 2018/12/04 22:03
     */
    public List<SubjectCategoryVO> menus() {
        SubjectCategory subjectCategory = new SubjectCategory();
        //subjectCategory.setTenantCode(SecurityUtils.getTenantCode());
        // 查询所有分类
        List<SubjectCategory> subjectCategoryList = selectSubjectCategoryList(subjectCategory);
        if (CollectionUtils.isNotEmpty(subjectCategoryList)) {
            // 转成dto
            List<SubjectCategoryVO> subjectCategorySetTreeList = subjectCategoryList.stream().map(SubjectCategoryVO::new).distinct().collect(
                    Collectors.toList());
            // 排序、组装树形结构
            List<SubjectCategoryVO> result=  TreeUtils.buildTree(CollUtil.sort(subjectCategorySetTreeList, Comparator.comparingLong(SubjectCategoryVO::getSort)), -1L);
            return result;
        }
        return new ArrayList<>();
    }


    /**
     * 返回树形分类集合
     *
     * @return List
     * @author tangyi
     * @date 2018/12/04 22:03
     */
    public List<SubjectCategoryVO> menus(String status) {
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setStatus(status);
        //subjectCategory.setTenantCode(SecurityUtils.getTenantCode());
        // 查询所有分类
        List<SubjectCategory> subjectCategoryList = selectSubjectCategoryList(subjectCategory);
        if (CollectionUtils.isNotEmpty(subjectCategoryList)) {
            // 转成dto
            List<SubjectCategoryVO> subjectCategorySetTreeList = subjectCategoryList.stream().map(SubjectCategoryVO::new).distinct().collect(
                    Collectors.toList());
            // 排序、组装树形结构
            List<SubjectCategoryVO> result=  TreeUtils.buildTree(CollUtil.sort(subjectCategorySetTreeList, Comparator.comparingLong(SubjectCategoryVO::getSort)), -1L);
            return result;
        }
        return new ArrayList<>();
    }




}
