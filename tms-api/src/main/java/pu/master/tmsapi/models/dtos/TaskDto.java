package pu.master.tmsapi.models.dtos;


import java.util.List;

import pu.master.tmsapi.models.enums.TaskPriority;
import pu.master.tmsapi.models.enums.TaskStatus;


public class TaskDto extends BaseDto
{

    private String title;

    private String description;

    private TaskPriority priorityLevel;

    private TaskStatus status;

    private ProjectDto project;

    private List<CommentDto> comments;


    public TaskDto()
    {
    }


    public String getTitle()
    {
        return title;
    }


    public String getDescription()
    {
        return description;
    }


    public TaskPriority getPriorityLevel()
    {
        return priorityLevel;
    }


    public TaskStatus getStatus()
    {
        return status;
    }


    public ProjectDto getProject()
    {
        return project;
    }


    public List<CommentDto> getComments()
    {
        return comments;
    }
}
