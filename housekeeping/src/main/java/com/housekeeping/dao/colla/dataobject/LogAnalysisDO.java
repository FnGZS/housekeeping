package com.housekeeping.dao.colla.dataobject;

/**
 * @Type LogAnalysisDO.java
 */
public class LogAnalysisDO {

	private Long id;
	
	private String ip;
	
	private Long userId;
	
	private String url;
	
	private Long respTime;
	
	private String respCode; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getRespTime() {
		return respTime;
	}

	public void setRespTime(Long respTime) {
		this.respTime = respTime;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
}
