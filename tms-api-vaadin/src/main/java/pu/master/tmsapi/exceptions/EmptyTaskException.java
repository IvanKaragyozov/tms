package pu.master.tmsapi.exceptions;


public class EmptyTaskException extends RuntimeException
{
    public EmptyTaskException()
    {
    }


    public EmptyTaskException(final String message)
    {
        super(message);
    }
}
