package pu.master.tmsapi.models.dtos;


public class RightDto extends BaseDto
{

    private String name;


    public RightDto()
    {
    }


    public RightDto(final long id, final String name)
    {
        super(id);
        this.name = name;
    }


    public String getName()
    {
        return name;
    }
}
