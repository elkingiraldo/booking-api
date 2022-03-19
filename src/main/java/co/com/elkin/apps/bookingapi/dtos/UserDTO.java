package co.com.elkin.apps.bookingapi.dtos;

import java.io.Serializable;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NonNull
	private String nickname;

	private String name;
	private String lastName;
}
