package com.wyg.exam.domain.vo;

import com.wyg.exam.domain.Course;
import com.wyg.exam.domain.ExamExaminationRank;
import com.wyg.exam.domain.Examination;
import lombok.Data;

import java.util.List;

@Data
public class PaperVO extends Examination {

    /**
     * 成绩排行
     */
    List<ExamExaminationRank> rankList;


}
