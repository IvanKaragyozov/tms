package pu.master.tmsapi.models.dtos;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pu.master.tmsapi.models.enums.ProjectPriority;


// TODO: Add validation
public class ProjectDto extends BaseDto
{

    private String title;

    private String description;

    private LocalDate dateCreated;

    private LocalDate dueDate;

    private ProjectPriority priorityLevel;

    private List<TaskDto> tasks;


    public ProjectDto()
    {
    }


    public String getTitle()
    {
        return title;
    }


    public ProjectDto setTitle(final String title)
    {
        this.title = title;
        return this;
    }


    public String getDescription()
    {
        return description;
    }


    public ProjectDto setDescription(final String description)
    {
        this.description = description;
        return this;
    }


    public LocalDate getDateCreated()
    {
        return dateCreated;
    }


    public ProjectDto setDateCreated(final LocalDate dateCreated)
    {
        this.dateCreated = dateCreated;
        return this;
    }


    public LocalDate getDueDate()
    {
        return dueDate;
    }


    public ProjectDto setDueDate(final LocalDate dueDate)
    {
        this.dueDate = dueDate;
        return this;
    }


    public ProjectPriority getPriorityLevel()
    {
        return priorityLevel;
    }


    public ProjectDto setPriorityLevel(final ProjectPriority priorityLevel)
    {
        this.priorityLevel = priorityLevel;
        return this;
    }


    public List<TaskDto> getTasks()
    {
        return tasks;
    }


    public ProjectDto setTasks(final List<TaskDto> tasks)
    {
        this.tasks = tasks;
        return this;
    }


    public ProjectDto addTask(final TaskDto taskDto)
    {
        if (this.tasks == null)
        {
            this.tasks = new ArrayList<>();
        }

        this.tasks.add(taskDto);
        return this;
    }
}
