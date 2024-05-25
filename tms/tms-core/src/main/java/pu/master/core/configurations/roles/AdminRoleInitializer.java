package pu.master.core.configurations.roles;


import java.util.Set;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import pu.master.core.repositories.RightRepository;
import pu.master.core.repositories.RoleRepository;
import pu.master.core.utils.constants.RoleNames;
import pu.master.domain.models.entities.Right;
import pu.master.domain.models.entities.Role;


/**
 * Class used to create the ADMIN role.
 * The {@code createAdminRole} is initiated after all Spring beans have been created.
 */
@Component
class AdminRoleInitializer extends BaseRoleInitializer
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminRoleInitializer.class);


    AdminRoleInitializer(final RoleRepository roleRepository,
                         final RightRepository rightRepository)
    {
        super(roleRepository, rightRepository);
    }


    @PostConstruct
    void createAdminRole()
    {
        final String adminRoleName = RoleNames.ADMIN.name();
        final Set<Right> adminRights = RoleInitializerUtils.ADMIN_RIGHTS;

        final Role adminRole = super.createRoleIfNotExists(adminRoleName, adminRights);
        LOGGER.info(adminRole == null
                    ? "[{}] role was already created"
                    : "Created admin role with name: [{}]", adminRoleName);
    }
}
