package com.housekeeping.exception;

/**
 * @Type ForceExitException
 * @Desc 强制�?出异�?
 */
public class ForceExitException extends Exception {

	private static final long serialVersionUID = 1222177180474448468L;

	private String errCode;

	private String errMsg;

	public ForceExitException() {
		super();
	}

	public ForceExitException(String message, Throwable cause) {
		super(message, cause);
	}

	public ForceExitException(String message) {
		super(message);
	}

	public ForceExitException(Throwable cause) {
		super(cause);
	}

	public ForceExitException(String errCode, String errMsg) {
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
