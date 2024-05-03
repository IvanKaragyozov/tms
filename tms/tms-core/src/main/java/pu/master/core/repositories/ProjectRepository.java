package pu.master.core.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pu.master.domain.models.entities.Project;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>
{

    List<Project> findProjectsByUsersId(long userId);
}
