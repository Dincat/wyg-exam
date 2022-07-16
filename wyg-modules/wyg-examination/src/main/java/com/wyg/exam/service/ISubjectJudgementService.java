package com.wyg.exam.service;

import java.util.List;
import com.wyg.exam.domain.SubjectJudgement;

/**
 * 简答题Service接口
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
public interface ISubjectJudgementService extends ISubjectService
{
    /**
     * 查询简答题
     * 
     * @param id 简答题主键
     * @return 简答题
     */
    public SubjectJudgement selectSubjectJudgementById(Long id);

    /**
     * 查询简答题列表
     * 
     * @param subjectJudgement 简答题
     * @return 简答题集合
     */
    public List<SubjectJudgement> selectSubjectJudgementList(SubjectJudgement subjectJudgement);

    /**
     * 新增简答题
     * 
     * @param subjectJudgement 简答题
     * @return 结果
     */
    public int insertSubjectJudgement(SubjectJudgement subjectJudgement);

    /**
     * 修改简答题
     * 
     * @param subjectJudgement 简答题
     * @return 结果
     */
    public int updateSubjectJudgement(SubjectJudgement subjectJudgement);

    /**
     * 批量删除简答题
     * 
     * @param ids 需要删除的简答题主键集合
     * @return 结果
     */
    public int deleteSubjectJudgementByIds(Long[] ids);

    /**
     * 删除简答题信息
     * 
     * @param id 简答题主键
     * @return 结果
     */
    public int deleteSubjectJudgementById(Long id);
}
