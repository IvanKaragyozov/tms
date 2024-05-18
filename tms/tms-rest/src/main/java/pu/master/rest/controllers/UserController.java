package pu.master.rest.controllers;


import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pu.master.core.services.UserService;
import pu.master.domain.models.dtos.UserDto;
import pu.master.domain.models.requests.LoginRequest;
import pu.master.domain.models.requests.UserRequest;


@RestController
public class UserController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;


    @Autowired
    public UserController(final UserService userService)
    {
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid final LoginRequest loginRequest)
    {
        final HttpCookie cookie = this.userService.login(loginRequest);
        LOGGER.debug(String.format("Requesting login for user with username: [%s]", loginRequest.getUsername()));

        return ResponseEntity.status(HttpStatus.OK)
                             .header(HttpHeaders.SET_COOKIE, cookie.toString())
                             .build();
    }


    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody @Valid final UserRequest userRequest)
    {
        final HttpCookie cookie = this.userService.registerUser(userRequest);
        LOGGER.info(String.format("Submitting registration request for user with username: [%s]", userRequest.getUsername()));

        return ResponseEntity.status(HttpStatus.CREATED)
                             .header(HttpHeaders.SET_COOKIE, cookie.toString())
                             .build();
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        final List<UserDto> allUsers = this.userService.getAllUserDtos();
        LOGGER.info("Requesting all users from the database");

        return ResponseEntity.ok(allUsers);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable final long id)
    {
        LOGGER.info(String.format("Sending request to user with id [%d]", id));
        final UserDto userDto = this.userService.getUserDtoById(id);

        return ResponseEntity.ok(userDto);
    }


    @GetMapping(value = "/users", params = "username")
    public ResponseEntity<UserDto> getUserByUsername(@RequestParam final String username)
    {
        LOGGER.info(String.format("Sending request to user with username [%s]", username));
        final UserDto userDto = this.userService.getUserDtoByUsername(username);

        return ResponseEntity.ok(userDto);
    }

}
