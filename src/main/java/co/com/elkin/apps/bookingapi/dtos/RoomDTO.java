package co.com.elkin.apps.bookingapi.dtos;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoomDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private float currentPrice;
}
