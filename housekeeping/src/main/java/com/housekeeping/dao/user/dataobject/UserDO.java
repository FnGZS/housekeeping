package com.housekeeping.dao.user.dataobject;

public class UserDO {

	private Long id;
	private Long schoolNum;
	private String userName;
	private String password;
	private String openId;
	private String phone;
	private String isBinding;
	private Integer sex;
	private String headimgurl;
	

	public Long getSchoolNum() {
		return schoolNum;
	}
	public void setSchoolNum(Long schoolNum) {
		this.schoolNum = schoolNum;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIsBinding() {
		return isBinding;
	}
	public void setIsBinding(String isBinding) {
		this.isBinding = isBinding;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
