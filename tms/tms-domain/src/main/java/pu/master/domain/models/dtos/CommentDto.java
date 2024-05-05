package pu.master.domain.models.dtos;


import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CommentDto extends BaseDto
{

    private String text;

    private LocalDateTime timePosted;

}
