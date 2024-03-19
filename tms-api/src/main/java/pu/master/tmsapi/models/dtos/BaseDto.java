package pu.master.tmsapi.models.dtos;


public abstract class BaseDto
{

    private long id;


    public BaseDto()
    {
    }


    public BaseDto(final long id)
    {
        this.id = id;
    }


    public long getId()
    {
        return id;
    }
}
