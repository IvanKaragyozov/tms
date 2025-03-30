package pu.master.tms.models.requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class RegistrationRequest
{

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Pattern(regexp = "^.{8,}$", message = "Password should be at least 8 symbols long")
    private String password;

    @NotBlank(message = "Email cannot be blank")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email address")
    private String email;

    @Pattern(regexp = "^[a-zA-Z]*$", message = "First name cannot contain numbers")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]*$", message = "Last name cannot contain numbers")
    private String lastName;

    @Pattern(regexp = "^\\+?[0-9\\s()]*$",
             message = "Phone number can only contain digits, spaces, parentheses and optionally a leading +")
    private String phoneNumber;

}
