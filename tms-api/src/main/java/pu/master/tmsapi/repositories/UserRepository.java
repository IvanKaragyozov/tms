package pu.master.tmsapi.repositories;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pu.master.tmsapi.models.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findUserByUsername(String username);
}
