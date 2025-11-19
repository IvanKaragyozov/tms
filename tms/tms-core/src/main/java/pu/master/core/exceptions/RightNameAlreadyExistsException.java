package pu.master.core.exceptions;


public class RightNameAlreadyExistsException extends TMSValidationException
{

    public RightNameAlreadyExistsException()
    {
    }


    public RightNameAlreadyExistsException(final String message)
    {
        super(message);
    }
}
