package co.com.elkin.apps.bookingapi.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.com.elkin.apps.bookingapi.dtos.UserDTO;
import co.com.elkin.apps.bookingapi.exception.APIServiceException;
import co.com.elkin.apps.bookingapi.exception.impl.APIServiceErrorCodes;
import co.com.elkin.apps.bookingapi.repositories.UserRepository;
import co.com.elkin.apps.bookingapi.services.IUserService;
import co.com.elkin.apps.bookingapi.services.converters.IUserConverterService;

@Service
public class DefaultUserService implements IUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUserService.class);

	private static final String NICKNAME_NULL = "nickname null";

	private static final String NAME_NULL = "name null";

	private static final String LAST_NAME_NULL = "lastName null";

	private final UserRepository userRepository;

	private final IUserConverterService userConverterService;

	@Autowired
	public DefaultUserService(final UserRepository userRepository, final IUserConverterService userConverterService) {
		this.userRepository = userRepository;
		this.userConverterService = userConverterService;
	}

	@Override
	public UserDTO create(final UserDTO userDTO) throws APIServiceException {
		LOGGER.info("[DefaultUserService][create]");
		validateDTO(userDTO);

		var optionalUser = userRepository.findByNickname(userDTO.getNickname());
		if (optionalUser.isPresent()) {
			throw new APIServiceException(HttpStatus.CONFLICT.getReasonPhrase(),
					APIServiceErrorCodes.USER_ALREADY_CREATED_EXCEPTION);
		}
		var createdUser = userRepository.save(userConverterService.toEntity(userDTO));

		return userConverterService.toDTO(createdUser);
	}

	@Override
	public UserDTO retrieveByNickname(final String nickname) throws APIServiceException {
		LOGGER.info("[DefaultUserService][retrieveByNickname][" + nickname + "]");
		var optionalUser = userRepository.findByNickname(nickname);

		if (optionalUser.isEmpty()) {
			throw new APIServiceException(HttpStatus.NOT_FOUND.getReasonPhrase(),
					APIServiceErrorCodes.USER_NOT_FOUND_EXCEPTION);
		}

		return userConverterService.toDTO(optionalUser.get());
	}

	private void validateDTO(final UserDTO userDTO) throws APIServiceException {
		if (StringUtils.isEmpty(userDTO.getNickname())) {
			throw new APIServiceException(NICKNAME_NULL, APIServiceErrorCodes.USER_NICKNAME_CANT_BE_EMPTY_EXCEPTION);
		}

		if (StringUtils.isEmpty(userDTO.getName())) {
			throw new APIServiceException(NAME_NULL, APIServiceErrorCodes.USER_NAME_CANT_BE_EMPTY_EXCEPTION);
		}

		if (StringUtils.isEmpty(userDTO.getLastName())) {
			throw new APIServiceException(LAST_NAME_NULL, APIServiceErrorCodes.USER_LAST_NAME_CANT_BE_EMPTY_EXCEPTION);
		}
	}
}
