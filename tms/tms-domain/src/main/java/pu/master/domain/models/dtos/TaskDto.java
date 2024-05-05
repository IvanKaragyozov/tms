package pu.master.domain.models.dtos;


import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pu.master.domain.models.enums.TaskPriority;
import pu.master.domain.models.enums.TaskStatus;


@Getter
@Setter
@NoArgsConstructor
public class TaskDto extends BaseDto
{

    private String title;

    private String description;

    private TaskPriority priorityLevel;

    private TaskStatus status;

    private ProjectDto project;

    private List<CommentDto> comments;
}
