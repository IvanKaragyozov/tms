package pu.master.tmsapi.models.entities;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "comments")
public class Comment extends BaseEntity
{

    @Column(name = "text")
    private String text;

    @Column(name = "time_posted")
    private LocalDateTime timePosted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;


    public Comment()
    {
    }


    public String getText()
    {
        return text;
    }


    public Comment setText(final String text)
    {
        this.text = text;
        return this;
    }


    public LocalDateTime getTimePosted()
    {
        return timePosted;
    }


    public Comment setTimePosted(final LocalDateTime timePosted)
    {
        this.timePosted = timePosted;
        return this;
    }


    public User getAuthor()
    {
        return author;
    }


    public Comment setAuthor(final User author)
    {
        this.author = author;
        return this;
    }


    public Task getTask()
    {
        return task;
    }


    public Comment setTask(final Task task)
    {
        this.task = task;
        return this;
    }
}
