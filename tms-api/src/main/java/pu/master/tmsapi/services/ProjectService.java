package pu.master.tmsapi.services;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.models.dtos.ProjectDto;
import pu.master.tmsapi.models.entities.Project;
import pu.master.tmsapi.models.entities.User;
import pu.master.tmsapi.models.requests.ProjectRequest;
import pu.master.tmsapi.repositories.ProjectRepository;


@Service
public class ProjectService
{

    private final ProjectRepository projectRepository;

    private final UserService userService;

    private final ModelMapper modelMapper;


    @Autowired
    public ProjectService(final ProjectRepository projectRepository,
                          final UserService userService,
                          final ModelMapper modelMapper)
    {
        this.projectRepository = projectRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    public Project createProject(final ProjectRequest projectRequest)
    {
        final Project project = mapProjectRequestToProject(projectRequest);
        final Set<User> users = projectRequest.getUsers().stream()
                                              .map(this.userService::getUserById)
                                              .collect(Collectors.toSet());

        project.setDateCreated(LocalDate.now());
        project.setUsers(users);

        return this.projectRepository.save(project);
    }


    public Project getProjectById(final long projectId)
    {
        // TODO: Add proper validation for non existing Project
        return this.projectRepository.findById(projectId).orElse(null);
    }


    public List<ProjectDto> getProjectsByUserId(final long userId)
    {

        final User user = this.userService.getUserById(userId);

        return this.projectRepository.findProjectsByUsersId(user.getId())
                                     .stream()
                                     .map(this::mapProjectToProjectDto)
                                     .toList();
    }


    private Project mapProjectRequestToProject(final ProjectRequest projectRequest)
    {
        return this.modelMapper.map(projectRequest, Project.class);
    }


    private ProjectDto mapProjectToProjectDto(final Project project)
    {
        return this.modelMapper.map(project, ProjectDto.class);
    }
}
