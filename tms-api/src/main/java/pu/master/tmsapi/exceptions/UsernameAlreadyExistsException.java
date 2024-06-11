package pu.master.tmsapi.exceptions;


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
