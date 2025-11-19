package pu.master.core.exceptions;


public class ForbiddenActionException extends TMSRuntimeException
{

    public ForbiddenActionException()
    {
    }


    public ForbiddenActionException(final String message)
    {
        super(message);
    }
}
