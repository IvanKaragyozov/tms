package pu.master.domain.models.entities;


import java.util.Objects;

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

        final Right right = (Right) o;

        return Objects.equals(name, right.name);
    }


    @Override
    public int hashCode()
    {
        return name != null ? name.hashCode() : 0;
    }
}
