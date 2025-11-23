package pu.master.core.utils;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.vaadin.flow.spring.security.AuthenticationContext;

import lombok.RequiredArgsConstructor;

import pu.master.core.exceptions.UserNotFoundException;
import pu.master.core.repositories.UserRepository;
import pu.master.core.utils.constants.TMSRole;
import pu.master.domain.models.entities.Role;
import pu.master.domain.models.entities.User;


@RequiredArgsConstructor
@Component
public class SecurityUtils
{

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtils.class);

    private final AuthenticationContext authenticationContext;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * Retrieves the current logged-in {@link User} from their username.
     *
     * @return The currently logged-in user.
     */
    public User getCurrentLoggedInUser()
    {
        return authenticationContext.getAuthenticatedUser(UserDetails.class)
                                    .map(user -> this.userRepository.findUserByUsername(user.getUsername()))
                                    .orElseThrow(() -> {
                                        LOGGER.error("Could not find current logged-in user by username");
                                        return new UserNotFoundException(
                                                        "Could not find current logged-in user by username");
                                    })
                                    .orElseThrow(() -> {
                                        LOGGER.error("Could not find current authenticated user");
                                        return new UserNotFoundException("Could not find current authenticated user");
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
                          .anyMatch(roleName -> roleName.equals(TMSRole.ADMIN.getRoleName()));
    }


    /**
     * Logouts the current logged-in user
     */
    public void logout()
    {
        this.authenticationContext.logout();
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
