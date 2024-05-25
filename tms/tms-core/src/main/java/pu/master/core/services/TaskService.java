package pu.master.core.services;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import pu.master.core.mappers.TaskMapper;
import pu.master.core.repositories.TaskRepository;
import pu.master.domain.models.dtos.TaskDto;
import pu.master.domain.models.entities.Task;
import pu.master.domain.models.entities.User;
import pu.master.domain.models.requests.TaskRequest;


@RequiredArgsConstructor
@Service
public class TaskService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    private final UserService userService;

    private final TaskMapper taskMapper;


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
