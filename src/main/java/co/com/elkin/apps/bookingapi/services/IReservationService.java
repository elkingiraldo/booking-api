package co.com.elkin.apps.bookingapi.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import co.com.elkin.apps.bookingapi.dtos.BookingDTO;
import co.com.elkin.apps.bookingapi.entities.Reservation;
import co.com.elkin.apps.bookingapi.entities.Room;
import co.com.elkin.apps.bookingapi.entities.User;
import co.com.elkin.apps.bookingapi.exception.APIServiceException;

public interface IReservationService {

	Reservation create(final BookingDTO bookingDTO, User user, final float totalPrice, final Room room)
			throws APIServiceException;

	List<LocalDate> retrieveAvailabilityRange(final Date startDate, final Date endDate);

	Reservation obtainById(final String id) throws APIServiceException;

	Reservation cancelReservation(final Reservation reservation) throws APIServiceException;
}
