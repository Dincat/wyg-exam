package com.wyg.exam.enums;

import com.wyg.exam.service.impl.SubjectChoicesServiceImpl;
import com.wyg.exam.service.impl.SubjectJudgementServiceImpl;
import com.wyg.exam.service.impl.SubjectShortAnswerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 题目类型枚举
 *
 * @author tangyi
 * @date 2019/6/16 16:22
 */
@Getter
@AllArgsConstructor
public enum SubjectTypeEnum {

	CHOICES("选择题", 0, SubjectChoicesServiceImpl.class),

	SHORT_ANSWER("简答题", 1, SubjectShortAnswerServiceImpl.class),

	JUDGEMENT("判断题", 2, SubjectJudgementServiceImpl.class),

	MULTIPLE_CHOICES("多选题", 3, SubjectChoicesServiceImpl.class);

	private String name;

	private Integer value;

	private Class<? extends com.wyg.exam.service.ISubjectService> service;

	/**
	 * 根据类型返回具体的SubjectType
	 *
	 * @param value value
	 * @return SubjectType
	 */
	public static SubjectTypeEnum matchByValue(Integer value) {
		for (SubjectTypeEnum item : SubjectTypeEnum.values()) {
			if (item.value.equals(value)) {
				return item;
			}
		}
		return CHOICES;
	}

	/**
	 * 根据描述返回具体的SubjectType
	 *
	 * @param name name
	 * @return SubjectType
	 */
	public static SubjectTypeEnum matchByName(String name) {
		for (SubjectTypeEnum item : SubjectTypeEnum.values()) {
			if (item.name.equals(name)) {
				return item;
			}
		}
		return CHOICES;
	}
}
