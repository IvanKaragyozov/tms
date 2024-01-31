package pu.master.tmsapi.models.entities;


import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "roles")
public class Role extends BaseEntity
{

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
                    name = "role_rights",
                    joinColumns = @JoinColumn(name = "role_id"),
                    inverseJoinColumns = @JoinColumn(name = "right_id")
    )
    private Set<Right> rights;


    public Role()
    {
    }


    public String getName()
    {
        return name;
    }


    public Role setName(final String name)
    {
        this.name = name;
        return this;
    }


    public Set<Right> getRights()
    {
        return rights;
    }


    public Role setRights(final Set<Right> rights)
    {
        this.rights = rights;
        return this;
    }


    public Role addRight(final Right right)
    {
        this.rights.add(right);
        return this;
    }

}
