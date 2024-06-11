package pu.master.tmsapi.models.requests;


import java.time.LocalDateTime;


public class CommentRequest
{

    private String text;

    private LocalDateTime timePosted;

    private Long author;

    private Long task;


    public CommentRequest()
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


    public Long getAuthor()
    {
        return author;
    }


    public Long getTask()
    {
        return task;
    }
}
