package com.wyg.exam.service;

import java.util.List;

import com.wyg.exam.domain.vo.SubjectCategoryVO;
import com.wyg.exam.domain.SubjectCategory;

/**
 * 题目分类Service接口
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
public interface ISubjectCategoryService 
{
    /**
     * 查询题目分类
     * 
     * @param id 题目分类主键
     * @return 题目分类
     */
    public SubjectCategory selectSubjectCategoryById(Long id);

    /**
     * 查询题目分类列表
     * 
     * @param subjectCategory 题目分类
     * @return 题目分类集合
     */
    public List<SubjectCategory> selectSubjectCategoryList(SubjectCategory subjectCategory);

    /**
     * 新增题目分类
     * 
     * @param subjectCategory 题目分类
     * @return 结果
     */
    public int insertSubjectCategory(SubjectCategory subjectCategory);

    /**
     * 修改题目分类
     * 
     * @param subjectCategory 题目分类
     * @return 结果
     */
    public int updateSubjectCategory(SubjectCategory subjectCategory);

    /**
     * 批量删除题目分类
     * 
     * @param ids 需要删除的题目分类主键集合
     * @return 结果
     */
    public int deleteSubjectCategoryByIds(Long[] ids);

    /**
     * 删除题目分类信息
     * 
     * @param id 题目分类主键
     * @return 结果
     */
    public int deleteSubjectCategoryById(Long id);

    List<SubjectCategoryVO> menus();

    List<SubjectCategoryVO> menus(String status);


}
