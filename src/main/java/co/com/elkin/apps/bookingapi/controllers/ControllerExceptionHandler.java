package co.com.elkin.apps.bookingapi.controllers;

import java.util.Locale;
import java.util.function.UnaryOperator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.com.elkin.apps.bookingapi.exception.APIServiceException;
import co.com.elkin.apps.bookingapi.exception.ServiceExceptionWrapper;
import co.com.elkin.apps.bookingapi.exception.impl.APIServiceErrorCodes;

/**
 * Class in charge of managing exceptions for communicating to the client
 * 
 * @author egiraldo
 *
 */
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String LOCALE_HEADER_NAME = "locale";
	private static final UnaryOperator<String> ERROR_MESSAGE = msg -> "Booking API service -> exception occurred"
			+ msg;

	@Value("${spring.application.name}")
	private String applicationName;

	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;

	/**
	 * Handle general exceptions into the system
	 * 
	 * @param ex,      Exception throws
	 * @param request, user's request
	 * @return {@link ServiceExceptionWrapper} with specific information of the
	 *         error
	 */
	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<ServiceExceptionWrapper> handleUnknownException(final Exception ex,
			final WebRequest request) {
		logger.error(ERROR_MESSAGE.apply(ex.getMessage()), ex);
		ex.printStackTrace();

		final var code = APIServiceErrorCodes.GENERAL_EXCEPTION.getMessage();

		final var locale = getLocale(request);

		final var errorMessage = messageSource.getMessage(code, null, locale);

		final var exception = new ServiceExceptionWrapper(code, errorMessage, null);

		return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handle API exceptions into the system
	 * 
	 * @param ex,      Exception throws
	 * @param request, user's request
	 * @return {@link ServiceExceptionWrapper} with specific information of the
	 *         error
	 */
	@ExceptionHandler(value = { APIServiceException.class })
	protected ResponseEntity<ServiceExceptionWrapper> handleAPIServiceException(final APIServiceException ex,
			final WebRequest request) {
		logger.error(ERROR_MESSAGE.apply(ex.getMessage()), ex);
		ex.printStackTrace();

		final var locale = getLocale(request);

		final var errorCode = ex.getCode().getMessage();

		final var errorMessage = messageSource.getMessage(ex.getCode().getMessage(), null, locale);

		String errorDetail = null;

		if (ex.getCode().getErrorDetail() != null) {
			errorDetail = messageSource.getMessage(ex.getCode().getErrorDetail(), null, locale);
		} else {
			errorDetail = ex.getMessage();
		}

		final var exception = new ServiceExceptionWrapper(errorCode, errorMessage, errorDetail,
				ex.getCode().getHttpStatus().value(), ((ServletWebRequest) request).getRequest().getRequestURI());

		exception.setErrorOriginApp(applicationName);

		return new ResponseEntity<>(exception, ex.getCode().getHttpStatus());
	}

	/**
	 * Obtain the locale the user required or taking the default value of EN
	 * 
	 * @param request, user's request
	 * @return {@link Locale}
	 */
	private Locale getLocale(final WebRequest request) {
		final String localeString = request.getHeader(LOCALE_HEADER_NAME);

		Locale locale = null;
		if (localeString != null) {
			locale = new Locale(localeString);
		}

		if (locale == null) {
			locale = LocaleContextHolder.getLocale();
		}
		return locale;
	}

}
