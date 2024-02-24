package pu.master.tmsapi.services;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.models.dtos.RoleDto;
import pu.master.tmsapi.models.entities.Right;
import pu.master.tmsapi.models.entities.Role;
import pu.master.tmsapi.models.requests.RoleRequest;
import pu.master.tmsapi.repositories.RoleRepository;


@Service
public class RoleService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class.getName());

    private final RoleRepository roleRepository;
    private final RightService rightService;

    private final ModelMapper modelMapper;


    @Autowired
    public RoleService(final RoleRepository roleRepository,
                       final RightService rightService,
                       final ModelMapper modelMapper)
    {
        this.roleRepository = roleRepository;
        this.rightService = rightService;
        this.modelMapper = modelMapper;
    }


    public Role createRole(final RoleRequest roleRequest)
    {
        final Role role = mapRoleRequestToRole(roleRequest);
        final Set<Right> rights = roleRequest
                        .getRights()
                        .stream()
                        .map(this.rightService::getRightById)
                        .collect(Collectors.toSet());

        role.setRights(rights);

        return this.roleRepository.save(role);
    }


    public List<RoleDto> getAllRoles()
    {
        final List<Role> allRoles = this.roleRepository.findAll();

        return allRoles.stream()
                       .map(this::mapRoleToRoleDto)
                       .toList();
    }


    public Role getRoleById(final long roleId)
    {
        // TODO: Add proper validation for non existing Role
        return this.roleRepository.findById(roleId).orElse(null);
    }


    private Role mapRoleRequestToRole(final RoleRequest roleRequest)
    {
        return this.modelMapper.map(roleRequest, Role.class);
    }


    private RoleDto mapRoleToRoleDto(final Role role)
    {
        return this.modelMapper.map(role, RoleDto.class);
    }

}
