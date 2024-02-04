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


    public TaskDto()
    {
    }


    public String getTitle()
    {
        return title;
    }


    public TaskDto setTitle(final String title)
    {
        this.title = title;
        return this;
    }


    public String getDescription()
    {
        return description;
    }


    public TaskDto setDescription(final String description)
    {
        this.description = description;
        return this;
    }


    public TaskPriority getPriorityLevel()
    {
        return priorityLevel;
    }


    public TaskDto setPriorityLevel(final TaskPriority priorityLevel)
    {
        this.priorityLevel = priorityLevel;
        return this;
    }


    public TaskStatus getStatus()
    {
        return status;
    }


    public TaskDto setStatus(final TaskStatus status)
    {
        this.status = status;
        return this;
    }


    public ProjectDto getProject()
    {
        return project;
    }


    public TaskDto setProject(final ProjectDto project)
    {
        this.project = project;
        return this;
    }


    public List<CommentDto> getComments()
    {
        return comments;
    }


    public TaskDto setComments(final List<CommentDto> comments)
    {
        this.comments = comments;
        return this;
    }
}
