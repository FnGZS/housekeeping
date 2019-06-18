package com.housekeeping.dao.user.dataobject;

import java.util.Date;

/**
 * @Type UserLoginDO
 * @Desc 
 * @author luogm
 * @date 2016�?8�?20�?
 * @Version V1.0
 */
public class UserLoginDO {
	
	private Long id;
	private String openId;
	private String loginAccount;
	private String headimgurl;
	private String unionId;
	private String telephone;
	private Integer sex;
	private String accessToken;
	private Integer signInCount;
	private Integer isBound;
	private Date gmtCreated;
	private Date gmtModified;


    public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

	public Integer getSignInCount() {
		return signInCount;
	}

	public void setSignInCount(Integer signInCount) {
		this.signInCount = signInCount;
	}

	public Integer getIsBound() {
		return isBound;
	}

	public void setIsBound(Integer isBound) {
		this.isBound = isBound;
	}

	public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}