package pu.master.tmsapi.models.requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class UserRequest
{

    @NotBlank(message = "Username cannot be blank!")
    private String username;

    @NotBlank(message = "Password cannot be blank!")
    private String password;

    @NotBlank(message = "Email cannot be blank!")
    @Email(regexp = "([a-zA-Z0-9])+@\\.([a-zA-Z])+", message = "Invalid email! Correct pattern is: a@.a")
    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;


    public String getUsername()
    {
        return username;
    }


    public UserRequest setUsername(final String username)
    {
        this.username = username;
        return this;
    }


    public String getPassword()
    {
        return password;
    }


    public UserRequest setPassword(final String password)
    {
        this.password = password;
        return this;
    }


    public String getEmail()
    {
        return email;
    }


    public UserRequest setEmail(final String email)
    {
        this.email = email;
        return this;
    }


    public String getFirstName()
    {
        return firstName;
    }


    public UserRequest setFirstName(final String firstName)
    {
        this.firstName = firstName;
        return this;
    }


    public String getLastName()
    {
        return lastName;
    }


    public UserRequest setLastName(final String lastName)
    {
        this.lastName = lastName;
        return this;
    }


    public String getPhoneNumber()
    {
        return phoneNumber;
    }


    public UserRequest setPhoneNumber(final String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
