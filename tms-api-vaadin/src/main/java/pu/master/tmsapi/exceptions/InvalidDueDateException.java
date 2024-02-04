package pu.master.tmsapi.exceptions;


public class InvalidDueDateException extends RuntimeException
{
    public InvalidDueDateException()
    {
    }


    public InvalidDueDateException(final String message)
    {
        super(message);
    }
}
