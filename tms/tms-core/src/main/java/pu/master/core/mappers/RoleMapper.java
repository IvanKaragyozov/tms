package pu.master.core.mappers;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pu.master.domain.models.dtos.RoleDto;
import pu.master.domain.models.entities.Role;
import pu.master.domain.models.requests.RoleRequest;


@Component
public class RoleMapper
{

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleMapper.class);

    private final ModelMapper modelMapper;


    @Autowired
    public RoleMapper(final ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }


    public Role mapRoleRequestToRole(final RoleRequest roleRequest)
    {
        LOGGER.debug("Mapping RoleRequest to Role");
        return this.modelMapper.map(roleRequest, Role.class);
    }


    public RoleDto mapRoleToDto(final Role role)
    {
        LOGGER.debug("Mapping Role to RoleDto");
        return this.modelMapper.map(role, RoleDto.class);
    }

}
