package pu.master.domain.models.entities;


import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import pu.master.domain.models.enums.TaskPriority;
import pu.master.domain.models.enums.TaskStatus;


@Getter
@Setter
@RequiredArgsConstructor

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

    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "date_last_modified")
    private LocalDateTime dateLastModified;

    @Column(name = "date_due")
    private LocalDateTime dateDue;

    @OneToMany(mappedBy = "task")
    private Set<Subtask> subtasks;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
                    name = "project_tasks",
                    joinColumns = @JoinColumn(name = "task_id"),
                    inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToMany
    @JoinTable(
                    name = "user_tasks",
                    joinColumns = @JoinColumn(name = "task_id"),
                    inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;


    public void addUser(final User user)
    {
        if (this.users == null)
        {
            this.users = new HashSet<>();
        }

        this.users.add(user);
    }


    public void addProject(final Project project)
    {
        if (this.projects == null)
        {
            this.projects = new HashSet<>();
        }
        this.projects.add(project);
    }
}
