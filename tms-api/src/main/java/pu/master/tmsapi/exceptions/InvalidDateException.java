package pu.master.tmsapi.exceptions;


public class InvalidDateException extends RuntimeException
{
    public InvalidDateException()
    {
    }


    public InvalidDateException(final String message)
    {
        super(message);
    }
}
