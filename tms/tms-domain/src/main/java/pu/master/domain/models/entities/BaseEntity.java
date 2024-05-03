package pu.master.domain.models.entities;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor

@MappedSuperclass
public abstract class BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
