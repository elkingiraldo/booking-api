package co.com.elkin.apps.bookingapi.services.converters;

import co.com.elkin.apps.bookingapi.dtos.RoomDTO;
import co.com.elkin.apps.bookingapi.entities.Room;

public interface IRoomConverterService {

	RoomDTO toDTO(final Room entity);

	Room toEntity(final RoomDTO dto);
}
