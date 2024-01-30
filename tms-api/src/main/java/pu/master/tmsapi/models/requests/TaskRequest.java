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

    private Long project;

    private List<Long> comments;

    private List<Long> users;


    public TaskRequest()
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


    public Long getProject()
    {
        return project;
    }


    public List<Long> getComments()
    {
        return comments;
    }


    public List<Long> getUsers()
    {
        return users;
    }
}
