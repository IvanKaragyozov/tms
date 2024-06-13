package pu.master.rest.controllers;


import java.net.URI;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

import pu.master.core.services.TaskService;
import pu.master.domain.models.dtos.TaskDto;
import pu.master.domain.models.entities.Task;
import pu.master.domain.models.requests.TaskRequest;


@RequiredArgsConstructor
@RestController
public class TaskController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;


    @PostMapping("/tasks")
    public ResponseEntity<Void> createTask(@RequestBody @Valid final TaskRequest taskRequest)
    {
        LOGGER.debug("Sending request for a new task [{}]", taskRequest.getTitle());
        final Task task = this.taskService.createTask(taskRequest);
        LOGGER.info("Created task [{}]", taskRequest.getTitle());

        final URI location = UriComponentsBuilder.fromUriString("/tasks/{id}")
                                                 .buildAndExpand(task.getId())
                                                 .toUri();

        return ResponseEntity.created(location).build();
    }


    @GetMapping("/users/tasks")
    public ResponseEntity<List<TaskDto>> getTasksByUserEmail(@RequestParam final String email)
    {
        final List<TaskDto> taskDtos = this.taskService.getTasksByUserEmail(email);
        LOGGER.info(String.format("Request sent for all tasks by user with email [%s]", email));
        return ResponseEntity.ok(taskDtos);
    }


    @GetMapping("/users/{id}/tasks")
    public ResponseEntity<List<TaskDto>> getTasksByUserId(@PathVariable final long id)
    {
        final List<TaskDto> taskDtos = this.taskService.getTasksByUserId(id);

        LOGGER.info(String.format("Request sent for all tasks by user with id [%d]", id));

        return ResponseEntity.ok(taskDtos);
    }


    @PatchMapping("/tasks/{taskId}/invite")
    public ResponseEntity<Void> inviteUserToTask(@PathVariable final long taskId,
                                                 @RequestParam final String email,
                                                 final HttpServletRequest request)
    {

        this.taskService.inviteUser(taskId, email, request);

        return ResponseEntity.ok().build();
    }


    @PatchMapping("/tasks/{taskId}/invitation")
    public ResponseEntity<Void> handleInvitation(@PathVariable final long taskId,
                                                 @RequestParam final String email,
                                                 @RequestParam final boolean accept)
    {

        this.taskService.handleInvitation(taskId, email, accept);

        return ResponseEntity.ok().build();
    }
}
