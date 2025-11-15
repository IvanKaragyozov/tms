package pu.master.domain.models.requests;


import jakarta.validation.constraints.NotEmpty;


public class LoginRequest
{

    @NotEmpty(message = "Username cannot be empty")
    private String username;

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
