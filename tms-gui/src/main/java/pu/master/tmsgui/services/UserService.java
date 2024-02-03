package pu.master.tmsgui.services;


import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pu.master.tmsgui.models.dtos.UserDto;
import pu.master.tmsgui.models.entities.Role;
import pu.master.tmsgui.models.entities.User;
import pu.master.tmsgui.models.requests.UserRequest;
import pu.master.tmsgui.repositories.UserRepository;


@Service
public class UserService
{

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final ModelMapper modelMapper;
    private final PasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserService(final UserRepository userRepository,
                       final RoleService roleService,
                       final ModelMapper modelMapper,
                       final PasswordEncoder bCryptPasswordEncoder)
    {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public User createUser(final UserRequest userRequest)
    {
        final User user = mapUserRequestToUser(userRequest);

        /*final Set<Role> roles = userRequest.getRoles()
                                           .stream()
                                           .map(this.roleService::getRoleById)
                                           .collect(Collectors.toSet());*/

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


    private User mapUserRequestToUser(final UserRequest userRequest)
    {
        return this.modelMapper.map(userRequest, User.class);
    }


    private UserDto mapUserToUserDto(final User user)
    {
        return this.modelMapper.map(user, UserDto.class);
    }


    private boolean doesUserContainRoles(final User user)
    {
        return user.getRoles() == null;
    }


    private User setDefaultUserRole(final User user)
    {
        final long userRoleId = 1;
        final Role defaultRole = this.roleService.getRoleById(userRoleId);

        return user.addRole(defaultRole);
    }
}
