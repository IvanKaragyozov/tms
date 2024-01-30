package pu.master.tmsapi.services;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.models.dtos.TaskDto;
import pu.master.tmsapi.models.entities.Project;
import pu.master.tmsapi.models.entities.Task;
import pu.master.tmsapi.models.entities.User;
import pu.master.tmsapi.models.requests.TaskRequest;
import pu.master.tmsapi.repositories.TaskRepository;


@Service
public class TaskService
{

    private final TaskRepository taskRepository;

    private final ProjectService projectService;
    private final UserService userService;

    private final ModelMapper modelMapper;


    @Autowired
    public TaskService(final TaskRepository taskRepository,
                       final ProjectService projectService,
                       final UserService userService,
                       final ModelMapper modelMapper)
    {
        this.taskRepository = taskRepository;
        this.projectService = projectService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public Task createTask(final TaskRequest taskRequest)
    {
        final Task task = mapTaskRequestToTask(taskRequest);
        final Project project = this.projectService.getProjectById(task.getId());
        final List<User> users = taskRequest.getUsers().stream()
                                            .map(this.userService::getUserById)
                                            .toList();

        task.setProject(project);
        task.setUsers(users);

        return task;
    }

    public Task getTaskById(final long taskId)
    {
        // TODO: Add proper validation for non existing Task
        return this.taskRepository.findById(taskId).orElse(null);
    }

    private Task mapTaskRequestToTask(final TaskRequest taskRequest)
    {
        return this.modelMapper.map(taskRequest, Task.class);
    }


    private TaskDto mapTaskToTaskDto(final Task task)
    {
        return this.modelMapper.map(task, TaskDto.class);
    }
}
