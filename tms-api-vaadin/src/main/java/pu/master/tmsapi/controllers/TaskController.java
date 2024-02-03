package pu.master.tmsapi.controllers;


import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pu.master.tmsapi.models.dtos.TaskDto;
import pu.master.tmsapi.models.entities.Task;
import pu.master.tmsapi.models.requests.TaskRequest;
import pu.master.tmsapi.services.TaskService;


@RestController
public class TaskController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;


    @Autowired
    public TaskController(final TaskService taskService)
    {
        this.taskService = taskService;
    }


    @PostMapping("/tasks")
    public ResponseEntity<Void> createTask(@RequestBody @Valid final TaskRequest taskRequest)
    {
        LOGGER.debug("Trying to save task to the database");
        final Task task = this.taskService.createTask(taskRequest);
        LOGGER.info("Created new task");

        final URI location = UriComponentsBuilder.fromUriString("/tasks/{id}")
                                                 .buildAndExpand(task.getId())
                                                 .toUri();

        return ResponseEntity.created(location).build();
    }


    @GetMapping("/users/{id}/tasks")
    public ResponseEntity<List<TaskDto>> getTasksByUserId(@PathVariable final long id)
    {
        final List<TaskDto> taskDtos = this.taskService.getTasksByUserId(id);

        LOGGER.info(String.format("Request sent for all tasks by user with id %d", id));

        return ResponseEntity.ok(taskDtos);
    }
}
