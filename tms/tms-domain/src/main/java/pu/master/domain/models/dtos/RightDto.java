package pu.master.domain.models.dtos;


import lombok.Getter;


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
