package pu.master.domain.models.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "subtasks")
public class Subtask extends BaseEntity
{

    @Column(name = "title")
    private String title;

    @Column(name = "is-finished")
    private boolean isFinished;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
