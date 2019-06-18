package com.housekeeping.dao.user.dataobject;

public class AdminDO {

	private Long adminUser;
	private String password;
	private String adminName;

	public Long getAdminUser() {
		return adminUser;
	}
	public void setAdminUser(Long adminUser) {
		this.adminUser = adminUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
}
