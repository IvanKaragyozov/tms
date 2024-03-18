package pu.master.tmsapi.models.dtos;


import java.time.LocalDate;
import java.util.List;
import pu.master.tmsapi.models.enums.ProjectPriority;


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


    public List<TaskDto> getTasks()
    {
        return tasks;
    }

}
