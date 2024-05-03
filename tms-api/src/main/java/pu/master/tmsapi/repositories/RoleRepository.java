package pu.master.tmsapi.repositories;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pu.master.tmsapi.models.entities.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{

    Optional<Role> findRoleByName(final String name);

    boolean existsByName(final String name);
}
