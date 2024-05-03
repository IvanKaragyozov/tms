package pu.master.core.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pu.master.domain.models.entities.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>
{

    List<Comment> findCommentsByTaskId(long taskId);
}
