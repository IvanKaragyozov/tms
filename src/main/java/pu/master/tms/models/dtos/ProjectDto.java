package pu.master.tms.models.dtos;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pu.master.tms.models.enums.ProjectPriority;
import pu.master.tms.models.enums.ProjectStatus;


@Getter
@Setter
@NoArgsConstructor
public class ProjectDto extends BaseDto
{

    private String title;

    private String description;

    private String dateCreated;

    private String dateDue;
    private String dateLastModified;

    private ProjectPriority priorityLevel;
    private ProjectStatus projectStatus = ProjectStatus.DUE;

    private String projectOwner;

    private List<TaskDto> tasks = new ArrayList<>();

    private Set<UserDto> collaborators = new LinkedHashSet<>();

}