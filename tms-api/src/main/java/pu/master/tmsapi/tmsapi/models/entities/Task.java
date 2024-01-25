package pu.master.tmsapi.tmsapi.models.entities;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import pu.master.tmsapi.tmsapi.models.enums.TaskPriority;
import pu.master.tmsapi.tmsapi.models.enums.TaskStatus;


@Entity
@Table(name = "tasks")
public class Task extends BaseEntity
{

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority_level")
    private TaskPriority priorityLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;
    @ManyToOne(targetEntity = Milestone.class)
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;

    @OneToMany(targetEntity = Comment.class, mappedBy = "task", fetch = FetchType.LAZY)
    private List<Comment> comments;


    public Task()
    {
    }


    public String getTitle()
    {
        return title;
    }


    public Task setTitle(final String title)
    {
        this.title = title;
        return this;
    }


    public String getDescription()
    {
        return description;
    }


    public Task setDescription(final String description)
    {
        this.description = description;
        return this;
    }


    public TaskPriority getPriorityLevel()
    {
        return priorityLevel;
    }


    public Task setPriorityLevel(final TaskPriority priorityLevel)
    {
        this.priorityLevel = priorityLevel;
        return this;
    }


    public TaskStatus getStatus()
    {
        return status;
    }


    public Task setStatus(final TaskStatus status)
    {
        this.status = status;
        return this;
    }


    public Milestone getMilestone()
    {
        return milestone;
    }


    public Task setMilestone(final Milestone milestone)
    {
        this.milestone = milestone;
        return this;
    }


    public List<Comment> getComments()
    {
        return comments;
    }


    public Task setComments(final List<Comment> comments)
    {
        this.comments = comments;
        return this;
    }
}
