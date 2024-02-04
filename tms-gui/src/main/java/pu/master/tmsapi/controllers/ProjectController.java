package pu.master.tmsapi.controllers;


import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pu.master.tmsapi.models.dtos.ProjectDto;
import pu.master.tmsapi.models.entities.Project;
import pu.master.tmsapi.models.requests.ProjectRequest;
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


    @PostMapping("/projects")
    public ResponseEntity<Void> createProjects(@RequestBody @Valid final ProjectRequest projectRequest)
    {
        LOGGER.debug("Trying to save project to the database");
        final Project role = this.projectService.createProject(projectRequest);
        LOGGER.info("Created new project");

        final URI location = UriComponentsBuilder.fromUriString("/projects/{id}")
                                                 .buildAndExpand(role.getId())
                                                 .toUri();

        return ResponseEntity.created(location).build();
    }


    @GetMapping("/users/{id}/projects")
    public ResponseEntity<List<ProjectDto>> getProjectsByUserId(@PathVariable final long id)
    {
        final List<ProjectDto> projectDtos = this.projectService.getProjectsByUserId(id);

        LOGGER.info(String.format("Request sent for all projects by user with id %d", id));

        return ResponseEntity.ok(projectDtos);
    }
}
