package pu.master.domain.models.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity
{

    @Column(name = "text")
    private String text;

    @Column(name = "time_posted")
    private LocalDateTime timePosted;

    @ManyToOne // TODO: Change fetch type to lazy
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne // TODO: Change fetch type to lazy
    @JoinColumn(name = "task_id")
    private Task task;


    public Comment(final long id, final String text, final LocalDateTime timePosted, final User author, final Task task)
    {
        super(id);
        this.text = text;
        this.timePosted = timePosted;
        this.author = author;
        this.task = task;
    }

    // TODO: Add implementation of equals() and hashCode()
}
