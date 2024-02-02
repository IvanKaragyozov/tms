package pu.master.tmsgui.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pu.master.tmsgui.models.entities.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>
{

    List<Task> findTasksByUsersId(long userId);

}
