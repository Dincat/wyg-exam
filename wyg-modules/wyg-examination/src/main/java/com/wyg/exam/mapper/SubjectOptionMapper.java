package com.wyg.exam.mapper;

import java.util.List;
import com.wyg.exam.domain.SubjectOption;

/**
 * 选择题选项Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
public interface SubjectOptionMapper 
{
    /**
     * 查询选择题选项
     * 
     * @param id 选择题选项主键
     * @return 选择题选项
     */
    public SubjectOption selectSubjectOptionById(Long id);

    /**
     * 查询选择题选项列表
     * 
     * @param subjectOption 选择题选项
     * @return 选择题选项集合
     */
    public List<SubjectOption> selectSubjectOptionList(SubjectOption subjectOption);

    /**
     * 新增选择题选项
     * 
     * @param subjectOption 选择题选项
     * @return 结果
     */
    public int insertSubjectOption(SubjectOption subjectOption);

    /**
     * 修改选择题选项
     * 
     * @param subjectOption 选择题选项
     * @return 结果
     */
    public int updateSubjectOption(SubjectOption subjectOption);

    /**
     * 删除选择题选项
     * 
     * @param id 选择题选项主键
     * @return 结果
     */
    public int deleteSubjectOptionById(Long id);

    /**
     * 批量删除选择题选项
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSubjectOptionByIds(Long[] ids);


    /**
     * 根据选择题ID删除
     *
     * @param subjectOption subjectOption
     * @return int
     * @author tangyi
     * @date 2019/06/16 21:54
     */
    int deleteBySubjectChoicesId(SubjectOption subjectOption);

    int insertBatch(List<SubjectOption> subjectOptionList);
}
