package pu.master.core.exceptions;


public class DateInvalidException extends RuntimeException
{
    public DateInvalidException()
    {
    }


    public DateInvalidException(final String message)
    {
        super(message);
    }
}
