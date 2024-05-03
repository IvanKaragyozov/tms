package pu.master.tmsapi.models.dtos;


import lombok.Getter;
import lombok.Setter;


@Getter
public class RightDto extends BaseDto
{

    private final String name;


    public RightDto(final long id, final String name)
    {
        super(id);
        this.name = name;
    }

}
