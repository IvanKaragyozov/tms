package pu.master.core.configurations.roles;


import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import pu.master.core.repositories.RightRepository;
import pu.master.core.repositories.RoleRepository;
import pu.master.domain.models.entities.Right;
import pu.master.domain.models.entities.Role;


@RequiredArgsConstructor

@Transactional
abstract class BaseRoleInitializer
{

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseRoleInitializer.class);

    protected final RoleRepository roleRepository;
    protected final RightRepository rightRepository;


    protected Role createRoleIfNotExists(final String roleName, final Set<Right> rights)
    {
        if (this.roleRepository.existsByName(roleName))
        {
            return null;
        }

        final Set<Right> persistedRights = rights
                        .stream()
                        .map(right -> this.rightRepository
                                        .findRightByName(right.getName())
                                        .orElseGet(() -> {
                                            final Right newRight = new Right(right.getName());
                                            LOGGER.info("Saving right with name: [{}]", newRight.getName());
                                            return rightRepository.save(newRight);
                                        }))
                        .collect(Collectors.toSet());

        final Role roleToSave = new Role(roleName);
        roleToSave.setRights(persistedRights);

        return this.roleRepository.save(roleToSave);
    }

}
