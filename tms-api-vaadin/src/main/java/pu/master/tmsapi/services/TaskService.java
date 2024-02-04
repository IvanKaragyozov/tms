package pu.master.tmsapi.services;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaadin.flow.component.notification.Notification;

import pu.master.tmsapi.exceptions.EmptyFieldException;
import pu.master.tmsapi.exceptions.TaskNotFoundException;
import pu.master.tmsapi.models.dtos.CommentDto;
import pu.master.tmsapi.models.dtos.TaskDto;
import pu.master.tmsapi.models.entities.Task;
import pu.master.tmsapi.models.entities.User;
import pu.master.tmsapi.models.enums.TaskPriority;
import pu.master.tmsapi.models.enums.TaskStatus;
import pu.master.tmsapi.models.requests.TaskRequest;
import pu.master.tmsapi.repositories.TaskRepository;


@Service
public class TaskService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class.getName());

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

        if (task.getTitle().isEmpty())
        {
            final String message = "Task title cannot be empty!";

            Notification.show(message, 3000, Notification.Position.TOP_CENTER);
            LOGGER.error(message);
            throw new EmptyFieldException(message);
        }

        return this.taskRepository.save(task);
    }



    public List<TaskDto> getAllTaskDtos()
    {
        final List<Task> allTasks = this.taskRepository.findAllTasks();
        final List<TaskDto> taskDtos = allTasks.stream()
                                               .map(this::mapTaskToTaskDto)
                                               .toList();

        return taskDtos;
    }


    public Task getTaskById(final long taskId)
    {
        LOGGER.info(String.format("Trying to retrieve task with id %d", taskId));
        return this.taskRepository.findById(taskId).orElseThrow(() -> {

            LOGGER.error(String.format("Tried to retrieve a task with id %d that does not exist!", taskId));
            throw new TaskNotFoundException(String.format("Comment with id %d does not exist!", taskId));
        });
    }


    public List<TaskDto> getTasksByUserId(final long userId)
    {
        final User user = this.userService.getUserById(userId);

        return this.taskRepository.findTasksByUsersId(user.getId())
                                  .stream().map(this::mapTaskToTaskDto)
                                  .toList();
    }

    public List<TaskDto> getTasksByTitle(final String title)
    {
        final List<Task> tasksByTitle = this.taskRepository.findTasksByTitleContaining(title);
        final List<TaskDto> taskDtos = tasksByTitle.stream().map(this::mapTaskToTaskDto).toList();
        return taskDtos;
    }

    public List<TaskDto> getTasksByPriorityLevel(final TaskPriority taskPriority)
    {
        final List<Task> tasksByPriorityLevel = this.taskRepository.findTasksByPriorityLevel(taskPriority);
        final List<TaskDto> taskDtos = tasksByPriorityLevel.stream().map(this::mapTaskToTaskDto).toList();
        return taskDtos;
    }


    public List<TaskDto> getTasksByStatus(final TaskStatus taskStatus)
    {
        final List<Task> tasksByStatus = this.taskRepository.findTasksByStatus(taskStatus);
        final List<TaskDto> taskDtos = tasksByStatus.stream().map(this::mapTaskToTaskDto).toList();
        return taskDtos;
    }


    public List<TaskDto> getTasksByPriorityAndStatus(final TaskPriority taskPriority, final TaskStatus taskStatus)
    {
        final List<Task> tasks = this.taskRepository.findTasksByPriorityLevelAndStatus(taskPriority, taskStatus);
        final List<TaskDto> taskDtos = tasks.stream().map(this::mapTaskToTaskDto).toList();
        return taskDtos;
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
