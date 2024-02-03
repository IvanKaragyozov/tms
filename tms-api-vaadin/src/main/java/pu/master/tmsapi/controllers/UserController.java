package pu.master.tmsapi.controllers;


import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pu.master.tmsapi.models.dtos.UserDto;
import pu.master.tmsapi.models.entities.User;
import pu.master.tmsapi.models.requests.UserRequest;
import pu.master.tmsapi.services.UserService;


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


    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody @Valid final UserRequest userRequest)
    {
        LOGGER.debug("Trying to save user to the database");
        final User user = this.userService.createUser(userRequest);
        LOGGER.info("Created new user");

        final URI location = UriComponentsBuilder.fromUriString("/users/{id}")
                                                 .buildAndExpand(user.getId())
                                                 .toUri();

        return ResponseEntity.created(location).build();
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        final List<UserDto> allUsers = this.userService.getAllUsers();
        LOGGER.info("Requesting all users from the database");

        return ResponseEntity.ok(allUsers);
    }

}
