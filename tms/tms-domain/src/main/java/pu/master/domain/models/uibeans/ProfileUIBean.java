package pu.master.domain.models.uibeans;


import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class ProfileUIBean
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
