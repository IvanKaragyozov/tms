package pu.master.tmsapi.models.entities;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    public BaseEntity()
    {
    }


    public BaseEntity(final long id)
    {
        this.id = id;
    }


    public long getId()
    {
        return id;
    }
}
