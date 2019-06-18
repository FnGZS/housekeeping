package com.housekeeping.model.reqinfo;

public class ReqHead {
	
    private String accessToken = "";
    // 版本�?
    private String version = "";
    // 浏览�?
    private String browser = "";
    // 手机操作系统iphone android
    private String os = "";
    // 手机操作系统版本�?
    private String osVersion = "";

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		// 兼容IOS
		if(version.equals("2.4.1")) {
			this.version = "2.41";
			return ;
		}
		this.version = version;
	}
}
