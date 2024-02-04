package pu.master.tmsapi.models.requests;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import pu.master.tmsapi.models.enums.ProjectPriority;


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


    public ProjectRequest setTitle(final String title)
    {
        this.title = title;
        return this;
    }


    public String getDescription()
    {
        return description;
    }


    public ProjectRequest setDescription(final String description)
    {
        this.description = description;
        return this;
    }


    public LocalDate getDateCreated()
    {
        return dateCreated;
    }


    public ProjectRequest setDateCreated(final LocalDate dateCreated)
    {
        this.dateCreated = dateCreated;
        return this;
    }


    public LocalDate getDueDate()
    {
        return dueDate;
    }


    public ProjectRequest setDueDate(final LocalDate dueDate)
    {
        this.dueDate = dueDate;
        return this;
    }


    public ProjectPriority getPriorityLevel()
    {
        return priorityLevel;
    }


    public ProjectRequest setPriorityLevel(final ProjectPriority priorityLevel)
    {
        this.priorityLevel = priorityLevel;
        return this;
    }


    public List<Long> getTasks()
    {
        return tasks;
    }


    public ProjectRequest setTasks(final List<Long> tasks)
    {
        this.tasks = tasks;
        return this;
    }


    public Set<Long> getUsers()
    {
        return users;
    }


    public ProjectRequest setUsers(final Set<Long> users)
    {
        this.users = users;
        return this;
    }
}
