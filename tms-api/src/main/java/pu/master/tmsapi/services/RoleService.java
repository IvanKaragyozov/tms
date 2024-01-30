package pu.master.tmsapi.services;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
        final Set<Right> existingRights = roleRequest
                        .getRights()
                        .stream()
                        .map(this.rightService::getRightById)
                        .collect(Collectors.toSet());

        role.setRights(existingRights);

        return this.roleRepository.save(role);
    }


    public List<RoleDto> getAllRoles()
    {
        final List<Role> allRoles = this.roleRepository.findAll();

        return allRoles.stream()
                       .map(this::mapRoleToRoleDto)
                       .toList();
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
