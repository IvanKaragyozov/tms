package pu.master.tmsapi.models.dtos;


import java.util.Set;


// TODO: Add validation
public class RoleDto extends BaseDto
{

    private String name;

    private Set<RightDto> rights;
}