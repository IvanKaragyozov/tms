package pu.master.tmsapi.models.dtos;


public abstract class BaseDto
{

    private long id;


    public long getId()
    {
        return id;
    }


    public BaseDto setId(final long id)
    {
        this.id = id;
        return this;
    }
}
