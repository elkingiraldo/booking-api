package co.com.elkin.apps.bookingapi.services;

import co.com.elkin.apps.bookingapi.dtos.BookingDTO;
import co.com.elkin.apps.bookingapi.entities.Reservation;
import co.com.elkin.apps.bookingapi.entities.Room;
import co.com.elkin.apps.bookingapi.entities.User;
import co.com.elkin.apps.bookingapi.exception.APIServiceException;

public interface IReservationService {

	Reservation create(final BookingDTO bookingDTO, User user, final float totalPrice, final Room room)
			throws APIServiceException;
}
