package com.wyg.exam.handler.impl;


import com.wyg.exam.constants.AnswerConstant;
import com.wyg.exam.domain.Answer;
import com.wyg.exam.domain.vo.SubjectVO;
import com.wyg.exam.enums.SubjectTypeEnum;
import com.wyg.exam.handler.AbstractAnswerHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 简答题
 * @author tangyi
 * @date 2019/12/8 22:00
 */
@Slf4j
@AllArgsConstructor
@Component
public class ShortAnswerHandler extends AbstractAnswerHandler {

	@Override
	public SubjectTypeEnum getSubjectType() {
		return SubjectTypeEnum.SHORT_ANSWER;
	}

	@Override
	public boolean judgeRight(Answer answer, SubjectVO subject) {
		// TODO 暂时全匹配
		return subject.getAnswer().getAnswer().equals(answer.getAnswer());
	}

	@Override
	public void judge(Answer answer, SubjectVO subject, List<Double> rightScore) {
		if (judgeRight(answer, subject)) {
			rightScore.add(subject.getScore());
			answer.setAnswerType(AnswerConstant.RIGHT);
			answer.setScore(subject.getScore());
			subject.setCorrectCount(1);
		} else {
			answer.setAnswerType(AnswerConstant.WRONG);
			answer.setScore(0.0);
			subject.setIncorrectCount(1);
		}
		answer.setMarkStatus(AnswerConstant.MARKED);
	}
}
