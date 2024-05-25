package pu.master.core.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pu.master.domain.models.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findUserByUsername(String username);

    boolean existsByUsername(String username);

}
