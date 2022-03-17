package co.com.elkin.apps.bookingapi.services.impl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.elkin.apps.bookingapi.dtos.BookingDTO;
import co.com.elkin.apps.bookingapi.dtos.ReservationDTO;
import co.com.elkin.apps.bookingapi.entities.RoomReserved;
import co.com.elkin.apps.bookingapi.exception.APIServiceException;
import co.com.elkin.apps.bookingapi.services.IBookingService;
import co.com.elkin.apps.bookingapi.services.IReservationService;
import co.com.elkin.apps.bookingapi.services.IRoomService;
import co.com.elkin.apps.bookingapi.services.IUserService;

@Service
public class DefaultBookingService implements IBookingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultBookingService.class);

	private final IUserService userService;

	private final IRoomService roomService;

	private final IReservationService reservationService;

	@Autowired
	public DefaultBookingService(final IUserService userService, final IRoomService roomService,
			final IReservationService reservationService) {
		this.userService = userService;
		this.roomService = roomService;
		this.reservationService = reservationService;
	}

	@Override
	public ReservationDTO create(final BookingDTO bookingDTO) throws APIServiceException {
		LOGGER.info("[DefaultBookingService][create]");

		var user = userService.retrieveEntityByNickname(bookingDTO.getNickname());
		var roomReserved = RoomReserved.builder().room(roomService.getRoom()).price(getPrice(bookingDTO)).build();

		return reservationService.create(bookingDTO, user, roomReserved);
	}

	private float getPrice(final BookingDTO bookingDTO) {
		LOGGER.info("[DefaultBookingService][getPrice]");
		var differenceDays = getDifferenceDays(bookingDTO.getStartDate(), bookingDTO.getEndDate());
		var currentRoomPrice = roomService.getCurrentPrice();

		return currentRoomPrice * (differenceDays + 1);
	}

	private long getDifferenceDays(final Date startDate, final Date endDate) {
		LOGGER.info("[DefaultBookingService][getDifferenceDays]");
		long diff = endDate.getTime() - startDate.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

}
