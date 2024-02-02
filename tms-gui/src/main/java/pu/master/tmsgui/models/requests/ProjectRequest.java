package pu.master.tmsgui.models.requests;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import pu.master.tmsgui.models.enums.ProjectPriority;


// TODO: Add validation
public class ProjectRequest
{

    private String title;

    private String description;

    private LocalDate dateCreated;

    private LocalDate dueDate;

    private ProjectPriority priorityLevel;

    private List<Long> tasks;

    private Set<Long> users;


    public ProjectRequest()
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


    public LocalDate getDateCreated()
    {
        return dateCreated;
    }


    public LocalDate getDueDate()
    {
        return dueDate;
    }


    public ProjectPriority getPriorityLevel()
    {
        return priorityLevel;
    }


    public List<Long> getTasks()
    {
        return tasks;
    }


    public Set<Long> getUsers()
    {
        return users;
    }
}
