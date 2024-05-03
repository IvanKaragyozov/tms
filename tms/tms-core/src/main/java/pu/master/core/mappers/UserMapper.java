package pu.master.core.mappers;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pu.master.domain.models.dtos.UserDto;
import pu.master.domain.models.entities.User;
import pu.master.domain.models.requests.UserRequest;


@Component
public class UserMapper
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserMapper.class);

    private final ModelMapper modelMapper;


    @Autowired
    public UserMapper(final ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }


    public User mapUserRequestToUser(final UserRequest userRequest)
    {
        LOGGER.debug("Mapping UserRequest to User");
        return this.modelMapper.map(userRequest, User.class);
    }


    public UserDto mapUserToDto(final User user)
    {
        LOGGER.debug("Mapping User to UserDto");
        return this.modelMapper.map(user, UserDto.class);
    }
}
