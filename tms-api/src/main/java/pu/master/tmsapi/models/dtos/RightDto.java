package pu.master.tmsapi.models.dtos;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class RightDto extends BaseDto
{

    private String name;

    public RightDto(final long id, final String name)
    {
        super(id);
        this.name = name;
    }

}
