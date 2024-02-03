package pu.master.tmsapi.models.requests;


import java.time.LocalDateTime;


// TODO: Add validation
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


    public CommentRequest setText(final String text)
    {
        this.text = text;
        return this;
    }


    public LocalDateTime getTimePosted()
    {
        return timePosted;
    }


    public CommentRequest setTimePosted(final LocalDateTime timePosted)
    {
        this.timePosted = timePosted;
        return this;
    }


    public Long getAuthor()
    {
        return author;
    }


    public CommentRequest setAuthor(final Long author)
    {
        this.author = author;
        return this;
    }


    public Long getTask()
    {
        return task;
    }


    public CommentRequest setTask(final Long task)
    {
        this.task = task;
        return this;
    }
}
