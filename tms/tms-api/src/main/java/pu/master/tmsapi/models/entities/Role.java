package pu.master.tmsapi.models.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "roles")
public class Role extends BaseEntity
{

    @Column(name = "name", nullable = false)
    private String name;

    @Cascade(CascadeType.PERSIST)
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

    // TODO: Add implementation of equals() and hashCode()

}
