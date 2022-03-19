package co.com.elkin.apps.bookingapi.services.impl;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.com.elkin.apps.bookingapi.dtos.BookingDTO;
import co.com.elkin.apps.bookingapi.dtos.ReservationDTO;
import co.com.elkin.apps.bookingapi.exception.APIServiceException;
import co.com.elkin.apps.bookingapi.exception.impl.APIServiceErrorCodes;
import co.com.elkin.apps.bookingapi.services.IBookingService;
import co.com.elkin.apps.bookingapi.services.IReservationService;
import co.com.elkin.apps.bookingapi.services.IRoomReservedService;
import co.com.elkin.apps.bookingapi.services.IRoomService;
import co.com.elkin.apps.bookingapi.services.IUserService;
import co.com.elkin.apps.bookingapi.services.converters.IRoomConverterService;
import co.com.elkin.apps.bookingapi.services.converters.IUserConverterService;

@Service
public class DefaultBookingService implements IBookingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultBookingService.class);

	private static final int MAX_DAYS_RESERVATION_IN_ADVANCE = 30;

	private static final int MAX_DAYS_DURATION_RESERVATION = 3;

	private static final String START_DATE_NULL = "startDate null";

	private static final String END_DATE_NULL = "endDate null";

	private static final String NICKNAME_NULL = "nickname null";

	private final IUserService userService;

	private final IUserConverterService userConverterService;

	private final IRoomService roomService;

	private final IRoomConverterService roomConverterService;

	private final IReservationService reservationService;

	@Autowired
	public DefaultBookingService(final IUserService userService, final IUserConverterService userConverterService,
			final IRoomService roomService, final IRoomReservedService roomReservedService,
			final IRoomConverterService roomConverterService, final IReservationService reservationService) {
		this.userService = userService;
		this.userConverterService = userConverterService;
		this.roomService = roomService;
		this.roomConverterService = roomConverterService;
		this.reservationService = reservationService;
	}

	@Override
	public ReservationDTO create(final BookingDTO bookingDTO) throws APIServiceException {
		LOGGER.info("[DefaultBookingService][create]");

		validateDTO(bookingDTO);
		validateDates(bookingDTO);

		var price = getPrice(bookingDTO);
		var user = userService.retrieveEntityByNickname(bookingDTO.getNickname());
		var room = roomService.getRoom();

		var reservationCreated = reservationService.create(bookingDTO, user, price, room);

		return ReservationDTO.builder().startDate(reservationCreated.getStartDate())
				.endDate(reservationCreated.getEndDate()).reservationId(String.valueOf(reservationCreated.getId()))
				.totalPrice(price).room(roomConverterService.toDTO(room)).user(userConverterService.toDTO(user))
				.status(reservationCreated.getStatus()).build();
	}

	private void validateDTO(final BookingDTO bookingDTO) throws APIServiceException {
		if (Objects.nonNull(bookingDTO.getStartDate())
				&& StringUtils.isEmpty(String.valueOf(bookingDTO.getStartDate()))) {
			throw new APIServiceException(START_DATE_NULL, APIServiceErrorCodes.BOOKING_START_DATE_NULL_EXCEPTION);
		}

		if (Objects.nonNull(bookingDTO.getEndDate()) && StringUtils.isEmpty(String.valueOf(bookingDTO.getEndDate()))) {
			throw new APIServiceException(END_DATE_NULL, APIServiceErrorCodes.BOOKING_END_DATE_NULL_EXCEPTION);
		}

		if (StringUtils.isEmpty(bookingDTO.getNickname())) {
			throw new APIServiceException(NICKNAME_NULL, APIServiceErrorCodes.USER_NICKNAME_CANT_BE_EMPTY_EXCEPTION);
		}
	}

	private void validateDates(final BookingDTO bookingDTO) throws APIServiceException {
		var startDate = getOffsetDateTime(bookingDTO.getStartDate());
		var endDate = getOffsetDateTime(bookingDTO.getEndDate());

		if (startDate.isAfter(endDate)) {
			throw new APIServiceException(HttpStatus.BAD_REQUEST.getReasonPhrase(),
					APIServiceErrorCodes.BOOKING_START_DATE_AFTER_END_DATE_EXCEPTION);
		}

		if (getDifferenceDays(startDate, endDate) >= MAX_DAYS_DURATION_RESERVATION) {
			throw new APIServiceException(HttpStatus.BAD_REQUEST.getReasonPhrase(),
					APIServiceErrorCodes.BOOKING_MAX_DURATION_RESERVATION_EXCEPTION);
		}

		var today = getCurrentOffsetDateTime();
		var thirtyDaysLater = today.plusDays(MAX_DAYS_RESERVATION_IN_ADVANCE);
		if (getDifferenceDays(startDate, today) >= 0 || startDate.isAfter(thirtyDaysLater)) {
			throw new APIServiceException(HttpStatus.BAD_REQUEST.getReasonPhrase(),
					APIServiceErrorCodes.BOOKING_VALID_DAYS_RESERVATION_EXCEPTION);
		}
	}

	private float getPrice(final BookingDTO bookingDTO) {
		LOGGER.info("[DefaultBookingService][getPrice]");

		var differenceDays = getDifferenceDays(getOffsetDateTime(bookingDTO.getStartDate()),
				getOffsetDateTime(bookingDTO.getEndDate()));
		var currentRoomPrice = roomService.getCurrentPrice();

		return currentRoomPrice * (differenceDays + 1);
	}

	private OffsetDateTime getOffsetDateTime(final Date date) {
		LOGGER.info("[DefaultBookingService][getOffsetDateTime]");
		return date.toInstant().atZone(ZoneId.systemDefault()).plusDays(1).truncatedTo(ChronoUnit.DAYS)
				.toOffsetDateTime();
	}

	private OffsetDateTime getCurrentOffsetDateTime() {
		LOGGER.info("[DefaultBookingService][getCurrentOffsetDateTime]");
		return Instant.now().atZone(ZoneId.systemDefault()).truncatedTo(ChronoUnit.DAYS).toOffsetDateTime();
	}

	private long getDifferenceDays(final OffsetDateTime startDate, final OffsetDateTime endDate) {
		LOGGER.info("[DefaultBookingService][getDifferenceDays]");
		return startDate.until(endDate, ChronoUnit.DAYS);
	}

}
