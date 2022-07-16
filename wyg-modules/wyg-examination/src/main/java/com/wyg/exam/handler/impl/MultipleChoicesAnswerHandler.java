package com.wyg.exam.handler.impl;


import com.wyg.exam.constants.AnswerConstant;
import com.wyg.exam.domain.Answer;
import com.wyg.exam.domain.vo.SubjectVO;
import com.wyg.exam.enums.SubjectTypeEnum;
import com.wyg.exam.handler.AbstractAnswerHandler;
import com.wyg.exam.utils.AnswerHandlerUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 统计多选题
 * @author tangyi
 * @date 2020/1/19 10:02 上午
 */
@Slf4j
@AllArgsConstructor
@Component
public class MultipleChoicesAnswerHandler extends AbstractAnswerHandler {

	@Override
	public SubjectTypeEnum getSubjectType() {
		return SubjectTypeEnum.MULTIPLE_CHOICES;
	}

	/**
	 * 判断选项是否正确
	 *
	 * @param answer  answer
	 * @param subject subject
	 * @author tangyi
	 * @date 2020/02/19 23:23
	 */
	public void judgeOptionRight(Answer answer, SubjectVO subject) {
		String userAnswer = answer.getAnswer();
		String correctAnswer = subject.getAnswer().getAnswer();

		if(StringUtils.isEmpty(userAnswer)) return;

		if (StringUtils.isNotBlank(userAnswer) && StringUtils.isNotBlank(correctAnswer)) {
			String[] userAnswers = AnswerHandlerUtils.replaceComma(userAnswer).split(AnswerConstant.COMMA);
			String[] correctAnswers = AnswerHandlerUtils.replaceComma(correctAnswer).split(AnswerConstant.COMMA);
			subject.getOptions().forEach(option -> {
				if (ArrayUtils.contains(correctAnswers, option.getOptionName())) {
					option.setRight(ArrayUtils.contains(userAnswers, option.getOptionName()) ? TRUE : FALSE);
				}
			});
		}
	}

	@Override
	public boolean judgeRight(Answer answer, SubjectVO subject) {
		if(StringUtils.isEmpty(answer.getAnswer())) return false;

		String[] correctAnswers = AnswerHandlerUtils.replaceComma(subject.getAnswer().getAnswer()).split(AnswerConstant.COMMA);
		for (String as : answer.getAnswer().split(AnswerConstant.COMMA)) {
			if (!ArrayUtils.contains(correctAnswers, as)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void judge(Answer answer, SubjectVO subject, List<Double> rightScore) {
		if (StringUtils.isNotBlank(subject.getAnswer().getAnswer())) {
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
}
