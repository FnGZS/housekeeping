package com.housekeeping.utils;

import org.apache.commons.lang3.StringUtils;

import com.housekeeping.model.enums.DeviceTypeEnum;

/**
 * @Type DeviceUtils.java
 * @Desc 
 * @author luogm
 * @date 2017年1月9日 下午1:58:56
 * @Version V1.0
 */
public class DeviceUtils {

	/**
	 * 根据操作系统获取设备类型
	 * @param os
	 * @return
	 */
	public static Integer getDeviceTypeFromOS(String os) {
		if(StringUtils.contains(os, "APP")) { // APP
			return DeviceTypeEnum.APP.getCode();
		}
		if(StringUtils.containsIgnoreCase(os, "Unknown") || StringUtils.containsIgnoreCase(os, "iphone") || StringUtils.containsIgnoreCase(os, "Android")) { // 小程序
			return DeviceTypeEnum.WEIXIN_APP.getCode();
		}
		return DeviceTypeEnum.WEB.getCode(); // web
	}
}
