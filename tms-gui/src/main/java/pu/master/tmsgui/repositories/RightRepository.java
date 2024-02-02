package pu.master.tmsgui.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pu.master.tmsgui.models.entities.Right;


@Repository
public interface RightRepository extends JpaRepository<Right, Long>
{
}
