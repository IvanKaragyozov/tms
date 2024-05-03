package pu.master.tmsapi.exceptions;


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
