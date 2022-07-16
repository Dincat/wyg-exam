package com.wyg.exam.mapper;

import java.util.List;
import com.wyg.exam.domain.ExaminationSubject;
import com.wyg.exam.domain.vo.SubjectVO;

/**
 * 考试题目Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
public interface ExaminationSubjectMapper 
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
     * 删除考试题目
     * 
     * @param id 考试题目主键
     * @return 结果
     */
    public int deleteExaminationSubjectById(Long id);

    /**
     * 批量删除考试题目
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExaminationSubjectByIds(Long[] ids);

    public List<ExaminationSubject> findList(ExaminationSubject examinationSubject);

    /**
     * 根据上一题ID查询下一题
     *
     * @param examinationSubject examinationSubject
     * @return ExaminationSubject
     * @author tangyi
     * @date 2019-09-14 16:41
     */
    ExaminationSubject getByPreviousId(ExaminationSubject examinationSubject);

    /**
     * 根据当前题目ID查询上一题
     *
     * @param examinationSubject examinationSubject
     * @return ExaminationSubject
     * @author tangyi
     * @date 2019/10/07 20:40:16
     */
    ExaminationSubject getPreviousByCurrentId(ExaminationSubject examinationSubject);

    /**
     * 根据考试ID和题目序号查询
     *
     * @param examinationSubject examinationSubject
     * @return ExaminationSubject
     * @author tangyi
     * @date 2019/06/18 23:17
     */
    ExaminationSubject findByExaminationIdAndSubjectId(ExaminationSubject examinationSubject);

    int deleteBySubjectId(ExaminationSubject examinationSubject);

    int findSubjectCount(ExaminationSubject examinationSubject);

    /**
     * 更新作答结果（正确数、错误数）
     * @param subjectVO
     * @return
     */
    public int updateAnswerResult(SubjectVO subjectVO);
}
