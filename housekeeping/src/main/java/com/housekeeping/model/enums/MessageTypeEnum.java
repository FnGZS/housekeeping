package com.housekeeping.model.enums;

/**
 * @Type MessageTypeEnum.java
 * @Desc 
 */
public enum MessageTypeEnum {

	PERSONAL(0, "个人消息"),
	GROUP(1, "群消�?"),
	SYSTEM(2, "系统消息");
	
	private MessageTypeEnum(Integer code, String desc) {
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
