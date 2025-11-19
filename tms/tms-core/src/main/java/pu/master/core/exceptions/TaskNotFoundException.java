package pu.master.core.exceptions;


public class TaskNotFoundException extends TMSRuntimeException
{

    public TaskNotFoundException()
    {
    }


    public TaskNotFoundException(final String message)
    {
        super(message);
    }
}
