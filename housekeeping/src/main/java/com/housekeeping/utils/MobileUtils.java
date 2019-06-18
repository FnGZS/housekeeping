package com.housekeeping.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Type MobileUtils
 * @Desc 
 * @Version V1.0
 */
public class MobileUtils extends StringUtils{
	/**
	 * 获得不是Null类型的字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String getNotNullString(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		return str;
	}

	/**
	 * 获得不是Null类型的字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String getNotNullString(Integer input, String nullReplace) {
		if (input == null) {
			return nullReplace;
		}
		return input.toString();
	}

	/**
	 * 是否为Class对象
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isBaseObject(Object object) {
		return isBaseObject(object.getClass());
	}

	/**
	 * 是否为Class对象
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isBaseObject(Class<?> cla) {
		if (cla.equals(String.class) || cla.equals(Integer.class) || cla.equals(Long.class) || cla.equals(Float.class)
				|| cla.equals(Double.class) || cla.equals(Byte.class) || cla.equals(Short.class)) {
			return true;
		}
		return false;
	}
}
