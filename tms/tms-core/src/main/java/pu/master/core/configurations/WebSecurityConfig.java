package pu.master.core.configurations;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

import pu.master.core.jwt.JwtRequestFilter;
import pu.master.core.utils.constants.JwtConstants;
import pu.master.core.utils.constants.RoleNames;


@RequiredArgsConstructor

@Configuration
@EnableWebSecurity
public class WebSecurityConfig
{

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

    private static final String LOGOUT_URL = "/logout";

    private static final String[] AUTH_PATH = {
                    "/login",
                    "/register",
                    "/logout"
    };

    private static final String[] ADMIN_PATH = {
                    "/users",
                    "/users/\\d+",
                    "/users\\?username=.*",
                    "/users\\?email=.*",
                    "/roles",
                    "/rights",
                    "/tasks",
                    "/tasks/\\d+/invite\\?email=.*",
                    "/tasks/\\d+/invitation\\?email.*",
    };

    private static final String[] USER_PATH = {
                    "/users",
                    "/tasks",
                    "/tasks/\\d+/invite\\?email=.*",
                    "/tasks/\\d+/invitation\\?email.*"
    };

    private final JwtRequestFilter jwtRequestFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception
    {

        http.csrf(AbstractHttpConfigurer::disable)
            // Authorize requests
            .authorizeHttpRequests((authorize) -> {
                authorize.requestMatchers(AUTH_PATH).permitAll()
                         .anyRequest().authenticated();
            })
            // Ensure session is stateless
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Add the JWT Request Filter before the Security Filter Chain
            .addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
            // Logout handler
            .logout((logout) -> logout.logoutUrl(LOGOUT_URL)
                                      .addLogoutHandler((request, response, authentication) -> {
                                          LOGGER.debug("Processing logout for user: " +
                                                      (authentication != null ? authentication.getName()
                                                                              : "anonymous"));
                                          if (request.getCookies() != null)
                                          {
                                              for (final Cookie cookie : request.getCookies())
                                              {
                                                  LOGGER.debug("Cookie before logout: " + cookie.getName() + "=" +
                                                              cookie.getValue());
                                                  cookie.setValue("");
                                                  cookie.setPath("/");
                                                  cookie.setMaxAge(0);
                                                  response.addCookie(cookie);
                                              }
                                          }
                                      })
                                      .deleteCookies(JwtConstants.JWT_COOKIE_NAME, "XSRF-TOKEN")
                                      .logoutSuccessHandler((request, response, authentication) -> {
                                          response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                                          LOGGER.debug("Logout successful. Clearing cookies.");
                                      }));

        return http.build();
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
