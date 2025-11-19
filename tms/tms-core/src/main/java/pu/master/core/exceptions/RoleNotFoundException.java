package pu.master.core.exceptions;


public class RoleNotFoundException extends TMSValidationException
{

    public RoleNotFoundException()
    {
    }


    public RoleNotFoundException(final String message)
    {
        super(message);
    }
}
