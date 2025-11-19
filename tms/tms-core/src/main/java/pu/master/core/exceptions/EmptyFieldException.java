package pu.master.core.exceptions;


public class EmptyFieldException extends TMSValidationException
{
    public EmptyFieldException()
    {
    }


    public EmptyFieldException(final String message)
    {
        super(message);
    }
}
