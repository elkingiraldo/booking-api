package co.com.elkin.apps.bookingapi.services;

import co.com.elkin.apps.bookingapi.dtos.BookingDTO;
import co.com.elkin.apps.bookingapi.dtos.ReservationDTO;
import co.com.elkin.apps.bookingapi.entities.RoomReserved;
import co.com.elkin.apps.bookingapi.entities.User;
import co.com.elkin.apps.bookingapi.exception.APIServiceException;

public interface IReservationService {

	ReservationDTO create(final BookingDTO bookingDTO, User user, final RoomReserved roomReserved)
			throws APIServiceException;
}
