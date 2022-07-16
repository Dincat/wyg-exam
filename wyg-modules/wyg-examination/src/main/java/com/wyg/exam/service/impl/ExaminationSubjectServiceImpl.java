package com.wyg.exam.service.impl;

import java.util.List;

import com.wyg.exam.domain.vo.SubjectVO;
import com.wyg.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.exam.mapper.ExaminationSubjectMapper;
import com.wyg.exam.domain.ExaminationSubject;
import com.wyg.exam.service.IExaminationSubjectService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 考试题目Service业务层处理
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
@Service
public class ExaminationSubjectServiceImpl implements IExaminationSubjectService 
{
    @Autowired
    private ExaminationSubjectMapper examinationSubjectMapper;

    /**
     * 查询考试题目
     * 
     * @param id 考试题目主键
     * @return 考试题目
     */
    @Override
    public ExaminationSubject selectExaminationSubjectById(Long id)
    {
        return examinationSubjectMapper.selectExaminationSubjectById(id);
    }

    /**
     * 查询考试题目列表
     * 
     * @param examinationSubject 考试题目
     * @return 考试题目
     */
    @Override
    public List<ExaminationSubject> selectExaminationSubjectList(ExaminationSubject examinationSubject)
    {
        List<ExaminationSubject> examinationSubjectList=  examinationSubjectMapper.selectExaminationSubjectList(examinationSubject);
        return examinationSubjectList;
    }

    /**
     * 新增考试题目
     * 
     * @param examinationSubject 考试题目
     * @return 结果
     */
    @Override
    public int insertExaminationSubject(ExaminationSubject examinationSubject)
    {
        examinationSubject.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(),SecurityUtils.getTenantCode());
        return examinationSubjectMapper.insertExaminationSubject(examinationSubject);
    }

    /**
     * 修改考试题目
     * 
     * @param examinationSubject 考试题目
     * @return 结果
     */
    @Override
    public int updateExaminationSubject(ExaminationSubject examinationSubject)
    {
        examinationSubject.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(),SecurityUtils.getTenantCode());
        return examinationSubjectMapper.updateExaminationSubject(examinationSubject);
    }

    /**
     * 批量删除考试题目
     * 
     * @param ids 需要删除的考试题目主键
     * @return 结果
     */
    @Override
    public int deleteExaminationSubjectByIds(Long[] ids)
    {
        return examinationSubjectMapper.deleteExaminationSubjectByIds(ids);
    }

    /**
     * 删除考试题目信息
     *
     * @param id 考试题目主键
     * @return 结果
     */
    @Override
    public int deleteExaminationSubjectById(Long id)
    {
        return examinationSubjectMapper.deleteExaminationSubjectById(id);
    }

    /**
     * 根据上一题ID查询下一题
     *
     * @param examinationSubject examinationSubject
     * @return ExaminationSubject
     * @author tangyi
     * @date 2019/10/07 20:59:43
     */
    @Override
    public ExaminationSubject getByPreviousId(ExaminationSubject examinationSubject) {
        return examinationSubjectMapper.getByPreviousId(examinationSubject);
    }

    /**
     * 根据当前题目ID查询上一题
     *
     * @param examinationSubject examinationSubject
     * @return ExaminationSubject
     * @author tangyi
     * @date 2019/10/07 20:59:43
     */
    @Override
    public ExaminationSubject getPreviousByCurrentId(ExaminationSubject examinationSubject) {
        return examinationSubjectMapper.getPreviousByCurrentId(examinationSubject);
    }

    /**
     * 根据考试ID和题目序号查询
     *
     * @param examinationSubject examinationSubject
     * @return ExaminationSubject
     * @author tangyi
     * @date 2019/06/18 23:17
     */
    @Override
   public ExaminationSubject findByExaminationIdAndSubjectId(ExaminationSubject examinationSubject)
    {
        return examinationSubjectMapper.findByExaminationIdAndSubjectId(examinationSubject);
    }

    /**
     * 根据题目ID删除
     *
     * @param examinationSubject examinationSubject
     * @return int
     * @author tangyi
     * @date 2019/06/16 21:56
     */
    @Transactional
    public int deleteBySubjectId(ExaminationSubject examinationSubject) {
        return examinationSubjectMapper.deleteBySubjectId(examinationSubject);
    }

    public int findSubjectCount(ExaminationSubject examinationSubject){
        return examinationSubjectMapper.findSubjectCount(examinationSubject);
    }

    @Override
    @Transactional
    public int updateAnswerResult(SubjectVO subjectVO) {
        return examinationSubjectMapper.updateAnswerResult(subjectVO);
    }

}
