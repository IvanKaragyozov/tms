package pu.master.tmsapi.models.dtos;


import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pu.master.tmsapi.models.enums.TaskPriority;
import pu.master.tmsapi.models.enums.TaskStatus;


@Getter
@Setter
@RequiredArgsConstructor
public class TaskDto extends BaseDto
{

    private String title;

    private String description;

    private TaskPriority priorityLevel;

    private TaskStatus status;

    private ProjectDto project;

    private List<CommentDto> comments;
}
