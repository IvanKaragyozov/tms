package pu.master.tmsapi.models.requests;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;


// TODO: Add validation
public class UserRequest
{

    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private boolean isActive;

    private String phoneNumber;

    private LocalDate dateCreatedAt;

    private LocalDate dateLastModifiedAt;

    private List<Long> projects;

    private Set<Long> roles;


    public String getUsername()
    {
        return username;
    }


    public UserRequest setUsername(final String username)
    {
        this.username = username;
        return this;
    }


    public String getPassword()
    {
        return password;
    }


    public UserRequest setPassword(final String password)
    {
        this.password = password;
        return this;
    }


    public String getEmail()
    {
        return email;
    }


    public UserRequest setEmail(final String email)
    {
        this.email = email;
        return this;
    }


    public String getFirstName()
    {
        return firstName;
    }


    public UserRequest setFirstName(final String firstName)
    {
        this.firstName = firstName;
        return this;
    }


    public String getLastName()
    {
        return lastName;
    }


    public UserRequest setLastName(final String lastName)
    {
        this.lastName = lastName;
        return this;
    }


    public boolean isActive()
    {
        return isActive;
    }


    public UserRequest setActive(final boolean active)
    {
        isActive = active;
        return this;
    }


    public String getPhoneNumber()
    {
        return phoneNumber;
    }


    public UserRequest setPhoneNumber(final String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
        return this;
    }


    public LocalDate getDateCreatedAt()
    {
        return dateCreatedAt;
    }


    public UserRequest setDateCreatedAt(final LocalDate dateCreatedAt)
    {
        this.dateCreatedAt = dateCreatedAt;
        return this;
    }


    public LocalDate getDateLastModifiedAt()
    {
        return dateLastModifiedAt;
    }


    public UserRequest setDateLastModifiedAt(final LocalDate dateLastModifiedAt)
    {
        this.dateLastModifiedAt = dateLastModifiedAt;
        return this;
    }


    public List<Long> getProjects()
    {
        return projects;
    }


    public UserRequest setProjects(final List<Long> projects)
    {
        this.projects = projects;
        return this;
    }


    public Set<Long> getRoles()
    {
        return roles;
    }


    public UserRequest setRoles(final Set<Long> roles)
    {
        this.roles = roles;
        return this;
    }
}
