package pu.master.tmsapi.models.entities;


import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import pu.master.tmsapi.models.enums.MilestonePriority;


@Entity
@Table(name = "milestones")
public class Milestone extends BaseEntity
{

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "time_due")
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private MilestonePriority priorityLevel;

    @OneToMany(mappedBy = "milestone", fetch = FetchType.LAZY)
    private List<Task> tasks;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<User> users;


    public Milestone()
    {
    }


    public String getTitle()
    {
        return title;
    }


    public Milestone setTitle(final String title)
    {
        this.title = title;
        return this;
    }


    public String getDescription()
    {
        return description;
    }


    public Milestone setDescription(final String description)
    {
        this.description = description;
        return this;
    }


    public LocalDate getDateCreated()
    {
        return dateCreated;
    }


    public Milestone setDateCreated(final LocalDate dateCreated)
    {
        this.dateCreated = dateCreated;
        return this;
    }


    public LocalDate getDueDate()
    {
        return dueDate;
    }


    public Milestone setDueDate(final LocalDate timeDue)
    {
        this.dueDate = timeDue;
        return this;
    }


    public MilestonePriority getPriorityLevel()
    {
        return priorityLevel;
    }


    public Milestone setPriorityLevel(final MilestonePriority priority)
    {
        this.priorityLevel = priority;
        return this;
    }


    public List<Task> getTasks()
    {
        return tasks;
    }


    public Milestone setTasks(final List<Task> tasks)
    {
        this.tasks = tasks;
        return this;
    }


    public List<User> getUsers()
    {
        return users;
    }


    public Milestone setUsers(final List<User> users)
    {
        this.users = users;
        return this;
    }


    public Milestone addUser(final User user)
    {
        this.users.add(user);
        return this;
    }
}
