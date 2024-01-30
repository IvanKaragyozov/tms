package pu.master.tmsapi.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.models.dtos.TaskDto;
import pu.master.tmsapi.models.entities.Project;
import pu.master.tmsapi.models.entities.Task;
import pu.master.tmsapi.models.requests.ProjectRequest;
import pu.master.tmsapi.models.requests.TaskRequest;
import pu.master.tmsapi.repositories.ProjectRepository;


@Service
public class ProjectService
{

    private final ProjectRepository projectRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ProjectService(final ProjectRepository projectRepository, final ModelMapper modelMapper)
    {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }


    public Project getProjectById(final long projectId)
    {
        // TODO: Add proper validation for non existing Project
        return this.projectRepository.findById(projectId).orElse(null);
    }


    private Project mapProjectRequestToProject(final ProjectRequest projectRequest)
    {
        return this.modelMapper.map(projectRequest, Project.class);
    }


    private TaskDto mapProjectToProjectDto(final Task task)
    {
        return this.modelMapper.map(task, TaskDto.class);
    }
}
