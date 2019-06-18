package com.housekeeping.controller.user.model;

import com.housekeeping.controller.base.AbstractFlagModel;


public class UserModel extends AbstractFlagModel {

	private Long userId;
	private String loginAccount;
    private String userName;
    
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLoginAccount() {
		return loginAccount;
	}
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
