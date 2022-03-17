package co.com.elkin.apps.bookingapi.services.impl;

import java.sql.Timestamp;
import java.time.Instant;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.elkin.apps.bookingapi.dtos.BookingDTO;
import co.com.elkin.apps.bookingapi.entities.Reservation;
import co.com.elkin.apps.bookingapi.entities.Room;
import co.com.elkin.apps.bookingapi.entities.RoomReserved;
import co.com.elkin.apps.bookingapi.entities.User;
import co.com.elkin.apps.bookingapi.exception.APIServiceException;
import co.com.elkin.apps.bookingapi.repositories.ReservationRepository;
import co.com.elkin.apps.bookingapi.services.IReservationService;
import co.com.elkin.apps.bookingapi.services.IRoomReservedService;

@Service
public class DefaultReservationService implements IReservationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultReservationService.class);

	private final ReservationRepository repository;

	private final IRoomReservedService roomReservedService;

	@Autowired
	public DefaultReservationService(final ReservationRepository repository,
			final IRoomReservedService roomReservedService) {
		this.repository = repository;
		this.roomReservedService = roomReservedService;
	}

	@Transactional
	@Override
	public Reservation create(final BookingDTO bookingDTO, final User user, final float totalPrice, final Room room)
			throws APIServiceException {
		LOGGER.info("[DefaultReservationService][create]");

		var tsCurrent = Timestamp.from(Instant.now());

		var reservation = Reservation.builder().startDate(bookingDTO.getStartDate()).endDate(bookingDTO.getEndDate())
				.tsCreated(tsCurrent).tsUpdated(tsCurrent).totalPrice(totalPrice).user(user).build();
		var reservationSaved = repository.saveAndFlush(reservation);

		var roomReserved = RoomReserved.builder().room(room).price(totalPrice).reservation(reservationSaved).build();
		roomReservedService.create(roomReserved);

		return reservationSaved;
	}

}
