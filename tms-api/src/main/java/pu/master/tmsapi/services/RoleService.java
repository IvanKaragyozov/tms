package pu.master.tmsapi.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.repositories.RoleRepository;


@Service
public class RoleService
{

    private final RoleRepository roleRepository;


    @Autowired
    public RoleService(final RoleRepository roleRepository)
    {
        this.roleRepository = roleRepository;
    }
}
