package pu.master.tmsapi.exceptions;


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
