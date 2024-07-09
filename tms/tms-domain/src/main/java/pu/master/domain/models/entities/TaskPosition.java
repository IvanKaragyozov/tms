package pu.master.domain.models.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor

@Entity
@Table(name = "task_positions")
public class TaskPosition extends BaseEntity
{

    private boolean isDone;

    private String positionDescription;
}
