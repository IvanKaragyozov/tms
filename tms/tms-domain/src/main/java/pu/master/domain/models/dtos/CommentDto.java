package pu.master.domain.models.dtos;


import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class CommentDto extends BaseDto
{

    private String text;

    private LocalDateTime timePosted;

}
