package pu.master.core.repositories;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pu.master.domain.models.entities.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{

    Optional<Role> findRoleByName(final String name);

    boolean existsByName(final String name);
}
