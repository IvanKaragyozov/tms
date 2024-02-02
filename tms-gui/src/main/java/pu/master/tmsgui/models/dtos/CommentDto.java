package pu.master.tmsgui.models.dtos;


import java.time.LocalDateTime;


// TODO: Add validation
public class CommentDto extends BaseDto
{

    private String text;

    private LocalDateTime timePosted;


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

}
