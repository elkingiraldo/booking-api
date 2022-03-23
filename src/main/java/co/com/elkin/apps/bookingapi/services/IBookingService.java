package co.com.elkin.apps.bookingapi.services;

import java.time.LocalDate;
import java.util.List;

import co.com.elkin.apps.bookingapi.dtos.BookingDTO;
import co.com.elkin.apps.bookingapi.dtos.CancelReservationDTO;
import co.com.elkin.apps.bookingapi.dtos.ReservationDTO;
import co.com.elkin.apps.bookingapi.exception.APIServiceException;

public interface IBookingService {

	ReservationDTO create(final BookingDTO bookingDTO) throws APIServiceException;

	List<LocalDate> searchAvailability();

	ReservationDTO cancelReservation(final CancelReservationDTO cancelReservationDTO) throws APIServiceException;
}
