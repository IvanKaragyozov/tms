package pu.master.tmsapi.models.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "rights")
public class Right extends BaseEntity
{

    @Column(name = "name")
    private String name;


    public Right()
    {
    }


    public Right(final String name)
    {
        this.name = name;
    }


    public Right(final long id, final String name)
    {
        super(id);
        this.name = name;
    }


    public String getName()
    {
        return name;
    }


    public Right setName(final String name)
    {
        this.name = name;
        return this;
    }
}
