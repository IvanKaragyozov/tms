package pu.master.tms.models.requests;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pu.master.tms.models.enums.TaskPriority;
import pu.master.tms.models.enums.TaskStatus;


@Getter
@AllArgsConstructor
public class TaskRequest
{

    @NotNull(message = "The title cannot be empty")
    @NotBlank(message = "The title cannot contain only white spaces")
    private String title;

    private String description;

    private TaskPriority priorityLevel;

    private TaskStatus status;

}
