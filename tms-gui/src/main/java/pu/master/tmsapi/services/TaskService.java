package pu.master.tmsapi.services;


import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.models.dtos.TaskDto;
import pu.master.tmsapi.models.entities.Task;
import pu.master.tmsapi.models.entities.User;
import pu.master.tmsapi.models.requests.TaskRequest;
import pu.master.tmsapi.repositories.TaskRepository;


@Service
public class TaskService
{

    private final TaskRepository taskRepository;

    private final UserService userService;

    private final ModelMapper modelMapper;


    @Autowired
    public TaskService(final TaskRepository taskRepository,
                       final UserService userService,
                       final ModelMapper modelMapper)
    {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    public Task createTask(final TaskRequest taskRequest)
    {
        final Task task = mapTaskRequestToTask(taskRequest);

        // TODO: Uncomment when setting tasks to user
        /*final List<User> users = taskRequest.getUsers().stream()
                                            .map(this.userService::getUserById)
                                            .toList();
        task.setUsers(users);*/

        return this.taskRepository.save(task);
    }


    public List<Task> getAllTasks()
    {
        return this.taskRepository.findAll();
    }


    public List<TaskDto> getAllTaskDtos()
    {
        final List<Task> allTasks = this.taskRepository.findAll();
        final List<TaskDto> taskDtos = allTasks.stream()
                                               .map(this::mapTaskToTaskDto)
                                               .toList();

        return taskDtos;
    }


    public Task getTaskById(final long taskId)
    {
        // TODO: Add proper validation for non existing Task
        return Objects.requireNonNull(this.taskRepository.findById(taskId).orElse(null));
    }


    public List<TaskDto> getTasksByUserId(final long userId)
    {
        final User user = this.userService.getUserById(userId);

        return this.taskRepository.findTasksByUsersId(user.getId())
                                  .stream().map(this::mapTaskToTaskDto)
                                  .toList();
    }


    public Task updateTask(final TaskDto taskDto)
    {
        final Task task = mapTaskDtoToTask(taskDto);
        return this.taskRepository.save(task);
    }


    public Task deleteTaskById(final long taskId)
    {
        final Task taskById = getTaskById(taskId);
        this.taskRepository.delete(taskById);

        return taskById;
    }


    public Task mapTaskRequestToTask(final TaskRequest taskRequest)
    {
        return this.modelMapper.map(taskRequest, Task.class);
    }


    public TaskDto mapTaskToTaskDto(final Task task)
    {
        return this.modelMapper.map(task, TaskDto.class);
    }


    public Task mapTaskDtoToTask(final TaskDto taskDto)
    {
        return this.modelMapper.map(taskDto, Task.class);
    }


    public TaskRequest mapTaskDtoToTaskRequest(final TaskDto taskDto)
    {
        return this.modelMapper.map(taskDto, TaskRequest.class);
    }
}
