package pu.master.core.configurations.roles;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import pu.master.core.repositories.RightRepository;
import pu.master.core.repositories.RoleRepository;
import pu.master.core.utils.constants.RoleNames;
import pu.master.domain.models.entities.Right;
import pu.master.domain.models.entities.Role;


/**
 * Class used to create the USER role.
 * The {@code createDefaultRole} is initiated after all Spring beans have been created.
 */
@RequiredArgsConstructor
@Component
@Transactional
class DefaultRoleInitializer
{

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRoleInitializer.class);

    private final RoleRepository roleRepository;
    private final RightRepository rightRepository;


    //@PostConstruct
    void createDefaultRole()
    {
        final String defaultRoleName = RoleNames.USER.name();
        if (this.roleRepository.existsByName(defaultRoleName))
        {
            return;
        }

        final Set<Right> defaultUserRights = RoleInitializerUtils.DEFAULT_USER_RIGHTS
                        .stream()
                        .map(right -> {
                            Optional<Right> optionalRight = this.rightRepository.findRightByName(right.getName());
                            return optionalRight.orElse(right);
                        })
                        .collect(Collectors.toSet());

        final Role defaultUserRole = new Role(defaultRoleName);
        defaultUserRole.setRights(defaultUserRights);

        this.roleRepository.save(defaultUserRole);
        LOGGER.info(String.format("Created default role with name: [%s]", defaultRoleName));
    }
}