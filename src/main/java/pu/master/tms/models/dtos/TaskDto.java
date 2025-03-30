package pu.master.tms.models.dtos;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pu.master.tms.models.enums.TaskPriority;
import pu.master.tms.models.enums.TaskStatus;


@Getter
@Setter
@NoArgsConstructor
public class TaskDto extends BaseDto
{

    private String title;

    private String description;

    private TaskPriority priorityLevel;

    private TaskStatus status;

    private String dateCreated;

    private String dateLastModified;

    private LocalDateTime dateDue;

    private String ownerUsername;

    private List<TaskItemDto> taskItems = new ArrayList<>();

    private List<UserDto> users = new ArrayList<>();

    public TaskDto(final String title,
                   final String description,
                   final TaskPriority priorityLevel,
                   final TaskStatus status,
                   final String dateCreated,
                   final String dateLastModified,
                   final LocalDateTime dateDue,
                   final String ownerUsername,
                   final List<UserDto> users)
    {
        this.title = title;
        this.description = description;
        this.priorityLevel = priorityLevel;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateLastModified = dateLastModified;
        this.dateDue = dateDue;
        this.ownerUsername = ownerUsername;
        this.users = users;
    }


    public TaskDto(final String title,
                   final String description,
                   final TaskPriority priorityLevel,
                   final TaskStatus status,
                   final String dateCreated,
                   final String dateLastModified,
                   final LocalDateTime dateDue,
                   final String ownerUsername)
    {
        this.title = title;
        this.description = description;
        this.priorityLevel = priorityLevel;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateLastModified = dateLastModified;
        this.dateDue = dateDue;
        this.ownerUsername = ownerUsername;
    }

    public TaskDto(String title, String description, TaskPriority priorityLevel, TaskStatus status,
                   String dateCreated, String dateLastModified, LocalDateTime dateDue,
                   String ownerUsername, List<UserDto> users, List<TaskItemDto> taskItems) {
        this.title = title;
        this.description = description;
        this.priorityLevel = priorityLevel;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateLastModified = dateLastModified;
        this.dateDue = dateDue;
        this.ownerUsername = ownerUsername;
        this.users = users;
        this.taskItems = taskItems;
    }
}
