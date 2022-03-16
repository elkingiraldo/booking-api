package co.com.elkin.apps.bookingapi.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.elkin.apps.bookingapi.dtos.UserDTO;
import co.com.elkin.apps.bookingapi.repositories.UserRepository;
import co.com.elkin.apps.bookingapi.services.IUserService;
import co.com.elkin.apps.bookingapi.services.converters.IUserConverterService;

@Service
public class DefaultUserService implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUserService.class);
    
    private final UserRepository userRepository;
    
    private final IUserConverterService userConverterService;

	@Autowired
	public DefaultUserService(
			final UserRepository userRepository,
			final IUserConverterService userConverterService
	) {
		this.userRepository = userRepository;
		this.userConverterService = userConverterService;
	}

    @Override
    public UserDTO create(final UserDTO userDTO) {
        LOGGER.info("[DefaultUserService][create]");
        var email = userDTO.getEmail();
        LOGGER.info(email);

        return userDTO;
    }

	@Override
	public UserDTO retrieveByEmail(final String email) {
		LOGGER.info("[DefaultUserService][retrieveByEmail]"+email);
		var optionalUser = userRepository.findByEmail(email);
		
		if (optionalUser.isEmpty()) {
			return null;
		}
		
		return userConverterService.toDTO(optionalUser.get());
	}
}
