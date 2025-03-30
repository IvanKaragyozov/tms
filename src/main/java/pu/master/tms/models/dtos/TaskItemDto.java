package pu.master.tms.models.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class TaskItemDto
{

    private String title;
    private boolean isFinished;

    public TaskItemDto(String title, boolean isFinished)
    {
        this.title = title;
        this.isFinished = isFinished;
    }
}
