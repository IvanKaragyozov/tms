package pu.master.tmsgui.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pu.master.tmsgui.models.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>
{

    Optional<User> findByUsername(String username);
}
