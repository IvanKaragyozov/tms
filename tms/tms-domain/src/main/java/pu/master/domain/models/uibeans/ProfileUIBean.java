package pu.master.domain.models.dtos;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
// Rename to ProfileUIBean and don't inherit BaseDto
public class UserUIBean extends BaseDto
{

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private boolean isActive;

    private String phoneNumber;

    private LocalDate dateCreatedAt;

    private LocalDate dateLastModifiedAt;

}
