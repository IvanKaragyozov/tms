package pu.master.tmsapi.exceptions;


public class RightNameAlreadyExistsException extends RuntimeException
{

    public RightNameAlreadyExistsException()
    {
    }


    public RightNameAlreadyExistsException(final String message)
    {
        super(message);
    }
}
