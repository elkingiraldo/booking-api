package co.com.elkin.apps.bookingapi.services;

import co.com.elkin.apps.bookingapi.dtos.UserDTO;
import co.com.elkin.apps.bookingapi.entities.User;
import co.com.elkin.apps.bookingapi.exception.APIServiceException;

public interface IUserService {

	UserDTO create(final UserDTO userDTO) throws APIServiceException;
    
    UserDTO retrieveByNickname(final String nickname) throws APIServiceException;
    
    User retrieveEntityByNickname(final String nickname) throws APIServiceException;
}
