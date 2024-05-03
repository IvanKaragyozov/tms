package pu.master.tmsapi.exceptions;


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
