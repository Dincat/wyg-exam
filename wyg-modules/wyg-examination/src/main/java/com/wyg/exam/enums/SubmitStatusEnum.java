package com.wyg.exam.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 提交状态
 * @author tangyi
 * @date 2019/12/10 17:53
 */
@Getter
@AllArgsConstructor
public enum SubmitStatusEnum {

	UNSUBMITTED("待提交", 0), SUBMITTED("已提交", 1),
	CALCULATE("批改中", 2),
	CALCULATED("批改完成", 3);

	private String name;

	private Integer value;

	public static SubmitStatusEnum matchByValue(Integer value) {
		for (SubmitStatusEnum item : SubmitStatusEnum.values()) {
			if (item.value.equals(value)) {
				return item;
			}
		}
		return UNSUBMITTED;
	}

	public static SubmitStatusEnum matchByName(String name) {
		for (SubmitStatusEnum item : SubmitStatusEnum.values()) {
			if (item.name.equals(name)) {
				return item;
			}
		}
		return UNSUBMITTED;
	}
}
