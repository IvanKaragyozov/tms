package pu.master.tmsgui.security;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pu.master.tmsgui.data.VadUser;
import pu.master.tmsgui.data.VadUserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService
{

    private final VadUserRepository vadUserRepository;


    public UserDetailsServiceImpl(VadUserRepository vadUserRepository)
    {
        this.vadUserRepository = vadUserRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException
    {
        final VadUser vadUser = vadUserRepository.findByUsername(username);
        if (vadUser == null)
        {
            throw new UsernameNotFoundException("No user present with username: " + username);
        }
        else
        {
            return new org.springframework.security.core.userdetails.User(vadUser.getUsername(), vadUser.getHashedPassword(),
                                                                          getAuthorities(vadUser));
        }
    }


    private static List<GrantedAuthority> getAuthorities(final VadUser vadUser)
    {
        return vadUser.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                      .collect(Collectors.toList());

    }

}
