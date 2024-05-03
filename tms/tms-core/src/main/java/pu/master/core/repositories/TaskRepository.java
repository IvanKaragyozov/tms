package pu.master.tmsapi.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pu.master.tmsapi.models.entities.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>
{

    List<Task> findTasksByUsersId(long userId);

}
