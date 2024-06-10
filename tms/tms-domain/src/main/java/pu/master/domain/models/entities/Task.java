package pu.master.domain.models.entities;


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

import java.util.List;

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

    @ManyToMany
    @JoinTable(
                    name = "user_tasks",
                    joinColumns = @JoinColumn(name = "task_id"),
                    inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    // TODO: Add implementation of equals() and hashCode()
}
