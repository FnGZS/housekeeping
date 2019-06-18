package com.housekeeping.dao.user.dataobject;

/**
 * @Type UserLoginDO
 * @Desc 
 * @author luogm
 * @date 2016�?8�?20�?
 * @Version V1.0
 */
public class UserLoginPO {
	
    private Long userId;

    private Integer deviceType;
    
   /* public UserLoginPO(Long userId, Integer deviceType) {
    	this.userId = userId;
    	this.deviceType = deviceType;
    }*/
    
    public UserLoginPO(Long userId) {
    	this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
}