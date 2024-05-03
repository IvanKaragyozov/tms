package pu.master.domain.models.requests;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pu.master.domain.models.enums.TaskPriority;
import pu.master.domain.models.enums.TaskStatus;


@Getter
@AllArgsConstructor
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
