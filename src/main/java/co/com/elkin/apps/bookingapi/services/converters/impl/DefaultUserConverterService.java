package co.com.elkin.apps.bookingapi.services.converters.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.com.elkin.apps.bookingapi.dtos.UserDTO;
import co.com.elkin.apps.bookingapi.entities.User;
import co.com.elkin.apps.bookingapi.services.converters.IUserConverterService;

@Service
public class DefaultUserConverterService implements IUserConverterService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUserConverterService.class);

	@Override
	public UserDTO toDTO(User entity) {
		LOGGER.info("[DefaultUserConverterService][toDTO]");
		final var modelMapper = new ModelMapper();
		return modelMapper.map(entity, UserDTO.class);
	}

	@Override
	public User toEntity(UserDTO dto) {
		LOGGER.info("[DefaultUserConverterService][toEntity]");
		final var modelMapper = new ModelMapper();
		final var entity = modelMapper.map(dto, User.class);
		return entity;
	}

}
