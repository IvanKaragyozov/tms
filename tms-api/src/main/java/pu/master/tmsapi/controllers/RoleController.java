package pu.master.tmsapi.controllers;


import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pu.master.tmsapi.models.dtos.RoleDto;
import pu.master.tmsapi.models.entities.Role;
import pu.master.tmsapi.models.requests.RoleRequest;
import pu.master.tmsapi.services.RoleService;


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
        final List<RoleDto> allRoles = this.roleService.getAllRoles();
        LOGGER.info("Requesting all roles from the database");

        return ResponseEntity.ok(allRoles);
    }
}
