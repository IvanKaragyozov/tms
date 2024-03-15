package pu.master.tmsapi.configurations;


import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pu.master.tmsapi.models.entities.Right;
import pu.master.tmsapi.models.entities.Role;
import pu.master.tmsapi.repositories.RoleRepository;
import pu.master.tmsapi.utils.constants.RightNames;
import pu.master.tmsapi.utils.constants.RoleNames;


@Component
public class DefaultRoleInitializer
{

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRoleInitializer.class);

    private final RoleRepository roleRepository;


    @Autowired
    public DefaultRoleInitializer(final RoleRepository roleRepository)
    {
        this.roleRepository = roleRepository;
    }


    @PostConstruct
    public void createDefaultRole()
    {
        final String defaultRoleName = RoleNames.USER.name();
        if (this.roleRepository.existsByName(defaultRoleName))
        {
            return;
        }

        final Set<Right> defaultRights = Arrays.stream(RightNames.values())
                                               .map(right -> new Right(right.name()))
                                               .collect(Collectors.toSet());

        final Role defaultRole = new Role(defaultRoleName);
        defaultRole.setRights(defaultRights);

        this.roleRepository.save(defaultRole);
        LOGGER.info(String.format("Created default role with name: [%s]", defaultRoleName));
    }
}