package pu.master.tmsapi.models.requests;


import java.util.Set;


// TODO: Add validation
public class RoleRequest
{

    private String name;

    private Set<Integer> rights;


    public RoleRequest()
    {
    }


    public RoleRequest(final String name, final Set<Integer> rights)
    {
        this.name = name;
        this.rights = rights;
    }


    public String getName()
    {
        return name;
    }


    public Set<Integer> getRights()
    {
        return rights;
    }


    public RoleRequest setName(final String name)
    {
        this.name = name;
        return this;
    }


    public RoleRequest setRights(final Set<Integer> rights)
    {
        this.rights = rights;
        return this;
    }
}
