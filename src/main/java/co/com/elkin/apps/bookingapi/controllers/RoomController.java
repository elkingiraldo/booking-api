package co.com.elkin.apps.bookingapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.elkin.apps.bookingapi.services.IRoomService;

@RestController
@CrossOrigin
@RequestMapping("/room")
public class RoomController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);

	private final IRoomService roomService;

	@Autowired
	public RoomController(final IRoomService roomService) {
		this.roomService = roomService;
	}

}
