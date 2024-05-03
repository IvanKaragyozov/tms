package pu.master.core.services;


import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pu.master.core.exceptions.UserNotFoundException;
import pu.master.core.jwt.JwtCookieUtil;
import pu.master.core.mappers.UserMapper;
import pu.master.domain.models.dtos.UserDto;
import pu.master.domain.models.entities.Role;
import pu.master.domain.models.entities.User;
import pu.master.domain.models.requests.LoginRequest;
import pu.master.domain.models.requests.UserRequest;
import pu.master.core.repositories.UserRepository;
import pu.master.core.utils.constants.RoleNames;


@Service
public class UserService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final AuthenticationManager authenticationManager;
    private final JwtCookieUtil jwtCookieUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    private final RoleService roleService;

    private final UserMapper userMapper;


    public UserService(final AuthenticationManager authenticationManager,
                       final JwtCookieUtil jwtCookieUtil, final BCryptPasswordEncoder bCryptPasswordEncoder,
                       final UserRepository userRepository,
                       final RoleService roleService,
                       final UserMapper userMapper)
    {
        this.authenticationManager = authenticationManager;
        this.jwtCookieUtil = jwtCookieUtil;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userMapper = userMapper;
    }


    public HttpCookie login(final LoginRequest loginRequest)
    {

        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword());

        final UserDetails principal =
                        (UserDetails) this.authenticationManager.authenticate(authenticationToken).getPrincipal();


        return this.jwtCookieUtil.createJWTCookie(principal);
    }

    public HttpCookie registerUser(final UserRequest userRequest)
    {
        final User user = createUser(userRequest);

        final LoginRequest loginRequest = new LoginRequest().setUsername(user.getUsername())
                                                            .setPassword(user.getPassword());
        return login(loginRequest);
    }

    public User createUser(final UserRequest userRequest)
    {



        final User user = this.userMapper.mapUserRequestToUser(userRequest);

        final Role defaultUserRole = getDefaultUserRole();
        final String encryptedUserPassword = this.bCryptPasswordEncoder.encode(userRequest.getPassword());

        user.addRole(defaultUserRole);
        user.setPassword(encryptedUserPassword);
        user.setActive(true);
        user.setDateCreatedAt(LocalDate.now());
        user.setDateLastModifiedAt(LocalDate.now());

        return this.userRepository.save(user);
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

    public Role getDefaultUserRole()
    {
        return this.roleService.getRoleByName(RoleNames.USER.name());
    }
}
