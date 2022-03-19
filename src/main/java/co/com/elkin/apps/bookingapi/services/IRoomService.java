package co.com.elkin.apps.bookingapi.services;

import co.com.elkin.apps.bookingapi.entities.Room;

public interface IRoomService {

	Room getRoom();

	float getCurrentPrice();
}
