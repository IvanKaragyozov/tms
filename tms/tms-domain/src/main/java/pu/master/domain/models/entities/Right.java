package pu.master.domain.models.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "rights")
public class Right extends BaseEntity
{

    @Column(name = "name")
    private String name;


    public Right(final long id, final String name)
    {
        super(id);
        this.name = name;
    }

    // TODO: Add implementation of equals() and hashCode()
}
