package pu.master.tmsapi.mappers;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pu.master.tmsapi.models.dtos.UserDto;
import pu.master.tmsapi.models.entities.User;
import pu.master.tmsapi.models.requests.UserRequest;


@Mapper
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
