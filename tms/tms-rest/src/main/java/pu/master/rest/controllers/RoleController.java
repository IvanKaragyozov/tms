package pu.master.rest.controllers;


import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import pu.master.core.services.RoleService;
import pu.master.domain.models.dtos.RoleDto;
import pu.master.domain.models.entities.Role;
import pu.master.domain.models.requests.RoleRequest;


@RestController
public class RoleController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class.getName());

    private final RoleService roleService;


    @Autowired
    public RoleController(final RoleService roleService)
    {
        this.roleService = roleService;
    }


    @PreAuthorize("hasAnyAuthority({'ADMIN'})")
    @PostMapping("/roles")
    public ResponseEntity<Void> createRole(@RequestBody @Valid final RoleRequest roleRequest)
    {
        LOGGER.debug("Trying to save role to the database");
        final Role role = this.roleService.createRole(roleRequest);
        LOGGER.info("Created new role");

        final URI location = UriComponentsBuilder.fromUriString("/roles/{id}")
                                                 .buildAndExpand(role.getId())
                                                 .toUri();

        return ResponseEntity.created(location).build();
    }


    @GetMapping("/roles")
    public ResponseEntity<List<RoleDto>> getAllRoles()
    {
        LOGGER.info("Requesting all roles from the database");
        final List<RoleDto> allRoles = this.roleService.getAllRolesDtos();

        return ResponseEntity.ok(allRoles);
    }
}
