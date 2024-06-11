package pu.master.tmsapi.models.dtos;


import java.time.LocalDateTime;


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
