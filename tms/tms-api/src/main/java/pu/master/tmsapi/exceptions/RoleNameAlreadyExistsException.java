package pu.master.tmsapi.exceptions;


public class RoleNameAlreadyExistsException extends RuntimeException
{

    public RoleNameAlreadyExistsException()
    {
    }


    public RoleNameAlreadyExistsException(final String message)
    {
        super(message);
    }
}
