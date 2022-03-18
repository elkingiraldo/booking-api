package co.com.elkin.apps.bookingapi.exception.impl;

import org.springframework.http.HttpStatus;

import co.com.elkin.apps.bookingapi.exception.IAPIServiceErrorMsg;

/**
 * API error codes
 * 
 * @author egiraldo
 */
public enum APIServiceErrorCodes implements IAPIServiceErrorMsg {

	GENERAL_EXCEPTION("general.exception", HttpStatus.INTERNAL_SERVER_ERROR),

	BOOKING_END_DATE_NULL_EXCEPTION("booking.end.date.validation.exception", HttpStatus.UNPROCESSABLE_ENTITY),
	BOOKING_MAX_DURATION_RESERVATION_EXCEPTION("booking.max.duration.reservation.exception", HttpStatus.BAD_REQUEST),
	BOOKING_NICKNAME_CANT_BE_EMPTY_EXCEPTION("booking.nickname.cant.be.empty.exception",
			HttpStatus.UNPROCESSABLE_ENTITY),
	BOOKING_START_DATE_AFTER_END_DATE_EXCEPTION("booking.start.date.after.end.date.exception", HttpStatus.BAD_REQUEST),
	BOOKING_START_DATE_NULL_EXCEPTION("booking.start.date.validation.exception", HttpStatus.UNPROCESSABLE_ENTITY),
	BOOKING_VALID_DAYS_RESERVATION_EXCEPTION("booking.valid.days.reservation.exception", HttpStatus.BAD_REQUEST),

	USER_ALREADY_CREATED_EXCEPTION("user.already.created.exception", HttpStatus.CONFLICT),
	USER_SHOULD_BE_REGISTERED_EXCEPTION(
			"user.should.be.registered.exception", HttpStatus.NOT_FOUND),
	USER_LAST_NAME_CANT_BE_EMPTY_EXCEPTION("user.last.name.cant.be.empty.exception", HttpStatus.UNPROCESSABLE_ENTITY),
	USER_NAME_CANT_BE_EMPTY_EXCEPTION("user.name.cant.be.empty.exception", HttpStatus.UNPROCESSABLE_ENTITY),
	USER_NICKNAME_CANT_BE_EMPTY_EXCEPTION("user.nickname.cant.be.empty.exception", HttpStatus.UNPROCESSABLE_ENTITY),
	USER_NOT_FOUND_EXCEPTION("user.not.found.exception", HttpStatus.NOT_FOUND);

	private String message;

	private HttpStatus httpStatus;

	private String errorDetail;

	private APIServiceErrorCodes(final String message, final HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}

	private APIServiceErrorCodes(final String message, final String errorDetail, final HttpStatus httpStatus) {
		this.message = message;
		this.errorDetail = errorDetail;
		this.httpStatus = httpStatus;
	}

	private APIServiceErrorCodes(final String message) {
		this.message = message;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
