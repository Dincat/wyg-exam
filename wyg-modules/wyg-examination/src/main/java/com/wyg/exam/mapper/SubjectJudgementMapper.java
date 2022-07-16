package com.wyg.exam.mapper;

import java.util.List;

import com.wyg.exam.domain.SubjectChoices;
import com.wyg.exam.domain.SubjectJudgement;
import com.wyg.exam.domain.base.BaseMapper;
import com.wyg.exam.domain.vo.SubjectVO;

/**
 * 简答题Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
public interface SubjectJudgementMapper extends BaseMapper<SubjectJudgement>
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
     * 删除简答题
     * 
     * @param id 简答题主键
     * @return 结果
     */
    public int deleteSubjectJudgementById(Long id);

    /**
     * 批量删除简答题
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSubjectJudgementByIds(Long[] ids);

    /**
     * 更新作答结果（正确数、错误数）
     * @param subjectVO
     * @return
     */
    public int updateAnswerResult(SubjectVO subjectVO);
}
