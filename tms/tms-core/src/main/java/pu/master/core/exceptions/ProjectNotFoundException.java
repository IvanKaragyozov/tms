package pu.master.core.exceptions;


public class ProjectNotFoundException extends TMSRuntimeException
{
    public ProjectNotFoundException()
    {
    }


    public ProjectNotFoundException(final String message)
    {
        super(message);
    }
}
