package pu.master.core.exceptions;


public class UserNotFoundException extends TMSRuntimeException
{
    public UserNotFoundException()
    {
    }


    public UserNotFoundException(final String message)
    {
        super(message);
    }
}
