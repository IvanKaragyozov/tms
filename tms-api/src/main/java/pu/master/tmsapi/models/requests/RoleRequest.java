package pu.master.tmsapi.models.requests;


import java.util.Set;
import lombok.Data;


@Data
public class RoleRequest
{

    private String name;

    private Set<String> rights;

}
