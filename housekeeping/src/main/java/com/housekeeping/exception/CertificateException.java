package com.housekeeping.exception;

/**
 * @Type CertificateException
 * @Desc 安全认证异常�?
 */
public class CertificateException extends Exception {

	private static final long serialVersionUID = -2562466754273633486L;

	private String errCode;

	private String errMsg;

	public CertificateException() {
		super();
	}

	public CertificateException(String message, Throwable cause) {
		super(message, cause);
	}

	public CertificateException(String message) {
		super(message);
	}

	public CertificateException(Throwable cause) {
		super(cause);
	}

	public CertificateException(String errCode, String errMsg) {
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
