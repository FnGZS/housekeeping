package com.housekeeping.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class CollectionUtil {
	
	/**
     * String转换成List
     */
    public static List<Long> getLongListFromString(String str) {
    	if(StringUtils.isBlank(str)) {
    		return null;
    	}
    	List<Long> list = new ArrayList<>();
    	for(String num : str.split(",")) {
    		if(StringUtils.isNumeric(num)) {
				list.add(Long.parseLong(num));
			}
    	}
    	return list;
    }
    
	/**
     * String转换成Set
     */
    public static Set<Long> getLongSetFromString(String str) {
    	if(StringUtils.isBlank(str)) {
    		return null;
    	}
    	Set<Long> set = new HashSet<>();
    	for(String num : str.split(",")) {
    		if(StringUtils.isNumeric(num)) {
				set.add(Long.parseLong(num));
			}
    	}
    	return set;
    }
    
    public static boolean isEmpty(List<?> list) {
    	if(list == null || list.size() <= 0) {
    		return true;
    	}
    	return false;
    }
    
    public static boolean isNotEmpty(List<?> list) {
    	if(list != null && list.size() > 0) {
    		return true;
    	}
    	return false;
    }
    
    public static boolean isEmpty(Set<?> set) {
    	if(set == null || set.size() <= 0) {
    		return true;
    	}
    	return false;
    }
    
    public static boolean isNotEmpty(Set<?> set) {
    	if(set != null && set.size() > 0) {
    		return true;
    	}
    	return false;
    }
}
