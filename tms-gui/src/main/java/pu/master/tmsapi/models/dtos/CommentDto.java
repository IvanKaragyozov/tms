package pu.master.tmsapi.models.dtos;


import java.time.LocalDateTime;


// TODO: Add validation
public class CommentDto extends BaseDto
{

    private String text;

    private LocalDateTime timePosted;

    private TaskDto taskDto;


    public CommentDto()
    {
    }


    public String getText()
    {
        return text;
    }


    public CommentDto setText(final String text)
    {
        this.text = text;
        return this;
    }


    public LocalDateTime getTimePosted()
    {
        return timePosted;
    }


    public CommentDto setTimePosted(final LocalDateTime timePosted)
    {
        this.timePosted = timePosted;
        return this;
    }


    public TaskDto getTaskDto()
    {
        return taskDto;
    }


    public CommentDto setTaskDto(final TaskDto taskDto)
    {
        this.taskDto = taskDto;
        return this;
    }
}
