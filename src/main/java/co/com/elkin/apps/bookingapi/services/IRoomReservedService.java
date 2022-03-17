package co.com.elkin.apps.bookingapi.services;

import co.com.elkin.apps.bookingapi.entities.RoomReserved;
import co.com.elkin.apps.bookingapi.exception.APIServiceException;

public interface IRoomReservedService {

	RoomReserved create(final RoomReserved roomReserved) throws APIServiceException;
}
