package pu.master.core.mappers;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pu.master.domain.models.uibeans.UserUIBean;
import pu.master.domain.models.entities.User;
import pu.master.domain.models.requests.RegistrationRequest;


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


    public User mapUserRequestToUser(final RegistrationRequest registrationRequest)
    {
        LOGGER.debug("Mapping UserRequest to User");
        return this.modelMapper.map(registrationRequest, User.class);
    }


    public UserUIBean mapUserToUIBean(final User user)
    {
        LOGGER.debug("Mapping User to UserUIBean");
        return this.modelMapper.map(user, UserUIBean.class);
    }


    public User mapUserUIBeanToUser(final UserUIBean userUIBean)
    {
        LOGGER.debug("Mapping UserUIBean to User");
        return this.modelMapper.map(userUIBean, User.class);
    }
}
