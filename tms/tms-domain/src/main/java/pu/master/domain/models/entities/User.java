package pu.master.domain.models.entities;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor

@Entity
@Table(name = "users")
public class User extends BaseEntity
{

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_created_at")
    private LocalDate dateCreatedAt;

    @Column(name = "date_last_modified_at")
    private LocalDate dateLastModifiedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
                    name = "user_roles",
                    joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
                    name = "user_tasks",
                    joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<Task> tasks;


    public void addRole(final Role role)
    {
        if (this.roles == null)
        {
            this.roles = new HashSet<>(2);
        }
        this.roles.add(role);
    }

    // TODO: Add implementation of equals() and hashCode()
}
