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

    private List<Integer> comments;

    private Integer user;


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


    public ProjectRequest getProject()
    {
        return project;
    }


    public List<Integer> getComments()
    {
        return comments;
    }


    public Integer getUser()
    {
        return user;
    }
}
