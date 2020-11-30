package com.icu.cc.common.util;

/**
 * 字符串处理工具
 */
public class StringUtils {

    /**
     * 判断字符串是否为空白字符串
     *
     * @param str 字符串
     * @return true - 是
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 不断字符串是否不是空白字符串
     *
     * @param str 字符串
     * @return true - 是
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

}
