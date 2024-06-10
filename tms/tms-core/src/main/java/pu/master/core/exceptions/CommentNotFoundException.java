package pu.master.core.exceptions;


public class CommentNotFoundException extends RuntimeException
{

    public CommentNotFoundException()
    {
    }


    public CommentNotFoundException(final String message)
    {
        super(message);
    }

}
