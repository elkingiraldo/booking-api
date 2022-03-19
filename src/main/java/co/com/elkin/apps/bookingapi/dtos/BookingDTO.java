package co.com.elkin.apps.bookingapi.dtos;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import co.com.elkin.apps.bookingapi.constants.Constant;
import lombok.Getter;

@Getter
public class BookingDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = Constant.DATE_JSON_FORMAT)
	private Date startDate;

	@JsonFormat(pattern = Constant.DATE_JSON_FORMAT)
	private Date endDate;

	private String nickname;
}
