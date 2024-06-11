package pu.master.core.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pu.master.domain.models.entities.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>
{

    List<Task> findTasksByUsersId(long userId);

    List<Task> findTasksByUsersEmail(final String email);

    List<Task> findTasksByUsersUsername(final String username);

}
