package co.com.elkin.apps.bookingapi.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nickname;
    private String name;
    private String lastName;
}
