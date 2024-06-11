package pu.master.domain.models.entities;


import java.util.ArrayList;
import java.util.HashSet;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(targetEntity = Comment.class, mappedBy = "task", fetch = FetchType.LAZY)
    private List<Comment> comments;

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
}
