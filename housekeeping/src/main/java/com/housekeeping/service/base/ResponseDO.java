package com.housekeeping.service.base;


/**
 * @Type ResponseDO
 * @Desc 
 */
public class ResponseDO<T> {

    /**
     * 返回code 0：成�? 1：业务错�? （有异常，直接抛出自定义异常，框架层捕获处理�?
     */
    private String code = ResponseCode.SUCCESS;

    /**
     * 消息
     */
    private String message;

    private T dataResult;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDataResult() {
        return dataResult;
    }

    public void setDataResult(T dataResult) {
        this.dataResult = dataResult;
    }

    public void setResult(String code, String message) {
        this.message = message;
        this.code = code;
    }

    public void setResult(String code, String message, T dataResult) {
        this.message = message;
        this.code = code;
        this.dataResult = dataResult;
    }

    public boolean isSuccess() {
        return ResponseCode.isSuccess(code);
    }
}
