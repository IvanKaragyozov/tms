package pu.master.tmsapi.models.requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class UserRequest
{

    @NotBlank(message = "Username cannot be blank!")
    private String username;

    @NotBlank(message = "Password cannot be blank!")
    private String password;

    @NotBlank(message = "Email cannot be black!")
    @Email(regexp = "([a-zA-Z0-9])+@\\.([a-zA-Z])+", message = "Invalid email! Correct pattern is: a@.a")
    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

}
