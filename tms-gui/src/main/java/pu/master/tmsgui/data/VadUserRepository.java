package pu.master.tmsgui.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface VadUserRepository extends JpaRepository<VadUser, Long>, JpaSpecificationExecutor<VadUser>
{

    VadUser findByUsername(String username);
}
