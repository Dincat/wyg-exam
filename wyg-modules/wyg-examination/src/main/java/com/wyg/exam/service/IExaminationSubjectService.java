package com.wyg.exam.service;

import java.util.List;
import com.wyg.exam.domain.ExaminationSubject;
import com.wyg.exam.domain.vo.SubjectVO;

/**
 * 考试题目Service接口
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
public interface IExaminationSubjectService 
{
    /**
     * 查询考试题目
     * 
     * @param id 考试题目主键
     * @return 考试题目
     */
    public ExaminationSubject selectExaminationSubjectById(Long id);

    /**
     * 查询考试题目列表
     * 
     * @param examinationSubject 考试题目
     * @return 考试题目集合
     */
    public List<ExaminationSubject> selectExaminationSubjectList(ExaminationSubject examinationSubject);

    /**
     * 新增考试题目
     * 
     * @param examinationSubject 考试题目
     * @return 结果
     */
    public int insertExaminationSubject(ExaminationSubject examinationSubject);

    /**
     * 修改考试题目
     * 
     * @param examinationSubject 考试题目
     * @return 结果
     */
    public int updateExaminationSubject(ExaminationSubject examinationSubject);

    /**
     * 批量删除考试题目
     * 
     * @param ids 需要删除的考试题目主键集合
     * @return 结果
     */
    public int deleteExaminationSubjectByIds(Long[] ids);

    /**
     * 删除考试题目信息
     * 
     * @param id 考试题目主键
     * @return 结果
     */
    public int deleteExaminationSubjectById(Long id);

    public ExaminationSubject getByPreviousId(ExaminationSubject examinationSubject);

    public ExaminationSubject getPreviousByCurrentId(ExaminationSubject examinationSubject);

    public ExaminationSubject findByExaminationIdAndSubjectId(ExaminationSubject examinationSubject);

    int deleteBySubjectId(ExaminationSubject examinationSubject);

    int findSubjectCount(ExaminationSubject examinationSubject);

    public int updateAnswerResult(SubjectVO subjectVO);
}
