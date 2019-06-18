package com.housekeeping.model.enums;

import com.housekeeping.exception.UploadException;

/**
 * @Type UploadBizTypeEnum.java
 * @Desc 
 */
public enum UploadBizTypeEnum {

	// 用户模块
	USER_AVATAR(0, "useravatar/%s/%s.%s"),
	USER(1, "user/%s/%s.%s"),

	// 小程序模�?
	WEIXIN_A_CODE(50, "weixinACode/%s.%s"),
	
	//纺织圈模�?
	CIRCLE_FACTORY_PIC_NEW(60,"circle/%s/factory/%s.%s"),
	CIRCLE_FACTORY_PIC_UPDATE(61,"circle/%s/factory/%s.%s"),
	CIRCLE_FACTORY_LICENSE(62,"circle/%s/license/%s.%s"),
	
	USER_QR_MYCARD(92, "qrcode/%s/mycard/%s.%s"),
	USER_QR_CONTACT(93, "qrcode/%s/contact/%s.%s");
	
	
	private UploadBizTypeEnum(Integer code, String path) {
		this.code = code;
		this.path = path;
	}

	private Integer code;

	private String path;

	public Integer getCode() {
		return code;
	}

	public String getPath() {
		return path;
	}
	
	public static String getPathBycode(Integer code) throws UploadException{
		for (UploadBizTypeEnum bizTypeEnum : UploadBizTypeEnum.values()) {
			if (code.equals(bizTypeEnum.getCode())) {
				return bizTypeEnum.getPath();
			}
		}
		throw new UploadException("不支持该业务类型的图片上�?");
	}
}
