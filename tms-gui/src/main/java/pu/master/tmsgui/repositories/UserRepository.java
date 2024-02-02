package pu.master.tmsgui.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pu.master.tmsgui.models.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
}
