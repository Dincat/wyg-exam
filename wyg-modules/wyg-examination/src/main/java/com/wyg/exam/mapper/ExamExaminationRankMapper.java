package com.wyg.exam.mapper;

import java.util.List;
import com.wyg.exam.domain.ExamExaminationRank;

/**
 * 考试成绩排行Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-05-25
 */
public interface ExamExaminationRankMapper 
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
     * 删除考试成绩排行
     * 
     * @param id 考试成绩排行主键
     * @return 结果
     */
    public int deleteExamExaminationRankById(Long id);

    /**
     * 批量删除考试成绩排行
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExamExaminationRankByIds(Long[] ids);

    public List<ExamExaminationRank> selectRankList(ExamExaminationRank examExaminationRank);

}
