package pu.master.tmsapi.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.repositories.RightRepository;


@Service
public class RightService
{

    private final RightRepository rightRepository;


    @Autowired
    public RightService(final RightRepository rightRepository)
    {
        this.rightRepository = rightRepository;
    }
}
