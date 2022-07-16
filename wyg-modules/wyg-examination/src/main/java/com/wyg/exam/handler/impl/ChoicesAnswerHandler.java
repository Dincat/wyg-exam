package com.wyg.exam.handler.impl;


import com.wyg.exam.constants.AnswerConstant;
import com.wyg.exam.domain.Answer;
import com.wyg.exam.domain.vo.SubjectVO;
import com.wyg.exam.enums.SubjectTypeEnum;
import com.wyg.exam.handler.AbstractAnswerHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 选择题
 * @author tangyi
 * @date 2019/12/8 21:57
 */
@Slf4j
@AllArgsConstructor
@Component
public class ChoicesAnswerHandler extends AbstractAnswerHandler {

	@Override
	public SubjectTypeEnum getSubjectType() {
		return SubjectTypeEnum.CHOICES;
	}

	@Override
	public boolean judgeRight(Answer answer, SubjectVO subject) {
		return subject.getAnswer().getAnswer().equalsIgnoreCase(answer.getAnswer());
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
		String answerStr = subject.getAnswer().getAnswer();
		if (StringUtils.isNotBlank(userAnswer)) {
			subject.getOptions().forEach(option -> {
				if (userAnswer.equals(option.getOptionName())) {
					option.setRight(answerStr.equals(option.getOptionName()) ? TRUE : FALSE);
				}
			});
		}
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
