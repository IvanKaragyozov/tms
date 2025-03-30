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
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pu.master.domain.models.enums.ProjectPriority;


@Getter
@Setter
@RequiredArgsConstructor

@Entity
@Table(name = "projects")
public class Project extends BaseEntity
{

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "date_last_modified")
    private LocalDateTime dateLastModified;

    @Column(name = "date_due")
    private LocalDateTime dateDue;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private ProjectPriority priorityLevel;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
                    name = "project_tasks",
                    joinColumns = @JoinColumn(name = "project_id"),
                    inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<Task> tasks;


    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
                    name = "user_projects",
                    joinColumns = @JoinColumn(name = "project_id"),
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


    public void addTask(final Task task)
    {
        if (this.tasks == null)
        {
            this.tasks = new HashSet<>();
        }
        this.tasks.add(task);
    }
}