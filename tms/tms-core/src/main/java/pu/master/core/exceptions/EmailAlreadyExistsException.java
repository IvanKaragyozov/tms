package pu.master.core.exceptions;


public class EmailAlreadyExistsException extends RuntimeException
{

    public EmailAlreadyExistsException()
    {
        super();
    }


    public EmailAlreadyExistsException(final String message)
    {
        super(message);
    }
}
