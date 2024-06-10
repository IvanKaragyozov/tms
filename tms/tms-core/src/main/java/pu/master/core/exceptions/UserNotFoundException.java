package pu.master.core.exceptions;


public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException()
    {
    }


    public UserNotFoundException(final String message)
    {
        super(message);
    }
}
