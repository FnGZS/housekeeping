package com.housekeeping.exception;

/**
 * @Type UploadException
 * @Desc 上传异常�?
 */
public class UploadException extends Exception{

	private static final long serialVersionUID = -3737716222270878848L;

	private String errCode;

	private String errMsg;

	public UploadException() {
		super();
	}

	public UploadException(String message, Throwable cause) {
		super(message, cause);
	}

	public UploadException(String message) {
		super(message);
	}

	public UploadException(Throwable cause) {
		super(cause);
	}

	public UploadException(String errCode, String errMsg) {
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
