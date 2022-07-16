package com.wyg.exam.service;

import java.util.List;
import com.wyg.exam.domain.SubjectShortAnswer;

/**
 * 简答题Service接口
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
public interface ISubjectShortAnswerService extends ISubjectService
{
    /**
     * 查询简答题
     * 
     * @param id 简答题主键
     * @return 简答题
     */
    public SubjectShortAnswer selectSubjectShortAnswerById(Long id);

    /**
     * 查询简答题列表
     * 
     * @param subjectShortAnswer 简答题
     * @return 简答题集合
     */
    public List<SubjectShortAnswer> selectSubjectShortAnswerList(SubjectShortAnswer subjectShortAnswer);

    /**
     * 新增简答题
     * 
     * @param subjectShortAnswer 简答题
     * @return 结果
     */
    public int insertSubjectShortAnswer(SubjectShortAnswer subjectShortAnswer);

    /**
     * 修改简答题
     * 
     * @param subjectShortAnswer 简答题
     * @return 结果
     */
    public int updateSubjectShortAnswer(SubjectShortAnswer subjectShortAnswer);

    /**
     * 批量删除简答题
     * 
     * @param ids 需要删除的简答题主键集合
     * @return 结果
     */
    public int deleteSubjectShortAnswerByIds(Long[] ids);

    /**
     * 删除简答题信息
     * 
     * @param id 简答题主键
     * @return 结果
     */
    public int deleteSubjectShortAnswerById(Long id);
}
