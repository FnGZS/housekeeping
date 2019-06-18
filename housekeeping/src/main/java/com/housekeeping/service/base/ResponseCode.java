package com.housekeeping.service.base;

/**
 * @Type ResponseCode
 * @Desc 返回代码定义
 */
public class ResponseCode {

    /**
     * 返回code 0：成�?
     */
    public static final String SUCCESS = "0";

    /**
     * 返回code 2：业务错�?
     */
    public static final String ERROR = "-1";

    /**
     * 判断是否成功
     * 
     * @param code
     * @return
     */
    public static boolean isSuccess(String code) {
        return SUCCESS.equals(code);
    }
}
