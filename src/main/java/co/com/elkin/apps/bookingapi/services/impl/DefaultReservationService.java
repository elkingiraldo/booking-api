package co.com.elkin.apps.bookingapi.services.impl;

import java.sql.Timestamp;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.elkin.apps.bookingapi.dtos.BookingDTO;
import co.com.elkin.apps.bookingapi.entities.Reservation;
import co.com.elkin.apps.bookingapi.entities.User;
import co.com.elkin.apps.bookingapi.exception.APIServiceException;
import co.com.elkin.apps.bookingapi.repositories.ReservationRepository;
import co.com.elkin.apps.bookingapi.services.IReservationService;

@Service
public class DefaultReservationService implements IReservationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultReservationService.class);

	private final ReservationRepository repository;

	@Autowired
	public DefaultReservationService(final ReservationRepository repository) {
		this.repository = repository;
	}

	@Override
	public Reservation create(final BookingDTO bookingDTO, final User user, final float totalPrice)
			throws APIServiceException {
		LOGGER.info("[DefaultReservationService][create]");

		var tsCurrent = Timestamp.from(Instant.now());

		var reservation = Reservation.builder().startDate(bookingDTO.getStartDate()).endDate(bookingDTO.getEndDate())
				.tsCreated(tsCurrent).tsUpdated(tsCurrent).totalPrice(totalPrice).user(user).build();

		var reservationCreated = repository.save(reservation);

		return reservationCreated;
	}

}
