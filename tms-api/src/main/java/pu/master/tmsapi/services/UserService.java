package pu.master.tmsapi.services;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.exceptions.UserNotFoundException;
import pu.master.tmsapi.models.dtos.UserDto;
import pu.master.tmsapi.models.entities.Role;
import pu.master.tmsapi.models.entities.User;
import pu.master.tmsapi.models.requests.UserRequest;
import pu.master.tmsapi.repositories.UserRepository;


@Service
public class UserService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserService(final UserRepository userRepository,
                       final RoleService roleService,
                       final ModelMapper modelMapper,
                       final BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public User createUser(final UserRequest userRequest)
    {
        final User user = mapUserRequestToUser(userRequest);

        // TODO: Create a default role and assign it to the default user
        final Set<Role> roles = userRequest.getRoles()
                                           .stream()
                                           .map(this.roleService::getRoleById)
                                           .collect(Collectors.toSet());

        final String encryptedUserPassword = this.bCryptPasswordEncoder.encode(userRequest.getPassword());

        user.setRoles(roles);
        user.setPassword(encryptedUserPassword);
        user.setActive(true);
        user.setDateCreatedAt(LocalDate.now());
        user.setDateLastModifiedAt(LocalDate.now());

        return this.userRepository.save(user);
    }


    public List<UserDto> getAllUsers()
    {
        final List<User> allUsers = this.userRepository.findAll();

        return allUsers.stream()
                       .map(this::mapUserToDto)
                       .toList();
    }


    public User getUserById(final long userId)
    {
        return this.userRepository.findById(userId).orElseThrow(() -> {

            LOGGER.error(String.format("Could not find user with id [%d]", userId));
            return new UserNotFoundException(String.format("User with id [%d] not found", userId));
        });
    }


    public UserDto getUserDtoById(final long userId)
    {
        final User user = getUserById(userId);

        return mapUserToDto(user);
    }


    public User getUserByUsername(final String username)
    {
        return this.userRepository.findUserByUsername(username).orElseThrow(() -> {

            LOGGER.error(String.format("Could not find user with username [%s]", username));
            return new UserNotFoundException(String.format("User with username [%s] not found", username));
        });
    }


    public UserDto getUserDtoByUsername(final String username)
    {
        final User user = getUserByUsername(username);

        return mapUserToDto(user);
    }


    private User mapUserRequestToUser(final UserRequest userRequest)
    {
        return this.modelMapper.map(userRequest, User.class);
    }


    private UserDto mapUserToDto(final User user)
    {
        return this.modelMapper.map(user, UserDto.class);
    }
}
