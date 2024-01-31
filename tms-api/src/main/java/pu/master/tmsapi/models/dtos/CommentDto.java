package pu.master.tmsapi.models.dtos;


import java.time.LocalDateTime;


// TODO: Add validation
public class CommentDto extends BaseDto
{

    private String text;

    private LocalDateTime timePosted;

    private UserDto author;

    private TaskDto task;


    public CommentDto()
    {
    }


    public String getText()
    {
        return text;
    }


    public LocalDateTime getTimePosted()
    {
        return timePosted;
    }


    public UserDto getAuthor()
    {
        return author;
    }


    public TaskDto getTask()
    {
        return task;
    }
}
