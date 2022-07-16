package com.wyg.exam.service;

import java.util.List;
import com.wyg.exam.domain.SubjectOption;

/**
 * 选择题选项Service接口
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
public interface ISubjectOptionService 
{
    /**
     * 查询选择题选项
     * 
     * @param id 选择题选项主键
     * @return 选择题选项
     */
    public SubjectOption selectSubjectOptionById(Long id);

    /**
     * 查询选择题选项列表
     * 
     * @param subjectOption 选择题选项
     * @return 选择题选项集合
     */
    public List<SubjectOption> selectSubjectOptionList(SubjectOption subjectOption);

    /**
     * 新增选择题选项
     * 
     * @param subjectOption 选择题选项
     * @return 结果
     */
    public int insertSubjectOption(SubjectOption subjectOption);

    /**
     * 修改选择题选项
     * 
     * @param subjectOption 选择题选项
     * @return 结果
     */
    public int updateSubjectOption(SubjectOption subjectOption);

    /**
     * 批量删除选择题选项
     * 
     * @param ids 需要删除的选择题选项主键集合
     * @return 结果
     */
    public int deleteSubjectOptionByIds(Long[] ids);

    /**
     * 删除选择题选项信息
     * 
     * @param id 选择题选项主键
     * @return 结果
     */
    public int deleteSubjectOptionById(Long id);

    int deleteBySubjectChoicesId(SubjectOption subjectOption);

    int insertBatch(List<SubjectOption> options);

}
