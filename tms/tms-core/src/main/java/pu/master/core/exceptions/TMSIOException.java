package pu.master.core.exceptions;


/**
 * Runtime exception for I/O related errors in the TMS application.
 */
public class TMSIOException extends TMSRuntimeException
{
    public TMSIOException() {}


    public TMSIOException(final String message)
    {
        super(message);
    }

    public TMSIOException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
