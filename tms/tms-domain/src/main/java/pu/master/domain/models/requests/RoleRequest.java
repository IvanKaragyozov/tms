package pu.master.domain.models.requests;


import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest
{

    private String name;

    private Set<String> rights;

}
