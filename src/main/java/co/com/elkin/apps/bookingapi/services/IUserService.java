package co.com.elkin.apps.bookingapi.services;

import co.com.elkin.apps.bookingapi.dtos.UserDTO;
import co.com.elkin.apps.bookingapi.exception.APIServiceException;

public interface IUserService {

    UserDTO create(final UserDTO userDTO);
    
    UserDTO retrieveByEmail(final String email) throws APIServiceException;
}
