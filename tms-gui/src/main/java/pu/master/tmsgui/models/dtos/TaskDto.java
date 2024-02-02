package pu.master.tmsgui.models.dtos;


import java.util.List;

import pu.master.tmsgui.models.enums.TaskPriority;
import pu.master.tmsgui.models.enums.TaskStatus;


// TODO: Add validation
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
