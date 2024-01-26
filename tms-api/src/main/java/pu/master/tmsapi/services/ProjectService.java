package pu.master.tmsapi.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.repositories.ProjectRepository;


@Service
public class ProjectService
{

    private final ProjectRepository projectRepository;


    @Autowired
    public ProjectService(final ProjectRepository projectRepository)
    {
        this.projectRepository = projectRepository;
    }
}
