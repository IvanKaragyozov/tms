package pu.master.tmsapi.services;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pu.master.tmsapi.mappers.TaskMapper;
import pu.master.tmsapi.models.dtos.TaskDto;
import pu.master.tmsapi.models.entities.Task;
import pu.master.tmsapi.models.entities.User;
import pu.master.tmsapi.models.requests.TaskRequest;
import pu.master.tmsapi.repositories.TaskRepository;


@Service
public class TaskService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    private final UserService userService;

    private final TaskMapper taskMapper;


    @Autowired
    public TaskService(final TaskRepository taskRepository,
                       final UserService userService,
                       final TaskMapper taskMapper)
    {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.taskMapper = taskMapper;
    }


    public Task createTask(final TaskRequest taskRequest)
    {
        final Task task = this.taskMapper.mapTaskRequestToTask(taskRequest);
        final List<User> users = taskRequest.getUsers().stream()
                                            .map(this.userService::getUserById)
                                            .toList();

        task.setUsers(users);

        return this.taskRepository.save(task);
    }


    public Task getTaskById(final long taskId)
    {
        // TODO: Add proper validation for non existing Task
        return this.taskRepository.findById(taskId).orElse(null);
    }


    public List<TaskDto> getTasksByUserId(final long userId)
    {
        final User user = this.userService.getUserById(userId);

        return this.taskRepository.findTasksByUsersId(user.getId())
                                  .stream().map(this.taskMapper::mapTaskToDto)
                                  .toList();
    }

}
