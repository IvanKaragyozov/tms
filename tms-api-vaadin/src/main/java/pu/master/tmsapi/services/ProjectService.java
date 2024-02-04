package pu.master.tmsapi.services;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.models.dtos.ProjectDto;
import pu.master.tmsapi.models.dtos.TaskDto;
import pu.master.tmsapi.models.entities.Project;
import pu.master.tmsapi.models.entities.User;
import pu.master.tmsapi.models.requests.ProjectRequest;
import pu.master.tmsapi.repositories.ProjectRepository;


@Service
public class ProjectService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class.getName());

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

        // TODO: Uncomment when setting projects to user
        /*final Set<User> users = projectRequest.getUsers().stream()
                                              .map(this.userService::getUserById)
                                              .collect(Collectors.toSet());*/

        project.setDateCreated(LocalDate.now());

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


    public List<ProjectDto> getAllProjectDtos()
    {
        final List<Project> allProjects = this.projectRepository.findAll();

        final List<ProjectDto> projectDtos = allProjects.stream()
                                                        .map(this::mapProjectToProjectDto)
                                                        .toList();

        return projectDtos;
    }


    public Project updateProject(final ProjectDto projectDto)
    {
        final Project project = mapProjectDtoToProject(projectDto);
        return this.projectRepository.save(project);
    }


    public Project deleteProjectById(final long projectId)
    {
        final Project projectById = getProjectById(projectId);
        this.projectRepository.delete(projectById);

        return projectById;
    }


    public Project mapProjectRequestToProject(final ProjectRequest projectRequest)
    {
        return this.modelMapper.map(projectRequest, Project.class);
    }


    public ProjectDto mapProjectToProjectDto(final Project project)
    {
        return this.modelMapper.map(project, ProjectDto.class);
    }


    public Project mapProjectDtoToProject(final ProjectDto projectDto)
    {
        return this.modelMapper.map(projectDto, Project.class);
    }


    public ProjectRequest mapProjectDtoToProjecctRequest(final ProjectDto projectDto)
    {
        ProjectRequest projectRequest = new ProjectRequest();

        projectRequest.setTitle(projectDto.getTitle());
        projectRequest.setDescription(projectDto.getDescription());
        projectRequest.setDateCreated(projectDto.getDateCreated());
        projectRequest.setDueDate(projectDto.getDueDate());
        projectRequest.setPriorityLevel(projectDto.getPriorityLevel());

        if (projectDto.getTasks() != null)
        {
            List<Long> taskIds = projectDto.getTasks().stream()
                                           .map(TaskDto::getId)
                                           .collect(Collectors.toList());
            projectRequest.setTasks(taskIds);
        }

        return projectRequest;
    }

}
