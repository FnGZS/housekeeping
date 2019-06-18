package com.housekeeping.model.enums;

/**
 * @Type UserBehaviorTypeEnum.java
 * @Desc 
 */
public enum UserBehaviorTypeEnum {
	
	USER_REGISTER(0, "用户注册"),
	CHANGE_PASSWORD(1, "修改密码"),
	USER_DELETE(2, "用户注销");
	
	private UserBehaviorTypeEnum(Integer code, String desc) {
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
