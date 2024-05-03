package pu.master.core.repositories;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pu.master.domain.models.entities.Right;


@Repository
public interface RightRepository extends JpaRepository<Right, Long>
{
    Optional<Right> findRightByName(final String name);

    boolean existsByName(final String name);
}