package com.wyg.exam.service;

import java.util.List;
import com.wyg.exam.domain.Answer;
import com.wyg.exam.domain.ExaminationRecord;
import com.wyg.exam.domain.vo.AnswerVO;
import com.wyg.exam.domain.vo.RankInfoVO;
import com.wyg.exam.domain.vo.StartExamVO;
import com.wyg.exam.domain.vo.SubjectVO;

/**
 * 答题Service接口
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
public interface IAnswerService 
{
    /**
     * 查询答题
     * 
     * @param id 答题主键
     * @return 答题
     */
    public Answer selectAnswerById(Long id);

    /**
     * 查询答题列表
     * 
     * @param answer 答题
     * @return 答题集合
     */
    public List<Answer> selectAnswerList(Answer answer);

    /**
     * 新增答题
     * 
     * @param answer 答题
     * @return 结果
     */
    public int insertAnswer(Answer answer);

    /**
     * 修改答题
     * 
     * @param answer 答题
     * @return 结果
     */
    public int updateAnswer(Answer answer);

    /**
     * 批量删除答题
     * 
     * @param ids 需要删除的答题主键集合
     * @return 结果
     */
    public int deleteAnswerByIds(Long[] ids);

    /**
     * 删除答题信息
     * 
     * @param id 答题主键
     * @return 结果
     */
    public int deleteAnswerById(Long id);

    AnswerVO answerInfo(Long recordId, Long currentSubjectId, Integer nextSubjectType, Integer nextType);

    int updateScore(Answer answer);

    Boolean completeMarking(ExaminationRecord examRecord);

    StartExamVO start(ExaminationRecord examRecord);

    SubjectVO subjectAnswer(AnswerVO answerVO);

    SubjectVO saveAndNext(AnswerVO answer, Integer nextType, Long nextSubjectId, Integer nextSubjectType);

    boolean submitAsync(Answer answer);

    void submit(Answer answer);

    List<AnswerVO> toAnswerVO(List<Answer> answerList);

    List<AnswerVO> toAnswerVO(List<SubjectVO> subjectVOList,List<Answer> answerList);

    List<RankInfoVO> getRankInfo(Long recordId);

    /**
     * 根据考试记录查询最后一道答题
     *
     * @param id 答题主键
     * @return 答题
     */
    public Answer selectLastByRecordId(Long recordId);
}
