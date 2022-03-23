package co.com.elkin.apps.bookingapi.dtos;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CancelReservationDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String reservationId;
	private String nickname;
}
