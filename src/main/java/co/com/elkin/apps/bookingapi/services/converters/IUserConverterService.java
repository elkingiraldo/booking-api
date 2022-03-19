package co.com.elkin.apps.bookingapi.services.converters;

import java.util.List;

import co.com.elkin.apps.bookingapi.dtos.UserDTO;
import co.com.elkin.apps.bookingapi.entities.User;

public interface IUserConverterService {

	UserDTO toDTO(final User entity);

	User toEntity(final UserDTO dto);

	List<UserDTO> toDtos(final List<User> entities);
}
