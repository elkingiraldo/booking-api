package co.com.elkin.apps.bookingapi.dtos;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import co.com.elkin.apps.bookingapi.constants.Constant;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReservationDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = Constant.DATE_JSON_FORMAT)
	private final Date startDate;

	@JsonFormat(pattern = Constant.DATE_JSON_FORMAT)
	private final Date endDate;

	private final String reservationId;
	private final Float totalPrice;
	private final RoomDTO room;
	private final UserDTO user;
}
