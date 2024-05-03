package pu.master.tmsapi.exceptions;


public class RightNotFoundException extends RuntimeException
{

    public RightNotFoundException()
    {
    }


    public RightNotFoundException(final String message)
    {
        super(message);
    }
}
