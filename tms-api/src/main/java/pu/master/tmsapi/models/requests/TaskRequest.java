package pu.master.tmsapi.models.requests;


import java.util.List;

import pu.master.tmsapi.models.enums.TaskPriority;
import pu.master.tmsapi.models.enums.TaskStatus;


// TODO: Add validation
public class TaskRequest
{

    private String title;

    private String description;

    private TaskPriority priorityLevel;

    private TaskStatus status;

    private ProjectRequest project;

    private List<CommentRequest> comments;
}
