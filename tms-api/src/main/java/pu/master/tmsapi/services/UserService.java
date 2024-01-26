package pu.master.tmsapi.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.repositories.UserRepository;


@Service
public class UserService
{

    private final UserRepository userRepository;


    @Autowired
    public UserService(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
}
