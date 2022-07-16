package com.wyg.common.core.utils;


import java.util.Random;

/**
 * 密码工具类
 *
 * @author haozi2015@aliyun.com
 */
public class PasswordUtils {
    private static final int MIN_NUMBER = 33;
    private static final int MAX_NUMBER = 126;
    private static final int BOUND = MAX_NUMBER - MIN_NUMBER + 1;

    /**
     * 随机生成指定位数的密码
     *
     * char[33,126]，可表示数字、大小写字母、特殊字符
     *
     * @param length 密码长度
     * @return
     */
    public static String randomPassword(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char value = (char) (random.nextInt(BOUND) + MIN_NUMBER);
            builder.append(value);
        }
        return builder.toString();
    }

    public static void main(String[] args) throws Exception {
        for (char i = 0; i < 100; i++) {
            System.out.println(randomPassword(10));
        }
    }
}