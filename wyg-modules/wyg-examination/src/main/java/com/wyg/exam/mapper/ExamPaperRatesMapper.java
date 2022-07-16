package com.wyg.exam.mapper;

import java.util.List;
import com.wyg.exam.domain.ExamPaperRates;

/**
 * 试卷评价Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-05-30
 */
public interface ExamPaperRatesMapper 
{
    /**
     * 查询试卷评价
     * 
     * @param id 试卷评价主键
     * @return 试卷评价
     */
    public ExamPaperRates selectExamPaperRatesById(Long id);

    /**
     * 查询试卷评价列表
     * 
     * @param examPaperRates 试卷评价
     * @return 试卷评价集合
     */
    public List<ExamPaperRates> selectExamPaperRatesList(ExamPaperRates examPaperRates);

    /**
     * 新增试卷评价
     * 
     * @param examPaperRates 试卷评价
     * @return 结果
     */
    public int insertExamPaperRates(ExamPaperRates examPaperRates);

    /**
     * 修改试卷评价
     * 
     * @param examPaperRates 试卷评价
     * @return 结果
     */
    public int updateExamPaperRates(ExamPaperRates examPaperRates);

    /**
     * 删除试卷评价
     * 
     * @param id 试卷评价主键
     * @return 结果
     */
    public int deleteExamPaperRatesById(Long id);

    /**
     * 批量删除试卷评价
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExamPaperRatesByIds(Long[] ids);


    /**
     * 计算某试卷的评价分平均值
     * @param paperId
     * @return
     */
    public double selectRatingAvgByPaperId(Long paperId);

}
