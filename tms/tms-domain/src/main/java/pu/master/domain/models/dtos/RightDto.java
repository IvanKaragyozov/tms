package pu.master.domain.models.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class RightDto extends BaseDto
{

    private String name;


    public RightDto(final long id, final String name)
    {
        super(id);
        this.name = name;
    }

}
