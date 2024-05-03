package pu.master.domain.models.requests;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;


public class LoginRequest
{

    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @Pattern(regexp = "^.{8,}$", message = "Password should be at least 8 symbols long")
    @NotEmpty(message = "Password cannot be empty")
    private String password;


    public LoginRequest()
    {
    }


    public String getUsername()
    {
        return username;
    }


    public LoginRequest setUsername(final String username)
    {
        this.username = username;
        return this;
    }


    public String getPassword()
    {
        return password;
    }


    public LoginRequest setPassword(final String password)
    {
        this.password = password;
        return this;
    }
}
