package pu.master.domain.models.entities;


import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor

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


    public Role(final String name)
    {
        this.name = name;
    }


    public Role(final long id, final String name, final Set<Right> rights)
    {
        super(id);
        this.name = name;
        this.rights = rights;
    }


    public Role addRight(final Right right)
    {
        this.rights.add(right);
        return this;
    }


    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Role role = (Role) o;

        return Objects.equals(name, role.name);
    }


    @Override
    public int hashCode()
    {
        return name != null ? name.hashCode() : 0;
    }

}
