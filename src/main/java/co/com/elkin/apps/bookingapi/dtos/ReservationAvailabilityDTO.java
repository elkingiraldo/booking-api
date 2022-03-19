package co.com.elkin.apps.bookingapi.dtos;

import java.io.Serializable;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReservationAvailabilityDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private OffsetDateTime date;
	private boolean disponible;
}
