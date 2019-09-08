package com.houseWork.entity.weixin;

import lombok.Data;

import java.util.Map;


@Data
public class UserPayParam {
	private String platCode;
	private Map<String, String> platUserInfoMap;
	private double fee;


}
