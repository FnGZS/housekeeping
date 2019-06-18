package com.housekeeping.model.enums;

/**
 * @Type DocumentTypeEnum.java
 * @Desc
 */

public enum DocumentTypeEnum {
    
	PICTURE(0, "图片"),
	VOICE(1, "语音"),
	DOCUMENT(2, "文档");
	
	private DocumentTypeEnum(Integer code, String desc) {
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
