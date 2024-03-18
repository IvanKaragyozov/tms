package pu.master.tmsapi.jwt;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pu.master.tmsapi.exceptions.UserNotFoundException;
import pu.master.tmsapi.models.entities.Role;
import pu.master.tmsapi.models.entities.User;
import pu.master.tmsapi.repositories.UserRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService
{

    private final Logger LOGGER = LoggerFactory.getLogger(JwtUserDetailsService.class);

    private final UserRepository userRepository;


    @Autowired
    public JwtUserDetailsService(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException
    {
        final User user = this.userRepository.findUserByUsername(username).orElseThrow(() -> {
            LOGGER.error(String.format("Could not find user with username [%s]", username));
            return new UserNotFoundException(String.format("User with username [%s] not found", username));
        });

        final List<SimpleGrantedAuthority> authorities = new ArrayList<>(4);
        final Set<Role> roles = user.getRoles();

        if (null != roles)
        {
            roles.forEach(role -> {
                final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getName());
                LOGGER.debug(String.format("Creating SimpleGrantedAuthority with name %s", role.getName()));
                authorities.add(simpleGrantedAuthority);
            });
        }

        LOGGER.debug("Creating org.springframework.security.core.userdetails.User");
        return new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        authorities);
    }
}
