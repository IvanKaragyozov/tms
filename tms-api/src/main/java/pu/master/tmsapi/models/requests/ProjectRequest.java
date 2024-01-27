package pu.master.tmsapi.models.requests;


import java.time.LocalDate;
import java.util.List;

import pu.master.tmsapi.models.enums.ProjectPriority;


// TODO: Add validation
public class ProjectRequest
{

    private String title;

    private String description;

    private LocalDate dateCreated;

    private LocalDate dueDate;

    private ProjectPriority priorityLevel;

    private List<TaskRequest> tasks;

    private List<UserRequest> users;
}
