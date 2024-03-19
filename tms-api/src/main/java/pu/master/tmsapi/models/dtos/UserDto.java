package pu.master.tmsapi.models.dtos;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class UserDto extends BaseDto
{

    private String username;

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
