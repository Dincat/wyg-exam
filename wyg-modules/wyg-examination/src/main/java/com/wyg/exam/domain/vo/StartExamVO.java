package com.wyg.exam.domain.vo;

import com.wyg.exam.domain.Examination;
import com.wyg.exam.domain.ExaminationRecord;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StartExamVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 考试记录信息
     */
    private ExaminationRecord examRecord;

    /**
     * 试卷信息
     */
    private Examination examination;

    /**
     * 题目信息
     */
    private SubjectVO subjectVO;
}
