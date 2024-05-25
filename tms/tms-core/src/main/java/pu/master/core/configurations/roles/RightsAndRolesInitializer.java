package pu.master.core.configurations.roles;


import java.util.Set;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import pu.master.core.repositories.RightRepository;
import pu.master.core.repositories.RoleRepository;
import pu.master.core.utils.constants.RoleNames;
import pu.master.domain.models.entities.Right;
import pu.master.domain.models.entities.Role;


@Transactional
@Component
@RequiredArgsConstructor
public class RightsAndRolesInitializer
{

    private static final Logger LOGGER = LoggerFactory.getLogger(RightsAndRolesInitializer.class);

    private final RightRepository rightRepository;
    private final RoleRepository roleRepository;


    @PostConstruct
    public void initializeRightsAndRoles()
    {
        createAllRights();
        createAdminRole();
        createDefaultUserRole();
    }


    private void createAllRights()
    {
        RoleInitializerUtils.ALL_RIGHT_NAMES.forEach(rightName -> {
            if (!this.rightRepository.existsByName(rightName))
            {
                final Right newRight = new Right(rightName);
                this.rightRepository.save(newRight);
                LOGGER.info("Saved right with name [{}]", rightName);
            }
        });
    }


    private void createAdminRole()
    {
        final String adminRoleName = RoleNames.ADMIN.name();
        if (!this.roleRepository.existsByName(adminRoleName))
        {
            final Set<Right> adminRights = RoleInitializerUtils.ADMIN_RIGHTS
                            .stream()
                            .map(right -> this.rightRepository.findRightByName(right.getName())
                                                              .orElseGet(() -> {
                                                                  final Right newRight = new Right(right.getName());
                                                                  LOGGER.info("Saving right with name [{}]", newRight);
                                                                  return rightRepository.save(newRight);
                                                              }))
                            .collect(Collectors.toSet());

            final Role adminRole = new Role(adminRoleName);
            adminRole.setRights(adminRights);

            this.roleRepository.save(adminRole);
            LOGGER.info("Created admin role with name: [{}]", adminRoleName);
        }
    }


    private void createDefaultUserRole()
    {
        final String defaultRoleName = RoleNames.USER.name();
        if (!this.roleRepository.existsByName(defaultRoleName))
        {
            final Set<Right> defaultUserRights = RoleInitializerUtils.DEFAULT_USER_RIGHTS
                            .stream()
                            .map(right -> this.rightRepository.findRightByName(right.getName())
                                                              .orElseGet(() -> {
                                                                  final Right newRight = new Right(right.getName());
                                                                  LOGGER.info("Saving right with name [{}]", newRight);
                                                                  return rightRepository.save(newRight);
                                                              }))
                            .collect(Collectors.toSet());

            final Role defaultUserRole = new Role(defaultRoleName);
            defaultUserRole.setRights(defaultUserRights);

            this.roleRepository.save(defaultUserRole);
            LOGGER.info("Created default role with name: [{}]", defaultRoleName);
        }
    }
}
