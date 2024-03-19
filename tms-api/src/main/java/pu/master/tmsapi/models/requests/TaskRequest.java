package pu.master.tmsapi.models.requests;


import java.util.List;
import lombok.Data;
import pu.master.tmsapi.models.enums.TaskPriority;
import pu.master.tmsapi.models.enums.TaskStatus;


@Data
public class TaskRequest
{

    private String title;

    private String description;

    private TaskPriority priorityLevel;

    private TaskStatus status;

    private Long project;

    private List<Long> comments;

    private List<Long> users;

}
