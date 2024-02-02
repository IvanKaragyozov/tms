package pu.master.tmsgui.security;


import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.flow.spring.security.AuthenticationContext;

import pu.master.tmsgui.data.User;
import pu.master.tmsgui.data.UserRepository;


@Component
public class AuthenticatedUser
{

    private final UserRepository userRepository;
    private final AuthenticationContext authenticationContext;


    public AuthenticatedUser(AuthenticationContext authenticationContext, UserRepository userRepository)
    {
        this.userRepository = userRepository;
        this.authenticationContext = authenticationContext;
    }


    @Transactional
    public Optional<User> get()
    {
        return authenticationContext.getAuthenticatedUser(UserDetails.class)
                                    .map(userDetails -> userRepository.findByUsername(userDetails.getUsername()));
    }


    public void logout()
    {
        authenticationContext.logout();
    }

}
