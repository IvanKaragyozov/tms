package pu.master.tmsgui.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface VadUserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>
{

    User findByUsername(String username);
}
