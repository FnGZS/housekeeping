package com.housekeeping.model.reqinfo;

public class ReqInfo {
    private ReqParam reqParam;
    private Object respBody;
    // 接口请求耗时
    private long respTime;

    public long getRespTime() {
        return respTime;
    }

    public void setRespTime(long respTime) {
        this.respTime = respTime;
    }

    public ReqParam getReqParam() {
        return reqParam;
    }

    public void setReqParam(ReqParam reqParam) {
        this.reqParam = reqParam;
    }

    public Object getRespBody() {
        return respBody;
    }

    public void setRespBody(Object respBody) {
        this.respBody = respBody;
    }

}
