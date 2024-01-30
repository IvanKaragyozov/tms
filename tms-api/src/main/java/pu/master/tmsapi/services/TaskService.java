package pu.master.tmsapi.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.models.dtos.TaskDto;
import pu.master.tmsapi.models.dtos.UserDto;
import pu.master.tmsapi.models.entities.Task;
import pu.master.tmsapi.models.entities.User;
import pu.master.tmsapi.models.requests.TaskRequest;
import pu.master.tmsapi.models.requests.UserRequest;
import pu.master.tmsapi.repositories.TaskRepository;


@Service
public class TaskService
{

    private final TaskRepository taskRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public TaskService(final TaskRepository taskRepository, final ModelMapper modelMapper)
    {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    public Task createTask(final TaskRequest taskRequest)
    {

    }

    private Task mapUserRequestToUser(final TaskRequest taskRequest)
    {
        return this.modelMapper.map(taskRequest, Task.class);
    }


    private TaskDto mapTaskToTaskDto(final Task task)
    {
        return this.modelMapper.map(task, TaskDto.class);
    }
}
