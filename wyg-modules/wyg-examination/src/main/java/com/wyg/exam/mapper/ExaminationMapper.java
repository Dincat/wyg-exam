package com.wyg.exam.mapper;

import java.util.List;
import com.wyg.exam.domain.Examination;

/**
 * 考试信息Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
public interface ExaminationMapper 
{
    /**
     * 查询考试信息
     * 
     * @param id 考试信息主键
     * @return 考试信息
     */
    public Examination selectExaminationById(Long id);

    /**
     * 查询考试信息列表
     * 
     * @param examination 考试信息
     * @return 考试信息集合
     */
    public List<Examination> selectExaminationList(Examination examination);

    /**
     * 新增考试信息
     * 
     * @param examination 考试信息
     * @return 结果
     */
    public int insertExamination(Examination examination);

    /**
     * 修改考试信息
     * 
     * @param examination 考试信息
     * @return 结果
     */
    public int updateExamination(Examination examination);

    /**
     * 删除考试信息
     * 
     * @param id 考试信息主键
     * @return 结果
     */
    public int deleteExaminationById(Long id);

    /**
     * 批量删除考试信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExaminationByIds(Long[] ids);

    List<Examination> getListByIds(Long[] ids);

    Integer findExaminationCount(Examination examination);

    Integer findExamUserCount(Examination examination);

    public int updateRate(Examination examination);
}
