package co.com.elkin.apps.bookingapi.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import co.com.elkin.apps.bookingapi.constants.Constant;
import lombok.Getter;

@Getter
public class BookingDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = Constant.DATE_JSON_FORMAT)
	@NotNull
	private Date startDate;

	@JsonFormat(pattern = Constant.DATE_JSON_FORMAT)
	@NotNull
	private Date endDate;

	@NotNull
	private String nickname;
}
