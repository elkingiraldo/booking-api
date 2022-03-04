package co.com.elkin.apps.bookingapi.service;

import co.com.elkin.apps.bookingapi.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUserService.class);

    @Override
    public UserDTO createUser(final UserDTO userDTO) {
        LOGGER.info("[DefaultUserService][createUser]");
        var email = userDTO.getEmail();
        LOGGER.info(email);

        return userDTO;
    }
}
