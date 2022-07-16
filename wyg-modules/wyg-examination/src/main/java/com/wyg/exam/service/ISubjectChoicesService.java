package com.wyg.exam.service;

import java.util.List;
import com.wyg.exam.domain.SubjectChoices;

/**
 * 选择题Service接口
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
public interface ISubjectChoicesService extends ISubjectService
{
    /**
     * 查询选择题
     * 
     * @param id 选择题主键
     * @return 选择题
     */
    public SubjectChoices selectSubjectChoicesById(Long id);

    /**
     * 查询选择题列表
     * 
     * @param subjectChoices 选择题
     * @return 选择题集合
     */
    public List<SubjectChoices> selectSubjectChoicesList(SubjectChoices subjectChoices);

    /**
     * 新增选择题
     * 
     * @param subjectChoices 选择题
     * @return 结果
     */
    public int insertSubjectChoices(SubjectChoices subjectChoices);

    /**
     * 修改选择题
     * 
     * @param subjectChoices 选择题
     * @return 结果
     */
    public int updateSubjectChoices(SubjectChoices subjectChoices);

    /**
     * 批量删除选择题
     * 
     * @param ids 需要删除的选择题主键集合
     * @return 结果
     */
    public int deleteSubjectChoicesByIds(Long[] ids);

    /**
     * 删除选择题信息
     * 
     * @param id 选择题主键
     * @return 结果
     */
    public int deleteSubjectChoicesById(Long id);
}
