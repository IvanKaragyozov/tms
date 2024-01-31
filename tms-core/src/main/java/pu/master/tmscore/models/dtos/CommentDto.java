package pu.master.tmsapi.models.dtos;


import java.time.LocalDateTime;


// TODO: Add validation
public class CommentDto extends BaseDto
{

    private String text;

    private LocalDateTime timePosted;

    private UserDto author;

    private TaskDto task;
}
