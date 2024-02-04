package pu.master.tmsapi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pu.master.tmsapi.models.entities.Right;


@Repository
public interface RightRepository extends JpaRepository<Right, Long>
{
}
