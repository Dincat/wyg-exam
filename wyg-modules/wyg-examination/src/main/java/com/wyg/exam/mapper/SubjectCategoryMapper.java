package com.wyg.exam.mapper;

import java.util.List;
import com.wyg.exam.domain.SubjectCategory;

/**
 * 题目分类Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
public interface SubjectCategoryMapper 
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
     * 删除题目分类
     * 
     * @param id 题目分类主键
     * @return 结果
     */
    public int deleteSubjectCategoryById(Long id);

    /**
     * 批量删除题目分类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSubjectCategoryByIds(Long[] ids);
}
