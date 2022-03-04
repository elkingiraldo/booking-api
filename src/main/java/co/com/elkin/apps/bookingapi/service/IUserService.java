package co.com.elkin.apps.bookingapi.service;

import co.com.elkin.apps.bookingapi.dto.UserDTO;

public interface IUserService {

    UserDTO createUser(final UserDTO userDTO);
}
