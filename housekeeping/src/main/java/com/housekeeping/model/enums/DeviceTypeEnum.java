package com.housekeeping.model.enums;

/**
 *
 */
public enum DeviceTypeEnum {
	
	WEB(0, "网站"),
	APP(1, "APP"),
	WEIXIN_APP(2, "微信小程�?");
	
	private DeviceTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private Integer code;

	private String desc;

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
