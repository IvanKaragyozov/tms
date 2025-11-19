package pu.master.core.exceptions;


public class TMSValidationException extends TMSRuntimeException
{
    public TMSValidationException() {}


    public TMSValidationException(final String message)
    {
        super(message);
    }
}
