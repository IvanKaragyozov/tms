package pu.master.core.exceptions;


public class UsernameAlreadyExistsException extends TMSValidationException
{

    public UsernameAlreadyExistsException()
    {
    }


    public UsernameAlreadyExistsException(final String message)
    {
        super(message);
    }
}
