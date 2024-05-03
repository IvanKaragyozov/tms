package pu.master.core.services;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pu.master.core.exceptions.RoleNameAlreadyExistsException;
import pu.master.core.exceptions.RoleNotFoundException;
import pu.master.core.mappers.RoleMapper;
import pu.master.core.repositories.RoleRepository;
import pu.master.domain.models.dtos.RoleDto;
import pu.master.domain.models.entities.Right;
import pu.master.domain.models.entities.Role;
import pu.master.domain.models.requests.RoleRequest;


@Service
public class RoleService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);

    private final RoleRepository roleRepository;
    private final RightService rightService;

    private final RoleMapper roleMapper;


    @Autowired
    public RoleService(final RoleRepository roleRepository,
                       final RightService rightService,
                       final RoleMapper roleMapper)
    {
        this.roleRepository = roleRepository;
        this.rightService = rightService;
        this.roleMapper = roleMapper;
    }


    public Role createRole(final RoleRequest roleRequest)
    {

        if (this.roleRepository.existsByName(roleRequest.getName()))
        {
            LOGGER.error(String.format("Role with name [%s] already exists!", roleRequest.getName()));
            throw new RoleNameAlreadyExistsException(String.format("Role with name [%s] already exists!",
                                                                   roleRequest.getName()));
        }

        final Role role = this.roleMapper.mapRoleRequestToRole(roleRequest);
        final Set<Right> rights = roleRequest
                        .getRights()
                        .stream()
                        .map(this.rightService::getRightByName)
                        .collect(Collectors.toSet());

        role.setRights(rights);

        return this.roleRepository.save(role);
    }


    public List<RoleDto> getAllRolesDtos()
    {
        final List<Role> allRoles = this.roleRepository.findAll();

        return allRoles.stream()
                       .map(this.roleMapper::mapRoleToDto)
                       .toList();
    }


    public Role getRoleById(final long roleId)
    {
        return this.roleRepository.findById(roleId).orElseThrow(() -> {
            LOGGER.error(String.format("Could not find role with id [%d]!", roleId));
            return new RoleNotFoundException(String.format("Role with id [%d] not found!", roleId));
        });
    }


    public Role getRoleByName(final String name)
    {
        return this.roleRepository.findRoleByName(name).orElseThrow(() -> {
            LOGGER.error(String.format("Could not find role with name [%s]!", name));
            return new RoleNotFoundException(String.format("Role with name [%s] not found!", name));
        });
    }

}
