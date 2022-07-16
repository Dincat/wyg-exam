package com.wyg.exam.service;

import java.util.List;
import com.wyg.exam.domain.ExaminationRecord;
import com.wyg.exam.domain.vo.ExaminationDashboardVO;
import com.wyg.exam.domain.vo.ExaminationRecordVO;

/**
 * 考试记录Service接口
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
public interface IExaminationRecordService 
{
    /**
     * 查询考试记录
     * 
     * @param id 考试记录主键
     * @return 考试记录
     */
    public ExaminationRecord selectExaminationRecordById(Long id);

    /**
     * 查询考试记录列表
     *
     * @param examinationRecord 考试记录
     * @return 考试记录集合
     */
    public List<ExaminationRecord> selectExaminationRecordList(ExaminationRecord examinationRecord);

    /**
     * 查询考试记录列表
     * 
     * @param examinationRecord 考试记录
     * @return 考试记录集合
     */
    public List<ExaminationRecordVO> selectExaminationRecordVOList(ExaminationRecord examinationRecord);

    /**
     * 新增考试记录
     * 
     * @param examinationRecord 考试记录
     * @return 结果
     */
    public int insertExaminationRecord(ExaminationRecord examinationRecord);

    /**
     * 修改考试记录
     * 
     * @param examinationRecord 考试记录
     * @return 结果
     */
    public int updateExaminationRecord(ExaminationRecord examinationRecord);

    /**
     * 批量删除考试记录
     * 
     * @param ids 需要删除的考试记录主键集合
     * @return 结果
     */
    public int deleteExaminationRecordByIds(Long[] ids);

    /**
     * 删除考试记录信息
     * 
     * @param id 考试记录主键
     * @return 结果
     */
    public int deleteExaminationRecordById(Long id);

    ExaminationRecordVO details(Long id);

    ExaminationDashboardVO findExamDashboardData(String tenantCode);

    ExaminationDashboardVO findExamRecordTendency(String tenantCode, Integer pastDays);
}
