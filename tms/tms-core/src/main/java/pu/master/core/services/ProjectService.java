package pu.master.core.services;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pu.master.core.mappers.ProjectMapper;
import pu.master.domain.models.dtos.ProjectDto;
import pu.master.domain.models.entities.Project;
import pu.master.domain.models.entities.User;
import pu.master.domain.models.requests.ProjectRequest;
import pu.master.core.repositories.ProjectRepository;


@Service
public class ProjectService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

    private final ProjectRepository projectRepository;

    private final UserService userService;

    private final ProjectMapper projectMapper;


    @Autowired
    public ProjectService(final ProjectRepository projectRepository,
                          final UserService userService,
                          final ProjectMapper projectMapper)
    {
        this.projectRepository = projectRepository;
        this.userService = userService;
        this.projectMapper = projectMapper;
    }


    public Project createProject(final ProjectRequest projectRequest)
    {
        final Project project = this.projectMapper.mapProjectRequestToProject(projectRequest);
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


    public List<ProjectDto> getProjectDtosByUserId(final long userId)
    {

        final User user = this.userService.getUserById(userId);

        return this.projectRepository.findProjectsByUsersId(user.getId())
                                     .stream()
                                     .map(this.projectMapper::mapProjectToDto)
                                     .toList();
    }

}
