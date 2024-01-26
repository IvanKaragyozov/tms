package pu.master.tmsapi.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.repositories.TaskRepository;


@Service
public class TaskService
{

    private final TaskRepository taskRepository;


    @Autowired
    public TaskService(final TaskRepository taskRepository)
    {
        this.taskRepository = taskRepository;
    }
}
