package com.wyg.exam.utils;

import com.wyg.common.core.utils.DateUtils;

import java.util.Date;

public class ExamRecordUtils {
    /**
     * 计算持续时间
     * @param startTime startTime
     * @param endTime endTime
     * @return String
     */
    public static String getExamDuration(Date startTime, Date endTime) {
        // 持续时间
        String suffix = "分钟";
        Integer duration = DateUtils.getBetweenMinutes(startTime, endTime);
        if (duration <= 0) {
            duration = DateUtils.getBetweenSecond(startTime, endTime);
            suffix = "秒";
        }
        return duration + suffix;
    }
}
