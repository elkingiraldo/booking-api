package co.com.elkin.apps.bookingapi.controller;

import co.com.elkin.apps.bookingapi.dto.UserDTO;
import co.com.elkin.apps.bookingapi.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final IUserService userService;

    @Autowired
    public UserController(final IUserService userService) {
        this.userService = userService;
    }

    /**
     * @param userDTO this is a test parameter
     * @return some response description
     */
    @Operation(summary = "Create a new user",
            description = "This endpoint allows you to create new users")
    @PostMapping
    public ResponseEntity<UserDTO> createUser(
            @RequestBody final UserDTO userDTO,
            @RequestHeader(value = "locale", required = false) final String locale
    ) {
        LOGGER.info("[UserController][createUser]");
        var userCreated = userService.createUser(userDTO);

        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }
}
