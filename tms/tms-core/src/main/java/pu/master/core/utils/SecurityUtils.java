package pu.master.core.utils;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import pu.master.core.exceptions.UserNotFoundException;
import pu.master.core.repositories.UserRepository;
import pu.master.core.utils.constants.RoleNames;
import pu.master.domain.models.entities.Role;
import pu.master.domain.models.entities.User;


@RequiredArgsConstructor
@Component
public class SecurityUtils
{

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtils.class);

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * Retrieves the current logged-in {@link User} from their username.
     *
     * @return Rhe currently logged-in user.
     */
    public User getCurrentLoggedInUser()
    {
        final String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        final Optional<User> potentialLoggedInUser = this.userRepository.findUserByUsername(currentUsername);
        LOGGER.debug("Retrieving current logged in user with username: [{}]", currentUsername);

        return potentialLoggedInUser.orElseThrow(() -> {
            LOGGER.error(String.format("Could not find current logged-in user with username [%s]", currentUsername));
            return new UserNotFoundException(String.format("User with username [%s] not found", currentUsername));
        });
    }

    /**
     * Checks if the current logged-in user is an admin.
     *
     * @return True if the current user is an admin, false otherwise.
     */
    public boolean isCurrentLoggedInUserAdmin()
    {
        final User currentUser = getCurrentLoggedInUser();
        return currentUser.getRoles().stream()
                          .map(Role::getName)
                          .anyMatch(roleName -> roleName.equals(RoleNames.ADMIN.name()));
    }


    /**
     * Logouts the current logged-in user
     */
    public void logout()
    {
        SecurityContextHolder.getContext().setAuthentication(null);
    }


    /**
     * Encodes a plain text password.
     *
     * @param rawPassword The plain text password.
     * @return The encoded password.
     */
    public String encodePassword(final String rawPassword)
    {
        return this.bCryptPasswordEncoder.encode(rawPassword);
    }
}
