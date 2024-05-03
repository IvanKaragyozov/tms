package pu.master.core.exceptions;


public class TaskNotFoundException extends RuntimeException
{

    public TaskNotFoundException()
    {
    }


    public TaskNotFoundException(final String message)
    {
        super(message);
    }
}
