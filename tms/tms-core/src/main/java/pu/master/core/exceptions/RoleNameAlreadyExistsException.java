package pu.master.core.exceptions;


public class RoleNameAlreadyExistsException extends TMSValidationException
{

    public RoleNameAlreadyExistsException()
    {
    }


    public RoleNameAlreadyExistsException(final String message)
    {
        super(message);
    }
}
