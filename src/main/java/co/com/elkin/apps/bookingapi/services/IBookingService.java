package co.com.elkin.apps.bookingapi.services;

import co.com.elkin.apps.bookingapi.dtos.BookingDTO;
import co.com.elkin.apps.bookingapi.dtos.ReservationDTO;
import co.com.elkin.apps.bookingapi.exception.APIServiceException;

public interface IBookingService {

	ReservationDTO create(final BookingDTO bookingDTO) throws APIServiceException;
}
