package pu.master.tmsapi.services;


import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.models.dtos.UserDto;
import pu.master.tmsapi.models.entities.Role;
import pu.master.tmsapi.models.entities.User;
import pu.master.tmsapi.models.requests.UserRequest;
import pu.master.tmsapi.repositories.UserRepository;


@Service
public class UserService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class.getName());

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
        final String encryptedUserPassword = this.bCryptPasswordEncoder.encode(userRequest.getPassword());

        setDefaultUserRole(user);
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
                       .map(this::mapUserToUserDto)
                       .toList();
    }


    public User getUserById(final long userId)
    {
        // TODO: Add proper validation for non existing User
        return this.userRepository.findById(userId).orElse(null);
    }


    private User setDefaultUserRole(final User user)
    {
        final long userRoleId = 1;
        final Role defaultRole = this.roleService.getRoleById(userRoleId);

        return user.addRole(defaultRole);
    }


    private User mapUserRequestToUser(final UserRequest userRequest)
    {
        return this.modelMapper.map(userRequest, User.class);
    }


    private UserDto mapUserToUserDto(final User user)
    {
        return this.modelMapper.map(user, UserDto.class);
    }
}
