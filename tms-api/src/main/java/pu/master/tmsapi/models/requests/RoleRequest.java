package pu.master.tmsapi.models.requests;


import java.util.Set;


// TODO: Add validation
public class RoleRequest
{

    private String name;

    private Set<Long> rights;


    public RoleRequest()
    {
    }


    public RoleRequest(final String name, final Set<Long> rights)
    {
        this.name = name;
        this.rights = rights;
    }


    public String getName()
    {
        return name;
    }


    public Set<Long> getRights()
    {
        return rights;
    }


    public RoleRequest setName(final String name)
    {
        this.name = name;
        return this;
    }


    public RoleRequest setRights(final Set<Long> rights)
    {
        this.rights = rights;
        return this;
    }
}
