package com.wyg.exam.handler;


import com.wyg.common.core.utils.SpringContextHolder;
import com.wyg.exam.domain.Answer;
import com.wyg.exam.domain.vo.SubjectVO;
import com.wyg.exam.service.impl.SubjectService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 统计成绩
 * @author tangyi
 * @date 2020/1/19 10:07 上午
 */
public abstract class AbstractAnswerHandler implements IAnswerHandler {

	@Autowired
	SubjectService subjectService;

	@Override
	public AnswerHandleResult handle(List<Answer> answers) {
		if (CollectionUtils.isNotEmpty(answers)) {
			// 保存答题正确的题目分数
			List<Double> rightScore = new ArrayList<>();
			// 获取题目信息
			List<SubjectVO> subjects = getSubjects(answers);
			answers.forEach(tempAnswer -> {
				subjects.stream()
						// 题目ID匹配
						.filter(tempSubject -> tempSubject.getId().equals(tempAnswer.getSubjectId())).findFirst()
						.ifPresent(subject ->
						{
							judge(tempAnswer, subject, rightScore);
							subjectService.updateAnswerResult(subject);
						});



			});
			AnswerHandleResult result = new AnswerHandleResult();
			// 记录总分、正确题目数、错误题目数
			result.setScore(rightScore.stream().mapToDouble(Double::valueOf).sum());
			result.setCorrectNum(rightScore.size());
			result.setInCorrectNum(answers.size() - rightScore.size());
			return result;
		}
		return null;
	}

	@Override
	public List<SubjectVO> getSubjects(List<Answer> answers) {
		return SpringContextHolder.getApplicationContext().getBean(SubjectService.class)
				.findListById(getSubjectType().getValue(),
						answers.stream().map(Answer::getSubjectId).distinct().toArray(Long[]::new));
	}
}
