package co.com.elkin.apps.bookingapi.services.converters;

import co.com.elkin.apps.bookingapi.dtos.UserDTO;
import co.com.elkin.apps.bookingapi.entities.User;

public interface IUserConverterService {

	UserDTO toDTO(final User entity);
	
	User toEntity(final UserDTO dto);
}
