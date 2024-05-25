package pu.master.core.configurations.roles;


import java.util.Optional;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import pu.master.core.repositories.RightRepository;
import pu.master.domain.models.entities.Right;


@RequiredArgsConstructor
@Component
class RightsInitializer
{

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRoleInitializer.class);

    private final RightRepository rightRepository;


    //@PostConstruct
    void createRights()
    {
        RoleInitializerUtils.ALL_RIGHT_NAMES.forEach(rightName -> {
            final Optional<Right> potentialRight = this.rightRepository.findRightByName(rightName);
            if (potentialRight.isEmpty())
            {
                final Right newRight = new Right(rightName);
                this.rightRepository.save(newRight);
                LOGGER.info("Saved right with name [{}]", rightName);
            }
        });
    }

}
