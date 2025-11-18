package pu.master.core.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.vaadin.flow.spring.security.VaadinWebSecurity;

import lombok.RequiredArgsConstructor;
import pu.master.core.security.providers.LoginViewProvider;


@RequiredArgsConstructor

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends VaadinWebSecurity
{

    private final LoginViewProvider loginViewProvider;


    @Override
    protected void configure(final HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                        new AntPathRequestMatcher("/images/**"),
                                        new AntPathRequestMatcher("/icons/**"),
                                        new AntPathRequestMatcher("/css/**"),
                                        new AntPathRequestMatcher("/js/**"),
                                        new AntPathRequestMatcher("/webjars/**"),
                                        new AntPathRequestMatcher("/line-awesome/**")
                        ).permitAll()
        );

        super.configure(http);

        setLoginView(http, loginViewProvider.getLoginView());
    }


    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration)
                    throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
