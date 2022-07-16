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
import com.wyg.exam.mapper.SubjectShortAnswerMapper;
import com.wyg.exam.domain.SubjectShortAnswer;
import com.wyg.exam.service.ISubjectShortAnswerService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 简答题Service业务层处理
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
@Service
public class SubjectShortAnswerServiceImpl extends BaseService<SubjectShortAnswerMapper, SubjectShortAnswer> implements ISubjectShortAnswerService
{
    @Autowired
    private SubjectShortAnswerMapper subjectShortAnswerMapper;

    /**
     * 查询简答题
     * 
     * @param id 简答题主键
     * @return 简答题
     */
    @Override
    public SubjectShortAnswer selectSubjectShortAnswerById(Long id)
    {
        return subjectShortAnswerMapper.selectSubjectShortAnswerById(id);
    }

    /**
     * 查询简答题列表
     * 
     * @param subjectShortAnswer 简答题
     * @return 简答题
     */
    @Override
    public List<SubjectShortAnswer> selectSubjectShortAnswerList(SubjectShortAnswer subjectShortAnswer)
    {
        return subjectShortAnswerMapper.selectSubjectShortAnswerList(subjectShortAnswer);
    }

    /**
     * 新增简答题
     * 
     * @param subjectShortAnswer 简答题
     * @return 结果
     */
    @Override
    public int insertSubjectShortAnswer(SubjectShortAnswer subjectShortAnswer)
    {
        return subjectShortAnswerMapper.insertSubjectShortAnswer(subjectShortAnswer);
    }

    /**
     * 修改简答题
     * 
     * @param subjectShortAnswer 简答题
     * @return 结果
     */
    @Override
    public int updateSubjectShortAnswer(SubjectShortAnswer subjectShortAnswer)
    {
        subjectShortAnswer.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(),SecurityUtils.getTenantCode());
        return subjectShortAnswerMapper.updateSubjectShortAnswer(subjectShortAnswer);
    }

    /**
     * 批量删除简答题
     * 
     * @param ids 需要删除的简答题主键
     * @return 结果
     */
    @Override
    public int deleteSubjectShortAnswerByIds(Long[] ids)
    {
        return subjectShortAnswerMapper.deleteSubjectShortAnswerByIds(ids);
    }

    /**
     * 删除简答题信息
     *
     * @param id 简答题主键
     * @return 结果
     */
    @Override
    public int deleteSubjectShortAnswerById(Long id)
    {
        return subjectShortAnswerMapper.deleteSubjectShortAnswerById(id);
    }

    @Override
    public List<SubjectVO> getSubjectList(List<ExaminationSubject> list) {
        return null;
    }

    @Override
    public SubjectVO getSubject(Long subjectId) {
        SubjectShortAnswer subjectShortAnswer=this.selectSubjectShortAnswerById(subjectId);
        return SubjectUtils.subjectShortAnswerToDto(subjectShortAnswer, true);
    }

    @Override
    public int updateSubject(SubjectVO subject) {
        SubjectShortAnswer subjectShortAnswer = new SubjectShortAnswer();
        BeanUtils.copyProperties(subject, subjectShortAnswer);
        // 参考答案
        subjectShortAnswer.setAnswer(subject.getAnswer().getAnswer());
        return this.updateSubjectShortAnswer(subjectShortAnswer);
    }

    @Override
    public int insertSubject(SubjectVO subjectVO) {
        SubjectShortAnswer subjectShortAnswer = new SubjectShortAnswer();
        BeanUtils.copyProperties(subjectVO, subjectShortAnswer);
        subjectShortAnswer.setId(subjectVO.getId());
        subjectShortAnswer.setScore(subjectVO.getScore());
        subjectShortAnswer.setAnswer(subjectVO.getAnswer().getAnswer());
        subjectShortAnswer.setExaminationId(subjectVO.getExaminationId());
        return this.insertSubjectShortAnswer(subjectShortAnswer);
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
        return SubjectUtils.subjectShortAnswerToDto(this.findListByIds(ids), true);
    }

    @Override
    public int physicalDeleteSubject(Long id) {
        return subjectShortAnswerMapper.physicalDelete(id);
    }

    @Override
    public int physicalDeleteAllSubject(Long[] ids) {
        return subjectShortAnswerMapper.physicalDeleteAll(ids);
    }

    @Override
    public SubjectVO getNextByCurrentIdAndType(Long examinationId, Long previousSubjectId, Integer nextType) {
        return null;
    }

    @Override
    @Transactional
    public int updateAnswerResult(SubjectVO subjectVO) {
        return subjectShortAnswerMapper.updateAnswerResult(subjectVO);
    }

}
