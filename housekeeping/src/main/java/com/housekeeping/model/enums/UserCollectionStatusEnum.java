package com.housekeeping.model.enums;

/**
 * @Type UserCollectionStatusEnum.java
 * @Desc
 */

public enum UserCollectionStatusEnum {
    
	NO_COLLECT(0, "未收�?"),
	COLLECT(1, "已收�?");
	
	private UserCollectionStatusEnum(Integer code, String desc) {
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
