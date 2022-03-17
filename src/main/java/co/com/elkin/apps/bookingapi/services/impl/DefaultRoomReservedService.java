package co.com.elkin.apps.bookingapi.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.elkin.apps.bookingapi.entities.Reservation;
import co.com.elkin.apps.bookingapi.entities.RoomReserved;
import co.com.elkin.apps.bookingapi.exception.APIServiceException;
import co.com.elkin.apps.bookingapi.repositories.RoomReservedRepository;
import co.com.elkin.apps.bookingapi.services.IRoomReservedService;
import co.com.elkin.apps.bookingapi.services.IRoomService;

@Service
public class DefaultRoomReservedService implements IRoomReservedService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRoomReservedService.class);

	private final RoomReservedRepository repository;

	private final IRoomService roomService;

	@Autowired
	public DefaultRoomReservedService(final RoomReservedRepository repository, final IRoomService roomService) {
		this.repository = repository;
		this.roomService = roomService;
	}

	@Override
	public RoomReserved create(final Reservation reservation, final float price) throws APIServiceException {
		LOGGER.info("[DefaultRoomReservedService][create]");

		var roomReserved = RoomReserved.builder().reservation(reservation).room(roomService.getRoom()).price(price)
				.build();

		var roomReservedCreated = repository.save(roomReserved);

		return roomReservedCreated;
	}

}
