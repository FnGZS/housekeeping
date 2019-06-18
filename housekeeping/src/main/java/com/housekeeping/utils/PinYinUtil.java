package com.housekeeping.utils;

import org.apache.commons.lang.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * @author luogm
 *
 */
public class PinYinUtil {
	/**
	 * 提取每个汉字的首字母(大写)
	 * 
	 * @param str
	 * @return
	 */
	public static String getPinYinHeadChar(String str) {
	    if (isNull(str)) {
	        return "";
	    }
	    String convert = "";
	    for (int j = 0; j < str.length(); j++) {
	        char word = str.charAt(j);
	        // 提取汉字的首字母
	        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
	        if (pinyinArray != null) {
	            convert += pinyinArray[0].charAt(0);
	        }
	        else {
	            convert += word;
	        }
	    }
	
	    convert = string2AllTrim(convert);
	    return convert.toUpperCase();
	}
	
    /**
	 * 提取第一个汉字的首字母(大写)
	 * 
	 * @param str
	 * @return
	 */
	public static String getFirstHeadChar(String str) {
	    if (isNull(str)) {
	        return "";
	    }
	    String convert = "";
	    char word = str.charAt(0);
	    // 提取汉字的首字母
	    String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
	    if (pinyinArray != null) {
	        convert += pinyinArray[0].charAt(0);
	    }
	    else {
	        convert += word;
	    }
	
	    convert = string2AllTrim(convert);
	    return convert.toUpperCase();
	}
	
	/*
	* 判断字符串是否为空
	*/
	
	public static boolean isNull(Object strData) {
	    if (strData == null || String.valueOf(strData).trim().equals("")) {
	        return true;
	    }
	    return false;
	}
	
	/**
	* 去掉字符串包含的所有空格
	* 
	* @param value
	* @return
	*/
	public static String string2AllTrim(String value) {
	    if (isNull(value)) {
	        return "";
	    }
	    return value.trim().replace(" ", "");
	}
	
	/**
	 * 获取价格中的数字
	 * @return
	 */
	public static String getNumberFromString(String value) {
		if (StringUtils.isBlank(value)) {
			return "";
		}
		int lastNumberIndex = 0;
		for (int i = value.length() - 1; i >= 0; i--) {
			char a = value.charAt(i);
			if (a>='0' && a<='9') {
				lastNumberIndex = i + 1;
				break;
			}
		}
		return value.substring(0, lastNumberIndex);
	}
	
	/**
	 * 将报价单中的价格拆分成数量和单位
	 * @param value
	 * @return
	 */
	public static String[] splitPriceToNumAndUnit(String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		String[] numAndUnit = new String[2];
		if (value.lastIndexOf("美元/米") != -1) {
			numAndUnit[0] = value.substring(0, value.length() - 4);
			numAndUnit[1] = "美元/米";
		} else if (value.lastIndexOf("美元/码") != -1) {
			numAndUnit[0] = value.substring(0, value.length() - 4);
			numAndUnit[1] = "美元/码";
		} else if (value.lastIndexOf("美元/公斤") != -1) {
			numAndUnit[0] = value.substring(0, value.length() - 5);
			numAndUnit[1] = "美元/公斤";
		} else if (value.lastIndexOf("元/米") != -1) {
			numAndUnit[0] = value.substring(0, value.length() - 3);
			numAndUnit[1] = "元/米";
		} else if (value.lastIndexOf("元/码") != -1) {
			numAndUnit[0] = value.substring(0, value.length() - 3);
			numAndUnit[1] = "元/码";
		} else if (value.lastIndexOf("元/公斤") != -1) {
			numAndUnit[0] = value.substring(0, value.length() - 4);
			numAndUnit[1] = "元/公斤";
		} else if (value.lastIndexOf("米") != -1) {
			numAndUnit[0] = value.substring(0, value.length() - 1);
			numAndUnit[1] = "米";
		} else if (value.lastIndexOf("码") != -1) {
			numAndUnit[0] = value.substring(0, value.length() - 1);
			numAndUnit[1] = "码";
		} else if (value.lastIndexOf("公斤") != -1) {
			numAndUnit[0] = value.substring(0, value.length() - 2);
			numAndUnit[1] = "公斤";
		}
		return numAndUnit;
	}
	
	/**
	 * 获取只包含一个"/"字符串的前后字符串
	 * @param unit
	 * @return
	 */
	public static String[] splitUnit(String unit) {
		if (StringUtils.isBlank(unit) || !unit.contains("/")) {
			return null;
		}
		String[] units = new String[2];
		int index = unit.indexOf("/");
		units[0] = unit.substring(0, index);
		if (index == unit.length()) {
			units[1] = "";
		} else {
			units[1] = unit.substring(index + 1);
		}
		return units;
	}
}
