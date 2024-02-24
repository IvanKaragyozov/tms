package pu.master.tmsapi.exceptions;


public class EmptyFieldException extends RuntimeException
{
    public EmptyFieldException()
    {
    }


    public EmptyFieldException(final String message)
    {
        super(message);
    }
}
