package pu.master.tmsapi.models.entities;


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
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pu.master.tmsapi.models.enums.ProjectPriority;


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



    // TODO: Add implementation of equals() and hashCode()
}
