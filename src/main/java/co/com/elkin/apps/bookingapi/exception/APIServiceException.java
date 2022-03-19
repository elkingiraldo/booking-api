package co.com.elkin.apps.bookingapi.exception;

import co.com.elkin.apps.bookingapi.exception.impl.APIServiceErrorCodes;

/**
 * Service exception
 * 
 * @author egiraldo
 */
public class APIServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	private final APIServiceErrorCodes code;

	public APIServiceException(final APIServiceErrorCodes code) {
		super(code.getErrorDetail());
		this.code = code;
	}

	public APIServiceException(final String message, final Throwable cause, final APIServiceErrorCodes code) {
		super(message, cause);
		this.code = code;
	}

	public APIServiceException(final String message, final APIServiceErrorCodes code) {
		super(message);
		this.code = code;
	}

	public APIServiceException(final Throwable cause, final APIServiceErrorCodes code) {
		super(code.getErrorDetail(), cause);
		this.code = code;
	}

	public APIServiceErrorCodes getCode() {
		return code;
	}

}
