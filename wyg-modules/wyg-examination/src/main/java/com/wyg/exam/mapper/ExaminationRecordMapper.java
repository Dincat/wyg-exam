package com.wyg.exam.mapper;

import java.util.Date;
import java.util.List;
import com.wyg.exam.domain.ExaminationRecord;

/**
 * 考试记录Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
public interface ExaminationRecordMapper 
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
     * 删除考试记录
     * 
     * @param id 考试记录主键
     * @return 结果
     */
    public int deleteExaminationRecordById(Long id);

    /**
     * 批量删除考试记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExaminationRecordByIds(Long[] ids);

    Integer findExaminationRecordCount(ExaminationRecord examinationRecord);

    /**
     * 时间范围条件查询
     * @param start start
     * @return List
     * @author tangyi
     * @date 2020/2/1 11:57 上午
     */
    List<ExaminationRecord> findExaminationRecordCountByDate(Date start);
}
