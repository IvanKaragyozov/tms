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
 * Class used to create the USER role.
 * The {@code createDefaultRole} is invoked after all Spring beans have been created.
 */
@Component
class DefaultRoleInitializer extends BaseRoleInitializer
{

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRoleInitializer.class);


    DefaultRoleInitializer(final RoleRepository roleRepository,
                           final RightRepository rightRepository)
    {
        super(roleRepository, rightRepository);
    }


    @PostConstruct
    void createDefaultRole()
    {
        final String defaultRoleName = RoleNames.USER.name();
        final Set<Right> defaultUserRights = RoleInitializerUtils.DEFAULT_USER_RIGHTS;

        final Role userRole = super.createRoleIfNotExists(defaultRoleName, defaultUserRights);
        LOGGER.info(userRole == null
                    ? "[{}] role was already created"
                    : "Created default user role with name: [{}]", defaultRoleName);
    }
}
