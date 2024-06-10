package pu.master.core.exceptions;


public class UsernameAlreadyExistsException extends RuntimeException
{

    public UsernameAlreadyExistsException()
    {
    }


    public UsernameAlreadyExistsException(final String message)
    {
        super(message);
    }
}
