package pu.master.tmsapi.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pu.master.tmsapi.models.entities.Project;
import pu.master.tmsapi.models.enums.ProjectPriority;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>
{

    @Query("FROM Project p LEFT JOIN FETCH p.tasks")
    List<Project> findAllProjects();
    List<Project> findProjectsByUsersId(long userId);

    List<Project> findProjectsByPriorityLevel(final ProjectPriority projectPriority);

    List<Project> findProjectsByTitle(final String title);
}
