package pu.master.domain.models.requests;


import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest
{

    @NotEmpty(message = "Username cannot be empty")
    private String username;

    private String password;

}
