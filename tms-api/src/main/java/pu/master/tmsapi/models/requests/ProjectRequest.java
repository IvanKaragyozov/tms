package pu.master.tmsapi.models.requests;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.Data;
import pu.master.tmsapi.models.enums.ProjectPriority;


@Data
public class ProjectRequest
{

    private String title;

    private String description;

    private LocalDate dateCreated;

    private LocalDate dueDate;

    private ProjectPriority priorityLevel;

    private List<Long> tasks;

    private Set<Long> users;

}
