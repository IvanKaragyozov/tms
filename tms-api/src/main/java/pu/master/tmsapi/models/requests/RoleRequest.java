package pu.master.tmsapi.models.requests;


import java.util.Set;


public class RoleRequest
{

    private String name;

    private Set<String> rights;


    public RoleRequest()
    {
    }


    public RoleRequest(final String name, final Set<String> rights)
    {
        this.name = name;
        this.rights = rights;
    }


    public String getName()
    {
        return name;
    }


    public Set<String> getRights()
    {
        return rights;
    }


    public RoleRequest setName(final String name)
    {
        this.name = name;
        return this;
    }


    public RoleRequest setRights(final Set<String> rights)
    {
        this.rights = rights;
        return this;
    }
}
