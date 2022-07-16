package com.wyg.exam.service.impl;

import java.util.List;

import com.wyg.exam.domain.ExaminationSubject;
import com.wyg.exam.domain.SubjectChoices;
import com.wyg.exam.domain.base.BaseService;
import com.wyg.exam.domain.vo.SubjectVO;
import com.wyg.exam.mapper.SubjectChoicesMapper;
import com.wyg.exam.utils.SubjectUtils;
import com.wyg.common.security.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.exam.mapper.SubjectJudgementMapper;
import com.wyg.exam.domain.SubjectJudgement;
import com.wyg.exam.service.ISubjectJudgementService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 简答题Service业务层处理
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
@Service
@AllArgsConstructor
public class SubjectJudgementServiceImpl extends BaseService<SubjectJudgementMapper, SubjectJudgement> implements ISubjectJudgementService
{
    @Autowired
    private SubjectJudgementMapper subjectJudgementMapper;

    /**
     * 查询简答题
     * 
     * @param id 简答题主键
     * @return 简答题
     */
    @Override
    public SubjectJudgement selectSubjectJudgementById(Long id)
    {
        return subjectJudgementMapper.selectSubjectJudgementById(id);
    }

    /**
     * 查询简答题列表
     * 
     * @param subjectJudgement 简答题
     * @return 简答题
     */
    @Override
    public List<SubjectJudgement> selectSubjectJudgementList(SubjectJudgement subjectJudgement)
    {
        return subjectJudgementMapper.selectSubjectJudgementList(subjectJudgement);
    }

    /**
     * 新增简答题
     * 
     * @param subjectJudgement 简答题
     * @return 结果
     */
    @Override
    public int insertSubjectJudgement(SubjectJudgement subjectJudgement)
    {
        return subjectJudgementMapper.insertSubjectJudgement(subjectJudgement);
    }

    /**
     * 修改简答题
     * 
     * @param subjectJudgement 简答题
     * @return 结果
     */
    @Override
    public int updateSubjectJudgement(SubjectJudgement subjectJudgement)
    {
        subjectJudgement.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(),SecurityUtils.getTenantCode());
        return subjectJudgementMapper.updateSubjectJudgement(subjectJudgement);
    }

    /**
     * 批量删除简答题
     * 
     * @param ids 需要删除的简答题主键
     * @return 结果
     */
    @Override
    public int deleteSubjectJudgementByIds(Long[] ids)
    {
        return subjectJudgementMapper.deleteSubjectJudgementByIds(ids);
    }

    /**
     * 删除简答题信息
     *
     * @param id 简答题主键
     * @return 结果
     */
    @Override
    public int deleteSubjectJudgementById(Long id)
    {
        return subjectJudgementMapper.deleteSubjectJudgementById(id);
    }

    @Override
    public List<SubjectVO> getSubjectList(List<ExaminationSubject> list) {
        return null;
    }

    @Override
    public SubjectVO getSubject(Long subjectId) {
        SubjectJudgement subjectJudgement=this.selectSubjectJudgementById(subjectId);
        return SubjectUtils.subjectJudgementToVO(subjectJudgement);
    }

    @Override
    public int updateSubject(SubjectVO subject) {
        SubjectJudgement subjectJudgement = new SubjectJudgement();
        BeanUtils.copyProperties(subject, subjectJudgement);
        subjectJudgement.setAnswer(subject.getAnswer().getAnswer());
        return this.updateSubjectJudgement(subjectJudgement);
    }

    @Override
    public int insertSubject(SubjectVO subjectVO) {
        SubjectJudgement subjectJudgement = new SubjectJudgement();
        BeanUtils.copyProperties(subjectVO, subjectJudgement);
        subjectJudgement.setId(subjectVO.getId());
        subjectJudgement.setScore(subjectVO.getScore());
        subjectJudgement.setExaminationId(subjectVO.getExaminationId());
        subjectJudgement.setAnswer(subjectVO.getAnswer().getAnswer());
        return this.insertSubjectJudgement(subjectJudgement);
    }

    /**
     * 根据ID批量查询
     *
     * @param ids ids
     * @return List
     * @author tangyi
     * @date 2019/06/16 18:16
     */
    @Override
    public List<SubjectVO> findSubjectListById(Long[] ids) {
        return SubjectUtils.subjectJudgementToVO(this.findListByIds(ids), true);
    }

    @Override
    public int physicalDeleteSubject(Long id) {
        return subjectJudgementMapper.physicalDelete(id);
    }

    @Override
    public int physicalDeleteAllSubject(Long[] ids) {
        return subjectJudgementMapper.physicalDeleteAll(ids);
    }

    @Override
    public SubjectVO getNextByCurrentIdAndType(Long examinationId, Long previousSubjectId, Integer nextType) {
        return null;
    }


    @Override
    @Transactional
    public int updateAnswerResult(SubjectVO subjectVO) {
        return subjectJudgementMapper.updateAnswerResult(subjectVO);
    }
}
