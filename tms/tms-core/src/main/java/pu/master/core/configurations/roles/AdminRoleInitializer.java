package pu.master.core.configurations.roles;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import pu.master.core.repositories.RightRepository;
import pu.master.core.repositories.RoleRepository;
import pu.master.core.utils.constants.RoleNames;
import pu.master.domain.models.entities.Right;
import pu.master.domain.models.entities.Role;


/**
 * Class used to create the ADMIN role.
 * The {@code createAdminRole} is initiated after all Spring beans have been created.
 */
@RequiredArgsConstructor
@Component
class AdminRoleInitializer
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminRoleInitializer.class);

    private final RoleRepository roleRepository;
    private final RightRepository rightRepository;


    //@PostConstruct
    void createAdminRole()
    {
        final String adminRoleName = RoleNames.ADMIN.name();
        if (this.roleRepository.existsByName(adminRoleName))
        {
            return;
        }

        final Set<Right> adminRights = RoleInitializerUtils.ADMIN_RIGHTS
                        .stream()
                        .map(right -> {
                            final Optional<Right> potentialRight = this.rightRepository.findRightByName(right.getName());
                            return potentialRight.orElse(right);
                        })
                        .collect(Collectors.toSet());

        final Role adminRole = new Role(adminRoleName);
        adminRole.setRights(adminRights);

        this.roleRepository.save(adminRole);
        LOGGER.info(String.format("Created admin role with name: [%s]", adminRole.getName()));
    }
}
