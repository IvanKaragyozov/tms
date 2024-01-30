package pu.master.tmsapi.services;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.models.dtos.UserDto;
import pu.master.tmsapi.models.entities.Project;
import pu.master.tmsapi.models.entities.Role;
import pu.master.tmsapi.models.entities.User;
import pu.master.tmsapi.models.requests.UserRequest;
import pu.master.tmsapi.repositories.UserRepository;


@Service
public class UserService
{

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final ModelMapper modelMapper;


    @Autowired
    public UserService(final UserRepository userRepository, final RoleService roleService, final ModelMapper modelMapper)
    {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }


    public User createUser(final UserRequest userRequest)
    {
        final User user = mapUserRequestToUser(userRequest);
        final Set<Role> roles = userRequest.getRoles()
                                           .stream()
                                           .map(this.roleService::getRoleById)
                                           .collect(Collectors.toSet());

        user.setRoles(roles);

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
        // TODO: Add proper validation for non existing Project
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
}
