package com.wyg.exam.mapper;

import java.util.List;
import com.wyg.exam.domain.Answer;
import feign.Param;

/**
 * 答题Mapper接口
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
public interface AnswerMapper 
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
     * 删除答题
     * 
     * @param id 答题主键
     * @return 结果
     */
    public int deleteAnswerById(Long id);

    /**
     * 批量删除答题
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAnswerByIds(Long[] ids);


    /**
     * 根据考试记录查询最后一道答题
     *
     * @param id 答题主键
     * @return 答题
     */
    public Answer selectLastByRecordId(@Param("recordId") Long recordId);
}
