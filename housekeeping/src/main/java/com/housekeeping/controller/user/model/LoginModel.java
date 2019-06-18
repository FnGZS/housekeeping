package com.housekeeping.controller.user.model;

import com.housekeeping.controller.base.AbstractFlagModel;

public class LoginModel extends AbstractFlagModel{
	
	private String openId;
	private String userName;
	private String userKey;
	private String avatar;
	private String authorization;
	private Integer sex;
	private Long userId;
	private String openAccount;
	private String phone;
	private Integer isbound;
	private String sessionKey;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getAuthorization() {
		return authorization;
	}
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getOpenAccount() {
		return openAccount;
	}
	public void setOpenAccount(String openAccount) {
		this.openAccount = openAccount;
	}
	public Integer getIsbound() {
		return isbound;
	}
	public void setIsbound(Integer isbound) {
		this.isbound = isbound;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
}
