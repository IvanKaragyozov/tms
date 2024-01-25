package pu.master.tmsapi.tmsapi.models.entities;


import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
                    name = "user_roles",
                    joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;


    public User()
    {
    }


    public String getUsername()
    {
        return username;
    }


    public User setUsername(final String username)
    {
        this.username = username;
        return this;
    }


    public String getPassword()
    {
        return password;
    }


    public User setPassword(final String password)
    {
        this.password = password;
        return this;
    }


    public String getEmail()
    {
        return email;
    }


    public User setEmail(final String email)
    {
        this.email = email;
        return this;
    }


    public String getFirstName()
    {
        return firstName;
    }


    public User setFirstName(final String firstName)
    {
        this.firstName = firstName;
        return this;
    }


    public String getLastName()
    {
        return lastName;
    }


    public User setLastName(final String lastName)
    {
        this.lastName = lastName;
        return this;
    }


    public boolean isActive()
    {
        return isActive;
    }


    public User setActive(final boolean active)
    {
        isActive = active;
        return this;
    }


    public String getPhoneNumber()
    {
        return phoneNumber;
    }


    public User setPhoneNumber(final String phone)
    {
        this.phoneNumber = phone;
        return this;
    }


    public LocalDate getDateCreatedAt()
    {
        return dateCreatedAt;
    }


    public User setDateCreatedAt(final LocalDate dateCreatedAt)
    {
        this.dateCreatedAt = dateCreatedAt;
        return this;
    }


    public LocalDate getDateLastModifiedAt()
    {
        return dateLastModifiedAt;
    }


    public User setDateLastModifiedAt(final LocalDate dateLastModifiedAt)
    {
        this.dateLastModifiedAt = dateLastModifiedAt;
        return this;
    }


    public List<Role> getRoles()
    {
        return roles;
    }


    public User setRoles(final List<Role> roles)
    {
        this.roles = roles;
        return this;
    }
}
