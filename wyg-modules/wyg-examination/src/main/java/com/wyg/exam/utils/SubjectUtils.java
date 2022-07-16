package com.wyg.exam.utils;

import com.wyg.exam.domain.*;
import com.wyg.exam.domain.vo.SubjectOptionVO;
import com.wyg.exam.domain.vo.SubjectVO;
import com.wyg.exam.enums.SubjectTypeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubjectUtils {
    private SubjectUtils() {
    }


    /**
     * SubjectChoices转SubjectDto
     *
     * @param subjectChoice subjectChoice
     * @param findAnswer findAnswer
     * @return List
     * @author tangyi
     * @date 2019/06/16 16:50
     */
    public static SubjectVO subjectChoicesToVO(SubjectChoices subjectChoice, boolean findAnswer) {
        if (subjectChoice == null)
            return null;
        SubjectVO subjectDto = new SubjectVO();
        subjectDto.setId(subjectChoice.getId());
        subjectDto.setSubjectName(subjectChoice.getSubjectName());
        subjectDto.setScore(subjectChoice.getScore());
        subjectDto.setAnalysis(subjectChoice.getAnalysis());
        subjectDto.setLevel(subjectChoice.getLevel());
        subjectDto.setCategoryId(subjectChoice.getCategoryId());
        // 选择题类型匹配
        SubjectTypeEnum subjectTypeEnum = SubjectTypeEnum.matchByValue(subjectChoice.getChoicesType());
        if (subjectTypeEnum != null)
            subjectDto.setType(subjectTypeEnum.getValue());
        subjectDto.setChoicesType(subjectChoice.getChoicesType());
        subjectDto.setCreator(subjectChoice.getCreator());

        subjectDto.setModifier(subjectChoice.getModifier());
        subjectDto.setModifyDate(subjectChoice.getModifyDate());
        // 参考答案
        if (findAnswer) {
            Answer answer = new Answer();
            answer.setAnswer(subjectChoice.getAnswer());
            subjectDto.setAnswer(answer);
        }

        //subjectDto.setOptions(subjectChoice.getOptions());
        if(CollectionUtils.isNotEmpty(subjectChoice.getOptions())){
            List<SubjectOptionVO> options=new ArrayList<>();
            for(SubjectOption option:subjectChoice.getOptions()) {
                SubjectOptionVO optionVO=new SubjectOptionVO();
                BeanUtils.copyProperties(option, optionVO);
                options.add(optionVO);
            }
            subjectDto.setOptions(options);
        }

        return subjectDto;
    }

    /**
     * SubjectChoices转SubjectDto
     *
     * @param subjectChoices subjectChoices
     * @param findAnswer findAnswer
     * @return List
     * @author tangyi
     * @date 2019/06/16 16:50
     */
    public static List<SubjectVO> subjectChoicesToVO(List<SubjectChoices> subjectChoices, boolean findAnswer) {
        List<SubjectVO> subjectDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(subjectChoices)) {
            subjectDtoList = subjectChoices.stream().map(subjectChoice -> SubjectUtils.subjectChoicesToVO(subjectChoice, findAnswer)).collect(Collectors.toList());
        }
        return subjectDtoList;
    }


    /**
     * SubjectJudgement转SubjectDto
     *
     * @param subjectJudgement subjectJudgement
     * @return List
     * @author tangyi
     * @date 2020/02/22 12:23
     */
    public static SubjectVO subjectJudgementToVO(SubjectJudgement subjectJudgement) {
        return subjectJudgementToVO(subjectJudgement, true);
    }

    /**
     * SubjectJudgement转SubjectDto
     *
     * @param subjectJudgement subjectJudgement
     * @param findAnswer findAnswer
     * @return List
     * @author tangyi
     * @date 2020/02/22 12:23
     */
    public static SubjectVO subjectJudgementToVO(SubjectJudgement subjectJudgement, boolean findAnswer) {
        if (subjectJudgement == null)
            return null;
        SubjectVO subjectDto = new SubjectVO();
        subjectDto.setId(subjectJudgement.getId());
        subjectDto.setSubjectName(subjectJudgement.getSubjectName());
        subjectDto.setScore(subjectJudgement.getScore());
        subjectDto.setAnalysis(subjectJudgement.getAnalysis());
        subjectDto.setLevel(subjectJudgement.getLevel());
        subjectDto.setType(SubjectTypeEnum.JUDGEMENT.getValue());
        subjectDto.setCreator(subjectJudgement.getCreator());
        subjectDto.setModifier(subjectJudgement.getModifier());
        subjectDto.setModifyDate(subjectJudgement.getModifyDate());
        subjectDto.setCategoryId(subjectJudgement.getCategoryId());
        // 题目类型
        subjectDto.setType(SubjectTypeEnum.JUDGEMENT.getValue());

        // 参考答案
        if (findAnswer) {
            Answer answer = new Answer();
            answer.setAnswer(subjectJudgement.getAnswer());
            subjectDto.setAnswer(answer);
        }
        return subjectDto;
    }

    /**
     * SubjectJudgement转SubjectDto
     *
     * @param subjectJudgements subjectJudgements
     * @param findAnswer findAnswer
     * @return List
     * @author tangyi
     * @date 2020/02/22 12:24
     */
    public static List<SubjectVO> subjectJudgementToVO(List<SubjectJudgement> subjectJudgements, boolean findAnswer) {
        List<SubjectVO> subjectDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(subjectJudgements)) {
            subjectDtoList = subjectJudgements.stream().map(subjectJudgement -> SubjectUtils.subjectJudgementToVO(subjectJudgement, findAnswer))
                    .collect(Collectors.toList());
        }
        return subjectDtoList;
    }



    /**
     * SubjectShortAnswer转SubjectDto
     *
     * @param subjectShortAnswer subjectShortAnswer
     * @param findAnswer findAnswer
     * @return List
     * @author tangyi
     * @date 2019/06/16 16:59
     */
    public static SubjectVO subjectShortAnswerToDto(SubjectShortAnswer subjectShortAnswer, boolean findAnswer) {
        if (subjectShortAnswer == null)
            return null;
        SubjectVO subjectDto = new SubjectVO();
        subjectDto.setId(subjectShortAnswer.getId());
        subjectDto.setSubjectName(subjectShortAnswer.getSubjectName());
        subjectDto.setScore(subjectShortAnswer.getScore());
        subjectDto.setAnalysis(subjectShortAnswer.getAnalysis());
        subjectDto.setLevel(subjectShortAnswer.getLevel());
        subjectDto.setType(SubjectTypeEnum.SHORT_ANSWER.getValue());
        subjectDto.setCreator(subjectShortAnswer.getCreator());
        subjectDto.setModifier(subjectShortAnswer.getModifier());
        subjectDto.setModifyDate(subjectShortAnswer.getModifyDate());
        subjectDto.setCategoryId(subjectShortAnswer.getCategoryId());
        // 题目类型
        subjectDto.setType(SubjectTypeEnum.SHORT_ANSWER.getValue());

        // 参考答案
        if (findAnswer) {
            Answer answer = new Answer();
            answer.setAnswer(subjectShortAnswer.getAnswer());
            subjectDto.setAnswer(answer);
        }
        return subjectDto;
    }

    /**
     * SubjectShortAnswer转SubjectDto
     *
     * @param subjectShortAnswers subjectShortAnswers
     * @param findAnswer findAnswer
     * @return List
     * @author tangyi
     * @date 2019/06/16 16:59
     */
    public static List<SubjectVO> subjectShortAnswerToDto(List<SubjectShortAnswer> subjectShortAnswers, boolean findAnswer) {
        List<SubjectVO> subjectDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(subjectShortAnswers)) {
            subjectDtoList = subjectShortAnswers.stream().map(subjectShortAnswer -> SubjectUtils.subjectShortAnswerToDto(subjectShortAnswer, findAnswer))
                    .collect(Collectors.toList());
        }
        return subjectDtoList;
    }


}
