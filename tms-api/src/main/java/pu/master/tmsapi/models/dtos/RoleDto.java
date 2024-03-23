package pu.master.tmsapi.models.dtos;


import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class RoleDto extends BaseDto
{

    private String name;

    private Set<RightDto> rights;

}
