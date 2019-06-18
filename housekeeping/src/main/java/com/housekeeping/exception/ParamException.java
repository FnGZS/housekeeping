package com.housekeeping.exception;

/**
 * @Type ParamException
 * @Desc 参数异常�?
 */
public class ParamException extends Exception{

	private static final long serialVersionUID = -3737716222270878848L;

	private String errCode;

	private String errMsg;

	public ParamException() {
		super();
	}

	public ParamException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParamException(String message) {
		super(message);
	}

	public ParamException(Throwable cause) {
		super(cause);
	}

	public ParamException(String errCode, String errMsg) {
		super(errCode + ":" + errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return this.errCode;
	}

	public String getErrMsg() {
		return this.errMsg;
	}
}
