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
}
