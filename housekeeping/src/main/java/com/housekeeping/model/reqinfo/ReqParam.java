package com.housekeeping.model.reqinfo;

public class ReqParam {
    private String ip = "";
    private String url;
    private String userId = "";
    private String loginAccount = "";
    private String formParam = "";
    private ReqHead reqHead;

    public String getFormParam() {
        return formParam;
    }

    public void setFormParam(String formParam) {
        this.formParam = formParam;
    }

    public ReqHead getReqHead() {
        return reqHead;
    }

    public void setReqHead(ReqHead reqHead) {
        this.reqHead = reqHead;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

}
