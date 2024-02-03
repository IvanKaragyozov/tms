package pu.master.tmsapi.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pu.master.tmsapi.models.entities.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>
{

    List<Comment> findCommentsByTaskId(long taskId);
}
