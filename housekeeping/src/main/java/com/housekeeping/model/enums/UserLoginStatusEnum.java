package com.housekeeping.model.enums;


public enum UserLoginStatusEnum {
	
	LOGIN_SUCCESS(0, "登陆成功"),
	LOGIN_ERROR(1, "登陆失败");
	
	private UserLoginStatusEnum(Integer code, String desc) {
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
