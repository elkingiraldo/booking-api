package co.com.elkin.apps.bookingapi.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.elkin.apps.bookingapi.entities.Room;
import co.com.elkin.apps.bookingapi.repositories.RoomRepository;
import co.com.elkin.apps.bookingapi.services.IRoomService;

@Service
public class DefaultRoomService implements IRoomService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRoomService.class);

	private final RoomRepository repository;

	@Autowired
	public DefaultRoomService(final RoomRepository repository) {
		this.repository = repository;
	}

	@Override
	public Room getRoom() {
		LOGGER.info("[DefaultRoomService][getRoom]");
		var findAll = repository.findAll();
		return findAll.get(0);
	}

	@Override
	public float getCurrentPrice() {
		LOGGER.info("[DefaultRoomService][getCurrentPrice]");
		return getRoom().getCurrentPrice();
	}

}
