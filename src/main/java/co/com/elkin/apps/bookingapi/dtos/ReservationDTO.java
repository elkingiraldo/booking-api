package co.com.elkin.apps.bookingapi.dtos;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;

@Builder
public class ReservationDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date startDate;
	private Date endDate;
}
