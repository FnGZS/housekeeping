package com.housekeeping.exception;

/**
 * @Type AuthorityException
 * @Desc 权限异常�?
 */
public class AuthorityException extends Exception{

	private static final long serialVersionUID = -1771842561388529342L;

	private String errCode;

	private String errMsg;

	public AuthorityException() {
		super();
	}

	public AuthorityException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthorityException(String message) {
		super(message);
	}

	public AuthorityException(Throwable cause) {
		super(cause);
	}

	public AuthorityException(String errCode, String errMsg) {
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
