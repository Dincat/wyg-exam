package com.wyg.exam.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.lang.Opt;
import com.wyg.exam.constants.AnswerConstant;
import com.wyg.exam.domain.ExaminationSubject;
import com.wyg.exam.domain.SubjectOption;
import com.wyg.exam.domain.base.BaseService;
import com.wyg.exam.domain.vo.SubjectOptionVO;
import com.wyg.exam.domain.vo.SubjectVO;
import com.wyg.exam.service.IExaminationSubjectService;
import com.wyg.exam.service.ISubjectOptionService;
import com.wyg.exam.utils.AnswerHandlerUtils;
import com.wyg.exam.utils.SubjectUtils;
import com.wyg.common.security.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import com.wyg.exam.mapper.SubjectChoicesMapper;
import com.wyg.exam.domain.SubjectChoices;
import com.wyg.exam.service.ISubjectChoicesService;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;

/**
 * 选择题Service业务层处理
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
@Service
@AllArgsConstructor
public class SubjectChoicesServiceImpl extends BaseService<SubjectChoicesMapper,SubjectChoices> implements ISubjectChoicesService
{
    @Autowired
    private SubjectChoicesMapper subjectChoicesMapper;


    @Autowired
    ISubjectOptionService subjectOptionService;

    @Autowired
    IExaminationSubjectService examinationSubjectService;

    /**
     * 查询选择题
     * 
     * @param id 选择题主键
     * @return 选择题
     */
    @Override
    public SubjectChoices selectSubjectChoicesById(Long id)
    {
        return subjectChoicesMapper.selectSubjectChoicesById(id);
    }

    /**
     * 查询选择题列表
     * 
     * @param subjectChoices 选择题
     * @return 选择题
     */
    @Override
    public List<SubjectChoices> selectSubjectChoicesList(SubjectChoices subjectChoices)
    {
        return subjectChoicesMapper.selectSubjectChoicesList(subjectChoices);
    }

    /**
     * 新增选择题
     * 
     * @param subjectChoices 选择题
     * @return 结果
     */
    @Override
    public int insertSubjectChoices(SubjectChoices subjectChoices)
    {
        return subjectChoicesMapper.insertSubjectChoices(subjectChoices);
    }

    /**
     * 修改选择题
     * 
     * @param subjectChoices 选择题
     * @return 结果
     */
    @Override
    public int updateSubjectChoices(SubjectChoices subjectChoices)
    {
        subjectChoices.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(),SecurityUtils.getTenantCode());
        return subjectChoicesMapper.updateSubjectChoices(subjectChoices);
    }

    /**
     * 批量删除选择题
     * 
     * @param ids 需要删除的选择题主键
     * @return 结果
     */
    @Override
    public int deleteSubjectChoicesByIds(Long[] ids)
    {
        return subjectChoicesMapper.deleteSubjectChoicesByIds(ids);
    }

    /**
     * 删除选择题信息
     *
     * @param id 选择题主键
     * @return 结果
     */
    @Override
    public int deleteSubjectChoicesById(Long id)
    {
        return subjectChoicesMapper.deleteSubjectChoicesById(id);
    }

    @Override
    public List<SubjectVO> getSubjectList(List<ExaminationSubject> list) {
        return null;
    }

    @Override
    public SubjectVO getSubject(Long subjectId)
    {
        SubjectChoices subject = this.selectSubjectChoicesById(subjectId);
        // 查找选项信息
        if (subject != null) {
            SubjectOption subjectOption = new SubjectOption();
            subjectOption.setSubjectChoicesId(subject.getId());
            List<SubjectOption> options = subjectOptionService.selectSubjectOptionList(subjectOption);
            subject.setOptions(options);
        }
        return SubjectUtils.subjectChoicesToVO(subject, true);
    }


    /**
     * 保存选项
     * @param subjectChoices subjectChoices
     * @author tangyi
     * @date 2020/01/17 22:30:48
     */
    @Transactional
    public void insertOptions(SubjectChoices subjectChoices) {
        if (CollectionUtils.isNotEmpty(subjectChoices.getOptions())) {
            SubjectOption subjectOption = new SubjectOption();
            subjectOption.setSubjectChoicesId(subjectChoices.getId());
            subjectOptionService.deleteBySubjectChoicesId(subjectOption);
            // 初始化
            subjectChoices.getOptions().forEach(option -> {
                option.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(),SecurityUtils.getTenantCode());
                option.setWordId(option.getWordId());
                option.setSubjectChoicesId(subjectChoices.getId());
            });
            // 批量插入
            subjectOptionService.insertBatch(subjectChoices.getOptions());
        }
    }


    @Override
    @Transactional
    @CacheEvict(value = "subjectChoices", key = "#subjectDto.id")
    public int insertSubject(SubjectVO subjectDto) {
        SubjectChoices subjectChoices = new SubjectChoices();
        BeanUtils.copyProperties(subjectDto, subjectChoices);
        subjectChoices.setId(subjectDto.getId());
        subjectChoices.setScore(subjectChoices.getScore());
        subjectChoices.setExaminationId(subjectChoices.getExaminationId());
        subjectChoices.setAnswer(subjectDto.getAnswer().getAnswer());
        subjectChoices.setChoicesType(subjectDto.getType());

        if(CollectionUtils.isNotEmpty(subjectDto.getOptions())){
            List<SubjectOption> optionList=new ArrayList<>();
            for(SubjectOptionVO vo:subjectDto.getOptions()){
                SubjectOption option=new SubjectOption();
                BeanUtils.copyProperties(vo,option);
                optionList.add(option);
            }
            subjectChoices.setOptions(optionList);
        }

        insertOptions(subjectChoices);
        return this.insertSubjectChoices(subjectChoices);
    }


    /**
     * 更新，包括更新选项
     *
     * @param subjectDto subjectDto
     * @return int
     * @author tangyi
     * @date 2019/06/16 17:50
     */
    @Override
    @Transactional
    @CacheEvict(value = "subjectChoices", key = "#subjectDto.id")
    public int updateSubject(SubjectVO subjectDto) {
        SubjectChoices subjectChoices = new SubjectChoices();
        BeanUtils.copyProperties(subjectDto, subjectChoices);
        subjectChoices.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(),SecurityUtils.getTenantCode());
        // 参考答案
        subjectChoices.setAnswer(AnswerHandlerUtils.replaceComma(subjectDto.getAnswer().getAnswer()));
        return this.updateSubjectChoices(subjectChoices);
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
        return SubjectUtils.subjectChoicesToVO(this.findListByIds(ids), true);
    }

    @Override
    public int physicalDeleteSubject(Long id) {
        return subjectChoicesMapper.physicalDelete(id);
    }

    @Override
    public int physicalDeleteAllSubject(Long[] ids) {
        return subjectChoicesMapper.physicalDeleteAll(ids);
    }


    /**
     * 根据ID查询
     *
     * @param examinationId  examinationId
     * @param subjectChoices subjectChoices
     * @return SubjectChoices
     * @author tangyi
     * @date 2019-09-14 16:47
     */
    public SubjectChoices getByCurrentId(Long examinationId, SubjectChoices subjectChoices) {
        ExaminationSubject examinationSubject = new ExaminationSubject();
        examinationSubject.setExaminationId(examinationId);
        examinationSubject.setSubjectId(subjectChoices.getId());
        examinationSubject = examinationSubjectService.findByExaminationIdAndSubjectId(examinationSubject);
        if (examinationSubject == null)
            return null;
        return this.getSubjectChoicesById(examinationSubject.getSubjectId());
    }

    /**
     * 根据上一题ID查询下一题
     *
     * @param examinationId  examinationId
     * @param subjectChoices subjectChoices
     * @return SubjectChoices
     * @author tangyi
     * @date 2019-09-14 16:47
     */
    public SubjectChoices getByPreviousId(Long examinationId, SubjectChoices subjectChoices) {
        ExaminationSubject examinationSubject = new ExaminationSubject();
        examinationSubject.setExaminationId(examinationId);
        examinationSubject.setSubjectId(subjectChoices.getId());
        examinationSubject = examinationSubjectService.getByPreviousId(examinationSubject);
        if (examinationSubject == null)
            return null;
        return this.getSubjectChoicesById(examinationSubject.getSubjectId());
    }

    /**
     * 根据当前题目ID查询上一题
     *
     * @param examinationId  examinationId
     * @param subjectChoices subjectChoices
     * @return SubjectChoices
     * @author tangyi
     * @date 2019/10/07 20:40:16
     */
    public SubjectChoices getPreviousByCurrentId(Long examinationId, SubjectChoices subjectChoices) {
        ExaminationSubject examinationSubject = new ExaminationSubject();
        examinationSubject.setExaminationId(examinationId);
        examinationSubject.setSubjectId(subjectChoices.getId());
        examinationSubject = examinationSubjectService.getPreviousByCurrentId(examinationSubject);
        if (examinationSubject == null)
            return null;
        return this.getSubjectChoicesById(examinationSubject.getSubjectId());
    }

    /**
     * 根据上一题ID查询下一题
     *
     * @param examinationId examinationId
     * @param previousId    previousId
     * @param nextType      0：下一题，1：上一题
     * @return SubjectDto
     * @author tangyi
     * @date 2019/09/14 16:35
     */
    @Override
    @Transactional
    public SubjectVO getNextByCurrentIdAndType(Long examinationId, Long previousId, Integer nextType) {
        SubjectChoices subjectChoices = new SubjectChoices();
        subjectChoices.setId(previousId);
        if (AnswerConstant.CURRENT.equals(nextType)) {
            subjectChoices = this.getByCurrentId(examinationId, subjectChoices);
        } else if (AnswerConstant.NEXT.equals(nextType)) {
            subjectChoices = this.getByPreviousId(examinationId, subjectChoices);
        } else {
            subjectChoices = this.getPreviousByCurrentId(examinationId, subjectChoices);
        }
        return SubjectUtils.subjectChoicesToVO(subjectChoices, true);
    }

    /**
     * 根据题目ID查询题目信息和选项
     *
     * @param subjectId subjectId
     * @return SubjectChoices
     * @author tangyi
     * @date 2019/10/07 21:03:43
     */
    private SubjectChoices getSubjectChoicesById(Long subjectId) {
        SubjectChoices subjectChoices = this.selectSubjectChoicesById(subjectId);
        SubjectOption subjectOption = new SubjectOption();
        subjectOption.setSubjectChoicesId(subjectChoices.getId());
        List<SubjectOption> options = subjectOptionService.selectSubjectOptionList(subjectOption);
        subjectChoices.setOptions(options);
        return subjectChoices;
    }

    @Override
    @Transactional
    public int updateAnswerResult(SubjectVO subjectVO) {
        return subjectChoicesMapper.updateAnswerResult(subjectVO);
    }

}
