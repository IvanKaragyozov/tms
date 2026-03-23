package pu.master.core.exceptions;


/**
 * Generic runtime exception for the TMS application. 
 */
public class TMSRuntimeException extends RuntimeException
{
    public TMSRuntimeException() {}


    public TMSRuntimeException(final String message)
    {
        super(message);
    }


    public TMSRuntimeException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
