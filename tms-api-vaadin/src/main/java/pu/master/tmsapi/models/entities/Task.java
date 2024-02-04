package pu.master.tmsapi.models.entities;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import pu.master.tmsapi.models.enums.TaskPriority;
import pu.master.tmsapi.models.enums.TaskStatus;


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

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(targetEntity = Comment.class, mappedBy = "task", fetch = FetchType.EAGER)
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
                    name = "user_tasks",
                    joinColumns = @JoinColumn(name = "task_id"),
                    inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;


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


    public Project getProject()
    {
        return project;
    }


    public Task setProject(final Project project)
    {
        this.project = project;
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


    public List<User> getUsers()
    {
        return users;
    }


    public Task setUsers(final List<User> users)
    {
        this.users = users;
        return this;
    }
}
