package co.com.elkin.apps.bookingapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.elkin.apps.bookingapi.dtos.BookingDTO;
import co.com.elkin.apps.bookingapi.dtos.ReservationDTO;
import co.com.elkin.apps.bookingapi.exception.APIServiceException;
import co.com.elkin.apps.bookingapi.services.IBookingService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/booking")
public class BookingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

	private final IBookingService bookingService;

	@Autowired
	public BookingController(final IBookingService bookingService) {
		this.bookingService = bookingService;
	}

	/**
	 * @param bookingDTO this is a test parameter
	 * @return some response description
	 * @throws APIServiceException
	 */
	@Operation(summary = "Create a new reservation", description = "This endpoint allows you to create new reservation")
	@PostMapping
	public ResponseEntity<ReservationDTO> create(@RequestBody final BookingDTO bookingDTO,
			@RequestHeader(value = "locale", required = false) final String locale) throws APIServiceException {
		LOGGER.info("[BookingController][create]");
		var reservationCreated = bookingService.create(bookingDTO);

		return new ResponseEntity<>(reservationCreated, HttpStatus.CREATED);
	}

}
