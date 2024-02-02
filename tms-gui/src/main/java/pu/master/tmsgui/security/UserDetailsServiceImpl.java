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

import pu.master.tmsgui.data.User;
import pu.master.tmsgui.data.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService
{

    private final UserRepository userRepository;


    public UserDetailsServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException
    {
        final User user = userRepository.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("No user present with username: " + username);
        }
        else
        {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getHashedPassword(),
                                                                          getAuthorities(user));
        }
    }


    private static List<GrantedAuthority> getAuthorities(final User user)
    {
        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                   .collect(Collectors.toList());

    }

}
