package com.wyg.exam.service.impl;

import com.wyg.exam.domain.Examination;
import com.wyg.exam.domain.ExaminationSubject;
import com.wyg.exam.domain.SubjectChoices;
import com.wyg.exam.domain.vo.SubjectVO;
import com.wyg.exam.enums.SubjectTypeEnum;
import com.wyg.exam.service.*;
import com.wyg.common.core.constant.Constants;
import com.wyg.common.core.exception.ServiceException;
import com.wyg.common.core.utils.SpringContextHolder;
import com.wyg.common.security.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectService {

    @Autowired
    ISubjectChoicesService subjectChoicesService;

    @Autowired
    ISubjectShortAnswerService subjectShortAnswerService;

    @Autowired
    IExaminationSubjectService examinationSubjectService;

    @Autowired
    ISubjectJudgementService subjectJudgementService;


    public List<SubjectVO> getSubjectList(List<ExaminationSubject> list) {
        List<SubjectVO> subjectDtos = new ArrayList();

        if (CollectionUtils.isEmpty(list)) {
            return subjectDtos;
        }

        list.forEach(tempExaminationSubject -> {
            ISubjectService service = subjectService(tempExaminationSubject.getType());

            SubjectVO temp = service.getSubject(tempExaminationSubject.getSubjectId());
            temp.setWordId(tempExaminationSubject.getWordId());
            if (temp != null)
                subjectDtos.add(temp);
        });

        return subjectDtos;
    }

    public SubjectVO getSubject(Long subjectId, Integer type) {
        return subjectService(type).getSubject(subjectId);
    }


    //region Private Methods

    /**
     * 根据题目类型返回对应的BaseSubjectService
     *
     * @param type type
     * @return BaseSubjectService
     * @author tangyi
     * @date 2019/06/16 17:34
     */
    private ISubjectService subjectService(Integer type) {
        ISubjectService service = SpringContextHolder.getApplicationContext().getBean(SubjectTypeEnum.matchByValue(type).getService());
        return service;
    }

    /**
     * 遍历关系集合，按类型分组题目ID，返回map
     *
     * @param examinationSubjects examinationSubjects
     * @return Map
     * @author tangyi
     * @date 2019/06/17 10:43
     */
    private Map<String, Long[]> getSubjectIdByType(List<ExaminationSubject> examinationSubjects) {
        Map<String, Long[]> idMap = new HashMap<>();
        examinationSubjects.stream().collect(Collectors.groupingBy(ExaminationSubject::getType, Collectors.toList()))
                .forEach((type, temp) -> {
                    // 匹配类型
                    SubjectTypeEnum subjectType = SubjectTypeEnum.matchByValue(type);
                    if (subjectType == null) {
                        return;
                    }
                    switch (subjectType) {
                        case CHOICES:
                            idMap.put(SubjectTypeEnum.CHOICES.name(),
                                    temp.stream().map(ExaminationSubject::getSubjectId).distinct()
                                            .toArray(Long[]::new));
                            break;
                        case JUDGEMENT:
                            idMap.put(SubjectTypeEnum.JUDGEMENT.name(),
                                    temp.stream().map(ExaminationSubject::getSubjectId).distinct()
                                            .toArray(Long[]::new));
                            break;
                        case MULTIPLE_CHOICES:
                            idMap.put(SubjectTypeEnum.MULTIPLE_CHOICES.name(),
                                    temp.stream().map(ExaminationSubject::getSubjectId).distinct()
                                            .toArray(Long[]::new));
                            break;
                        case SHORT_ANSWER:
                            idMap.put(SubjectTypeEnum.SHORT_ANSWER.name(),
                                    temp.stream().map(ExaminationSubject::getSubjectId).distinct()
                                            .toArray(Long[]::new));
                            break;
                    }

                });
        return idMap;
    }

    //endregion


    /**
     * 保存
     *
     * @param subjectVO
     * @return int
     * @author tangyi
     * @date 2019/06/16 17:59
     */
    @Transactional
    public int insert(SubjectVO subjectVO) {
        // 保存与考试的关联关系
        ExaminationSubject examinationSubject = new ExaminationSubject();
        examinationSubject.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode() + "", SecurityUtils.getTenantCode());
        examinationSubject.setSubjectName(subjectVO.getSubjectName());
        examinationSubject.setScore(subjectVO.getScore());
        examinationSubject.setExaminationId(subjectVO.getExaminationId());
        examinationSubject.setCategoryId(subjectVO.getCategoryId());
        examinationSubject.setSubjectId(subjectVO.getId());
        examinationSubject.setType(subjectVO.getType());
        examinationSubject.setWordId(subjectVO.getWordId());
        examinationSubjectService.insertExaminationSubject(examinationSubject);
        return subjectService(subjectVO.getType()).insertSubject(subjectVO);
    }


    public int update(SubjectVO subject) {
        int update = 0;
        if ((update = subjectService(subject.getType()).updateSubject(subject)) == 0)
            update = this.insert(subject);
        if (update < 1) return update;

        ExaminationSubject query = new ExaminationSubject();
        query.setSubjectId(subject.getId());
        List<ExaminationSubject> examList = examinationSubjectService.selectExaminationSubjectList(query);
        if (CollectionUtils.isNotEmpty(examList)) {
            ExaminationSubject examinationSubject = examList.get(0);
            examinationSubject.setSubjectName(subject.getSubjectName());
            examinationSubject.setScore(subject.getScore());
            examinationSubject.setExaminationId(subject.getExaminationId());
            examinationSubject.setCategoryId(subject.getCategoryId());
            examinationSubject.setSubjectId(subject.getId());
            examinationSubject.setType(subject.getType());
            examinationSubject.setWordId(subject.getWordId());
            examinationSubjectService.updateExaminationSubject(examinationSubject);
        }

        return update;
    }

    /**
     * 查询第一题
     *
     * @param examinationId examinationId
     * @return SubjectDto
     * @author tangyi
     * @date 2019/10/13 18:36:58
     */
    public SubjectVO findFirstSubjectByExaminationId(Long examinationId) {
        // 第一题
        ExaminationSubject examinationSubject = new ExaminationSubject();
        examinationSubject.setExaminationId(examinationId);
        examinationSubject.getParams().put(Constants.ORDER_BY_COLUMN, "subject_id");
        examinationSubject.getParams().put(Constants.IS_ASC, "asc");
        // 根据考试ID查询考试题目管理关系，题目ID递增
        List<ExaminationSubject> examinationSubjects = examinationSubjectService.selectExaminationSubjectList(examinationSubject);
        if (CollectionUtils.isEmpty(examinationSubjects))
            throw new ServiceException("该试卷未录入题目");
        // 第一题的ID
        examinationSubject = examinationSubjects.get(0);
        // 根据题目ID，类型获取题目的详细信息
        return this.getSubject(examinationSubject.getSubjectId(), examinationSubject.getType());
    }

    /**
     * 根据上一题ID查找
     *
     * @param examinationId     examinationId
     * @param previousSubjectId previousSubjectId
     * @param type              type
     * @param nextType          0：下一题，1：上一题
     * @return SubjectDto
     * @author tangyi
     * @date 2019/06/18 13:49
     */
    @Transactional
    public SubjectVO getNextByCurrentIdAndType(Long examinationId, Long previousSubjectId, Integer type, Integer nextType) {
        return subjectService(type).getNextByCurrentIdAndType(examinationId, previousSubjectId, nextType);
    }

    /**
     * 查询列表
     *
     * @param type type
     * @param ids  ids
     * @return SubjectDto
     * @author tangyi
     * @date 2019/06/16 18:14
     */
    public List<SubjectVO> findListById(Integer type, Long[] ids) {
        return subjectService(type).findSubjectListById(ids);
    }

    /**
     * 物理删除
     *
     * @param subjectDto subjectDto
     * @return int
     * @author tangyi
     * @date 2019/06/16 22:51
     */
    @Transactional
    public int physicalDelete(SubjectVO subjectDto) {
        if (subjectService(subjectDto.getType()).physicalDeleteSubject(subjectDto.getId()) > 0) {
            ExaminationSubject examinationSubject = new ExaminationSubject();
            examinationSubject.setSubjectId(subjectDto.getId());
            return examinationSubjectService.deleteBySubjectId(examinationSubject);
        }
        return -1;
    }


    @Transactional
    public boolean updateAnswerResult(SubjectVO subjectDto) {
        int val = examinationSubjectService.updateAnswerResult(subjectDto);
        int val1 = subjectService(subjectDto.getType()).updateAnswerResult(subjectDto);

        return (val + val1) == 2;
    }

}
