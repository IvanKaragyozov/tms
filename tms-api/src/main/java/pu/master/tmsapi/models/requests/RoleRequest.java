package pu.master.tmsapi.models.requests;


import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class RoleRequest
{

    private String name;

    private Set<String> rights;

}
