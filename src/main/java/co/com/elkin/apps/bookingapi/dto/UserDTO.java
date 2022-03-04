package co.com.elkin.apps.bookingapi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
}
