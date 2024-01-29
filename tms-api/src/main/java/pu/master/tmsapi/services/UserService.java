package pu.master.tmsapi.services;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.models.dtos.UserDto;
import pu.master.tmsapi.models.entities.User;
import pu.master.tmsapi.models.requests.UserRequest;
import pu.master.tmsapi.repositories.UserRepository;


@Service
public class UserService
{

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public UserService(final UserRepository userRepository, final ModelMapper modelMapper)
    {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    public User createUser(final UserRequest userRequest)
    {
        final User user = mapUserRequestToUser(userRequest);
        return this.userRepository.save(user);
    }


    public List<UserDto> getAllUsers()
    {
        final List<User> allUsers = this.userRepository.findAll();

        return allUsers.stream()
                       .map(this::mapUserToUserDto)
                       .toList();
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
