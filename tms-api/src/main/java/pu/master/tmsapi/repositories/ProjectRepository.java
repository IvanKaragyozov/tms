package pu.master.tmsapi.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pu.master.tmsapi.models.entities.Project;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>
{

    List<Project> findProjectsByUsersId(long userId);
}
