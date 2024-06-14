package pu.master.core.exceptions;


public class ForbiddenActionException extends RuntimeException
{

    public ForbiddenActionException()
    {
    }


    public ForbiddenActionException(final String message)
    {
        super(message);
    }
}
