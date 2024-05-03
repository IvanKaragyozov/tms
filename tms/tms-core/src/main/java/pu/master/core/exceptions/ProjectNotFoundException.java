package pu.master.core.exceptions;


public class ProjectNotFoundException extends RuntimeException
{
    public ProjectNotFoundException()
    {
    }


    public ProjectNotFoundException(final String message)
    {
        super(message);
    }
}
