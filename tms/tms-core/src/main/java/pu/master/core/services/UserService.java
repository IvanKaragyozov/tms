package pu.master.core.services;


import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import pu.master.core.exceptions.UserNotFoundException;
import pu.master.core.jwt.JwtCookieUtil;
import pu.master.core.mappers.UserMapper;
import pu.master.core.repositories.UserRepository;
import pu.master.core.utils.SecurityUtils;
import pu.master.core.utils.constants.RoleNames;
import pu.master.domain.models.dtos.UserDto;
import pu.master.domain.models.entities.Role;
import pu.master.domain.models.entities.User;
import pu.master.domain.models.requests.LoginRequest;
import pu.master.domain.models.requests.RegistrationRequest;


@RequiredArgsConstructor
@Service
public class UserService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final AuthenticationManager authenticationManager;
    private final JwtCookieUtil jwtCookieUtil;
    private final SecurityUtils securityUtils;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;


    public HttpCookie login(final LoginRequest loginRequest)
    {

        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword());

        final UserDetails principal =
                        (UserDetails) this.authenticationManager.authenticate(authenticationToken).getPrincipal();

        return this.jwtCookieUtil.createJWTCookie(principal);
    }


    public HttpCookie registerUser(final RegistrationRequest registrationRequest)
    {
        final User user = createUser(registrationRequest);

        final LoginRequest loginRequest = new LoginRequest().setUsername(user.getUsername())
                                                            .setPassword(user.getPassword());
        return login(loginRequest);
    }


    /**
     * Registers a {@link User} with admin authorities with hashed password
     * and saves it into the database.
     *
     * @param registrationRequest The account information for the admin.
     * @return The newly created admin.
     */
    public User registerAdmin(final RegistrationRequest registrationRequest)
    {
        final User admin = createAdmin(registrationRequest);
        admin.setDateCreatedAt(LocalDate.now());
        return this.userRepository.save(admin);
    }


    private User createUser(final RegistrationRequest registrationRequest)
    {
        final User user = this.userMapper.mapUserRequestToUser(registrationRequest);

        final Role defaultUserRole = getDefaultUserRole();
        final String encryptedUserPassword = this.securityUtils.encodePassword(registrationRequest.getPassword());

        user.addRole(defaultUserRole);
        user.setPassword(encryptedUserPassword);
        user.setActive(true);
        user.setDateCreatedAt(LocalDate.now());

        return this.userRepository.save(user);
    }


    private User createAdmin(final RegistrationRequest registrationRequest)
    {
        final User admin = this.userMapper.mapUserRequestToUser(registrationRequest);

        final Role adminRole = getAdminRole();
        final String encryptedPassword = this.securityUtils.encodePassword(registrationRequest.getPassword());

        admin.addRole(adminRole);
        admin.setPassword(encryptedPassword);

        return admin;
    }


    public List<UserDto> getAllUserDtos()
    {
        final List<User> allUsers = this.userRepository.findAll();
        return allUsers.stream()
                       .map(this.userMapper::mapUserToDto)
                       .toList();
    }


    public User getUserById(final long userId)
    {
        return this.userRepository.findById(userId).orElseThrow(() -> {

            LOGGER.error(String.format("Could not find user with id [%d]", userId));
            return new UserNotFoundException(String.format("User with id [%d] not found", userId));
        });
    }


    public UserDto getUserDtoById(final long userId)
    {
        final User user = getUserById(userId);

        return this.userMapper.mapUserToDto(user);
    }


    public User getUserByUsername(final String username)
    {
        return this.userRepository.findUserByUsername(username).orElseThrow(() -> {

            LOGGER.error(String.format("Could not find user with username [%s]", username));
            return new UserNotFoundException(String.format("User with username [%s] not found", username));
        });
    }


    public UserDto getUserDtoByUsername(final String username)
    {
        final User user = getUserByUsername(username);

        return this.userMapper.mapUserToDto(user);
    }


    private Role getDefaultUserRole()
    {
        return this.roleService.getRoleByName(RoleNames.USER.name());
    }


    private Role getAdminRole()
    {
        return this.roleService.getRoleByName(RoleNames.ADMIN.name());
    }
}
