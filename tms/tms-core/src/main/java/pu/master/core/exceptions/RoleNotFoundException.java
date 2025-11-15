package pu.master.core.exceptions;


public class RoleNotFoundException extends RuntimeException
{

    public RoleNotFoundException()
    {
    }


    public RoleNotFoundException(final String message)
    {
        super(message);
    }
}
