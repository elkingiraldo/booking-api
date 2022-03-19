package co.com.elkin.apps.bookingapi.services.converters.impl;

import java.util.ArrayList;
import java.util.List;

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
	public List<UserDTO> toDtos(final List<User> entities) {
		LOGGER.info("[DefaultUserConverterService][toDTOs]");
		final var dtoList = new ArrayList<UserDTO>();

		for (final var User : entities) {
			final var modelMapper = new ModelMapper();
			dtoList.add(modelMapper.map(User, UserDTO.class));
		}
		return dtoList;
	}

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
