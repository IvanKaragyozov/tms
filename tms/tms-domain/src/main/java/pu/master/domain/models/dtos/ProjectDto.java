package pu.master.domain.models.dtos;


import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pu.master.domain.models.enums.ProjectPriority;


@Getter
@Setter
@RequiredArgsConstructor
public class ProjectDto extends BaseDto
{

    private String title;

    private String description;

    private LocalDate dateCreated;

    private LocalDate dueDate;

    private ProjectPriority priorityLevel;

    private List<TaskDto> tasks;

}
