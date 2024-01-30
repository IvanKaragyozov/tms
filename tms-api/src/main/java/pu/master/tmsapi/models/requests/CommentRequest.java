package pu.master.tmsapi.models.requests;


import java.time.LocalDateTime;


// TODO: Add validation
public class CommentRequest
{

    private String text;

    private LocalDateTime timePosted;

    private Integer author;

    private Integer task;


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


    public Integer getAuthor()
    {
        return author;
    }


    public Integer getTask()
    {
        return task;
    }
}
