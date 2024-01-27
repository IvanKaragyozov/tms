package pu.master.tmsapi.models.dtos;


import java.util.List;

import pu.master.tmsapi.models.enums.TaskPriority;
import pu.master.tmsapi.models.enums.TaskStatus;


// TODO: Add validation
public class TaskDto extends BaseDto
{

    private String title;

    private String description;

    private TaskPriority priorityLevel;

    private TaskStatus status;

    private ProjectDto project;

    private List<CommentDto> comments;
}
