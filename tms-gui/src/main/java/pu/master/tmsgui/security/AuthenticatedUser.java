package pu.master.tmsgui.security;


import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.flow.spring.security.AuthenticationContext;

import pu.master.tmsgui.data.VadUser;
import pu.master.tmsgui.data.VadUserRepository;


@Component
public class AuthenticatedUser
{

    private final VadUserRepository vadUserRepository;
    private final AuthenticationContext authenticationContext;


    public AuthenticatedUser(final AuthenticationContext authenticationContext,
                             final VadUserRepository vadUserRepository)
    {
        this.vadUserRepository = vadUserRepository;
        this.authenticationContext = authenticationContext;
    }


    @Transactional
    public Optional<VadUser> get()
    {
        return authenticationContext.getAuthenticatedUser(UserDetails.class)
                                    .map(userDetails -> vadUserRepository.findByUsername(userDetails.getUsername()));
    }


    public void logout()
    {
        this.authenticationContext.logout();
    }

}
