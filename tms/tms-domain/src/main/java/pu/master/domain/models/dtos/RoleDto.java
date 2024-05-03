package pu.master.domain.models.dtos;


import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RoleDto extends BaseDto
{

    private String name;

    private Set<RightDto> rights;


    public RoleDto(final long id, final String name, final Set<RightDto> rights)
    {
        super(id);
        this.name = name;
        this.rights = rights;
    }
}
