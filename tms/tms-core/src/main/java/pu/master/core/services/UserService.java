package pu.master.core.services;


import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pu.master.core.exceptions.UserNotFoundException;
import pu.master.core.mappers.UserMapper;
import pu.master.core.repositories.UserRepository;
import pu.master.core.utils.SecurityUtils;
import pu.master.core.utils.constants.TMSRole;
import pu.master.core.validators.UserValidator;
import pu.master.domain.models.entities.Role;
import pu.master.domain.models.entities.User;
import pu.master.domain.models.requests.RegistrationRequest;
import pu.master.domain.models.uibeans.UserUIBean;


@RequiredArgsConstructor

@Service
public class UserService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final SecurityUtils securityUtils;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final UserValidator userValidator;


    public User registerUser(final RegistrationRequest registrationRequest)
    {
        this.userValidator.validateRegistrationRequest(registrationRequest);
        final User user = createUserData(registrationRequest);

        this.userRepository.save(user);
        LOGGER.info("Registered user with username: [{}]", user.getUsername());

        return user;
    }


    /**
     * Registers a {@link User} with admin authorities with hashed password and saves it into the database.
     *
     * @param registrationRequest The account information for the admin.
     * @return The newly created admin.
     */
    public User registerAdmin(final RegistrationRequest registrationRequest)
    {
        final User admin = createAdminData(registrationRequest);
        final Optional<User> potentialAdmin = this.userRepository.findUserByEmail(registrationRequest.getEmail());
        return potentialAdmin.orElseGet(() -> this.userRepository.save(admin));

    }


    private User createUserData(final RegistrationRequest registrationRequest)
    {
        final User user = this.userMapper.mapUserRequestToUser(registrationRequest);

        final Role defaultUserRole = getDefaultUserRole();
        final String encryptedUserPassword = this.securityUtils.encodePassword(registrationRequest.getPassword());

        user.addRole(defaultUserRole);
        user.setPassword(encryptedUserPassword);
        user.setActive(true);
        user.setDateCreatedAt(LocalDate.now());

        return user;
    }


    private User createAdminData(final RegistrationRequest registrationRequest)
    {
        final User admin = this.userMapper.mapUserRequestToUser(registrationRequest);

        final Role adminRole = getAdminRole();
        final String encryptedPassword = this.securityUtils.encodePassword(registrationRequest.getPassword());

        admin.addRole(adminRole);
        admin.setPassword(encryptedPassword);
        admin.setDateCreatedAt(LocalDate.now());

        return admin;
    }


    public UserUIBean getCurrentLoggedInUserUIBean()
    {
        final User currentuser = securityUtils.getCurrentLoggedInUser();
        return this.userMapper.mapUserToUIBean(currentuser);
    }


    public User getUserById(final long userId)
    {
        return this.userRepository.findById(userId).orElseThrow(() -> {

            LOGGER.error(String.format("Could not find user with id [%d]", userId));
            return new UserNotFoundException(String.format("User with id [%d] not found", userId));
        });
    }


    public User getUserByUsername(final String username)
    {
        return this.userRepository.findUserByUsername(username).orElseThrow(() -> {

            LOGGER.error(String.format("Could not find user with username [%s]", username));
            return new UserNotFoundException(String.format("User with username [%s] not found", username));
        });
    }


    public User getUserByEmail(final String email)
    {
        return this.userRepository.findUserByEmail(email).orElseThrow(() -> {

            LOGGER.error(String.format("Could not find user with email [%s]", email));
            return new UserNotFoundException(String.format("User with email [%s] not found", email));
        });
    }


    public pu.master.domain.models.uibeans.UserUIBean getUserUIBeanByUsername(final String username)
    {
        final User user = getUserByUsername(username);

        return this.userMapper.mapUserToUIBean(user);
    }


    public boolean usernameExists(final String username)
    {
        return userRepository.existsByUsername(username);
    }


    public boolean emailExists(final String email)
    {
        return userRepository.existsByEmail(email);
    }

    public boolean phoneExists(final String phoneNumber)
    {
        if (phoneNumber.isEmpty())
        {
            return false;
        }
        return userRepository.existsByPhoneNumber(phoneNumber);
    }


    private Role getDefaultUserRole()
    {
        return this.roleService.getRoleByName(TMSRole.USER.getRoleName());
    }


    private Role getAdminRole()
    {
        return this.roleService.getRoleByName(TMSRole.ADMIN.getRoleName());
    }

    public UserUIBean updateUserProfile(final UserUIBean userToUpdate)
    {
        final User existingUser = getUserByUsername(userToUpdate.getUsername());

        existingUser.setFirstName(userToUpdate.getFirstName());
        existingUser.setLastName(userToUpdate.getLastName());
        existingUser.setEmail(userToUpdate.getEmail());
        existingUser.setPhoneNumber(userToUpdate.getPhoneNumber());
        existingUser.setActive(userToUpdate.isActive());
        existingUser.setDateLastModifiedAt(LocalDate.now());

        final User saved = userRepository.save(existingUser);
        return userMapper.mapUserToUIBean(saved);
    }
}
