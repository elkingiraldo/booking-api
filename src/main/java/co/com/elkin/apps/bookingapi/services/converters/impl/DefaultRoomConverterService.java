package co.com.elkin.apps.bookingapi.services.converters.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.com.elkin.apps.bookingapi.dtos.RoomDTO;
import co.com.elkin.apps.bookingapi.entities.Room;
import co.com.elkin.apps.bookingapi.services.converters.IRoomConverterService;

@Service
public class DefaultRoomConverterService implements IRoomConverterService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRoomConverterService.class);

	@Override
	public RoomDTO toDTO(Room entity) {
		LOGGER.info("[DefaultRoomConverterService][toDTO]");
		final var modelMapper = new ModelMapper();
		return modelMapper.map(entity, RoomDTO.class);
	}

	@Override
	public Room toEntity(RoomDTO dto) {
		LOGGER.info("[DefaultRoomConverterService][toEntity]");
		final var modelMapper = new ModelMapper();
		final var entity = modelMapper.map(dto, Room.class);
		return entity;
	}

}
