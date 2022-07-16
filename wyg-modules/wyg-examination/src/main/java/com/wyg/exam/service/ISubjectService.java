package com.wyg.exam.service;

import com.wyg.exam.domain.ExaminationSubject;
import com.wyg.exam.domain.vo.SubjectVO;

import java.util.List;

public interface ISubjectService {
    List<SubjectVO> getSubjectList(List<ExaminationSubject> list);

    SubjectVO getSubject(Long subjectId);

    int updateSubject(SubjectVO subject);

    int insertSubject(SubjectVO subjectVO);

    /**
     * 根据ID批量查询
     *
     * @param ids ids
     * @return List
     * @author tangyi
     * @date 2019/06/16 18:10
     */
    List<SubjectVO> findSubjectListById(Long[] ids);

    /**
     * 物理删除
     *
     * @param subjectDto subjectDto
     * @return int
     * @author tangyi
     * @date 2019/06/16 22:48
     */
    int physicalDeleteSubject(Long id);

    /**
     * 物理批量删除
     *
     * @param ids ids
     * @return int
     * @author tangyi
     * @date 2019/06/16 22:49
     */
    int physicalDeleteAllSubject(Long[] ids);

    SubjectVO getNextByCurrentIdAndType(Long examinationId, Long previousSubjectId, Integer nextType);

    int updateAnswerResult(SubjectVO subjectVO);
}
