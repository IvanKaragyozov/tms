package pu.master.tmsapi.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import pu.master.tmsapi.services.ProjectService;


@RestController
public class ProjectController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    private final ProjectService projectService;


    @Autowired
    public ProjectController(final ProjectService projectService)
    {
        this.projectService = projectService;
    }

    // TODO: Create POST request for Project entity and GET request for Projects By User Id
}
