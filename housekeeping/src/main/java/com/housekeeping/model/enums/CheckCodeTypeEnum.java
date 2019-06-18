package com.housekeeping.model.enums;

/**
 * @Desc 验证码类型枚�?
 */
public enum CheckCodeTypeEnum {
	
    REGISTER(0, "用户注册"),
	CHANGE_PASSWORD(1, "修改密码"),
	BIND_MOBILE(2, "绑定手机�?");

	private CheckCodeTypeEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	private Integer code;
	
	private String message;

	public Integer getCode() {
		return this.code;
	}

	public void setCodeEnum(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}
	
}
