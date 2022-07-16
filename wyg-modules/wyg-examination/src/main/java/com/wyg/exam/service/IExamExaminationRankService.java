package com.wyg.exam.service;

import java.util.List;
import com.wyg.exam.domain.ExamExaminationRank;
import com.wyg.exam.domain.ExaminationRecord;

/**
 * 考试成绩排行Service接口
 * 
 * @author WorrilessGo
 * @date 2022-05-25
 */
public interface IExamExaminationRankService 
{
    /**
     * 查询考试成绩排行
     * 
     * @param id 考试成绩排行主键
     * @return 考试成绩排行
     */
    public ExamExaminationRank selectExamExaminationRankById(Long id);

    /**
     * 查询考试成绩排行列表
     * 
     * @param examExaminationRank 考试成绩排行
     * @return 考试成绩排行集合
     */
    public List<ExamExaminationRank> selectExamExaminationRankList(ExamExaminationRank examExaminationRank);

    /**
     * 新增考试成绩排行
     * 
     * @param examExaminationRank 考试成绩排行
     * @return 结果
     */
    public int insertExamExaminationRank(ExamExaminationRank examExaminationRank);

    /**
     * 修改考试成绩排行
     * 
     * @param examExaminationRank 考试成绩排行
     * @return 结果
     */
    public int updateExamExaminationRank(ExamExaminationRank examExaminationRank);

    /**
     * 批量删除考试成绩排行
     * 
     * @param ids 需要删除的考试成绩排行主键集合
     * @return 结果
     */
    public int deleteExamExaminationRankByIds(Long[] ids);

    /**
     * 删除考试成绩排行信息
     * 
     * @param id 考试成绩排行主键
     * @return 结果
     */
    public int deleteExamExaminationRankById(Long id);


    /**
     * 更新考试成绩排行
     *
     * @param examinationRecord 考试记录
     * @return 结果
     */
    public int updateRank(ExaminationRecord examinationRecord);

    public List<ExamExaminationRank> selectRankList(ExamExaminationRank examExaminationRank);

}
