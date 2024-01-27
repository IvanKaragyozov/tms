package pu.master.tmsapi.models.dtos;


import java.time.LocalDate;
import java.util.List;

import pu.master.tmsapi.models.enums.ProjectPriority;


// TODO: Add validation
public class ProjectDto extends BaseDto
{

    private String title;

    private String description;

    private LocalDate dateCreated;

    private LocalDate dueDate;

    private ProjectPriority priorityLevel;

    private List<TaskDto> tasks;

    private List<UserDto> users;
}