package pu.master.tmsapi.models.entities;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import pu.master.tmsapi.models.enums.ProjectPriority;


@Entity
@Table(name = "projects")
public class Project extends BaseEntity
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
    private ProjectPriority priorityLevel;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Task> tasks;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
                    name = "user_projects",
                    joinColumns = @JoinColumn(name = "project_id"),
                    inverseJoinColumns = @JoinColumn(name = "user_id")

    )
    private Set<User> users;


    public Project()
    {
    }


    public String getTitle()
    {
        return title;
    }


    public Project setTitle(final String title)
    {
        this.title = title;
        return this;
    }


    public String getDescription()
    {
        return description;
    }


    public Project setDescription(final String description)
    {
        this.description = description;
        return this;
    }


    public LocalDate getDateCreated()
    {
        return dateCreated;
    }


    public Project setDateCreated(final LocalDate dateCreated)
    {
        this.dateCreated = dateCreated;
        return this;
    }


    public LocalDate getDueDate()
    {
        return dueDate;
    }


    public Project setDueDate(final LocalDate timeDue)
    {
        this.dueDate = timeDue;
        return this;
    }


    public ProjectPriority getPriorityLevel()
    {
        return priorityLevel;
    }


    public Project setPriorityLevel(final ProjectPriority priority)
    {
        this.priorityLevel = priority;
        return this;
    }


    public List<Task> getTasks()
    {
        return tasks;
    }


    public Project setTasks(final List<Task> tasks)
    {
        this.tasks = tasks;
        return this;
    }


    public Set<User> getUsers()
    {
        return users;
    }


    public Project setUsers(final Set<User> users)
    {
        this.users = users;
        return this;
    }


    public Project addUser(final User user)
    {
        this.users.add(user);
        return this;
    }
}
