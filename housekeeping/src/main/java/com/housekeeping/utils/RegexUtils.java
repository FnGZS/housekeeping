package com.housekeeping.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * @Type RegexUtils
 * @Desc
 * @Version V1.0
 */
public class RegexUtils {

	/**
	 * 正则表达式匹配
	 * @param pattern	正则表达式
	 * @param value		匹配字符串
	 * @return
	 */
	public static boolean regexMatch(String pattern, String value) {
		if (StringUtils.isBlank(pattern) || StringUtils.isBlank(value)) {
			return false;
		}

		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(value);
		return matcher.matches();
	}
	
	public static boolean isLegalMobile(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return false;
		}

		Pattern regex = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[89]))\\d{8}$");
		Matcher matcher = regex.matcher(mobile);
		return matcher.matches();
	}
}
