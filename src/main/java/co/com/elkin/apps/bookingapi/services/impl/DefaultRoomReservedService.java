package co.com.elkin.apps.bookingapi.services.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.elkin.apps.bookingapi.entities.RoomReserved;
import co.com.elkin.apps.bookingapi.exception.APIServiceException;
import co.com.elkin.apps.bookingapi.repositories.RoomReservedRepository;
import co.com.elkin.apps.bookingapi.services.IRoomReservedService;

@Service
public class DefaultRoomReservedService implements IRoomReservedService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRoomReservedService.class);

	private final RoomReservedRepository repository;

	@Autowired
	public DefaultRoomReservedService(final RoomReservedRepository repository) {
		this.repository = repository;
	}

	@Transactional
	@Override
	public RoomReserved create(final RoomReserved roomReserved) throws APIServiceException {
		LOGGER.info("[DefaultRoomReservedService][create]");
		return repository.save(roomReserved);
	}

}
