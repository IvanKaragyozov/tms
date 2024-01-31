package pu.master.tmsapi.models.dtos;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;


// TODO: Add validation
public class UserDto extends BaseDto
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

    private List<ProjectDto> projects;

    private Set<RoleDto> roles;


    public UserDto()
    {
    }


    public String getUsername()
    {
        return username;
    }


    public String getPassword()
    {
        return password;
    }


    public String getEmail()
    {
        return email;
    }


    public String getFirstName()
    {
        return firstName;
    }


    public String getLastName()
    {
        return lastName;
    }


    public boolean isActive()
    {
        return isActive;
    }


    public String getPhoneNumber()
    {
        return phoneNumber;
    }


    public LocalDate getDateCreatedAt()
    {
        return dateCreatedAt;
    }


    public LocalDate getDateLastModifiedAt()
    {
        return dateLastModifiedAt;
    }


    public List<ProjectDto> getProjects()
    {
        return projects;
    }


    public Set<RoleDto> getRoles()
    {
        return roles;
    }

}
