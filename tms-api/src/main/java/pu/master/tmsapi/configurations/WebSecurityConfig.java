package pu.master.tmsapi.configurations;


import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import pu.master.tmsapi.jwt.JwtRequestFilter;

import static pu.master.tmsapi.utils.constants.JwtConstants.JWT_COOKIE_NAME;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig
{

    private static final String LOGOUT_URL = "/logout";

    private static final String[] AUTH_PATH = {
                    "/login",
                    "/registration",
                    "/logout"
    };

    private final JwtRequestFilter jwtRequestFilter;


    @Autowired
    public WebSecurityConfig(final JwtRequestFilter jwtRequestFilter)
    {
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception
    {


        /*private static final String[] GUEST_GET_LIST = {
           "/categories.*",
           "/cinemas.*",
           "/cinemas/\\d/halls",
           "/cinemas/\\d/reviews",
           "/items.*",
           "/movies.*",
           "/categories/\\d/movies",
           "/programs.*",
           "/cinemas/\\d/programs",
           "/programs/\\d/projections",
           "/movies/\\d/projections",
           "/movies/\\d/reviews",
           "/projections(\\?.*|\\z)"
         };

         private static final String[] USER_LIST = {
           "/reviews/\\d.*",
           "/cinemas/\\d/reviews",
           "/movies/\\d/reviews",
           "/users/\\d/orders",
           "/users\\?username=.*",
           "/users\\?email=.*",
           "/users/\\d.*"
         };*/

        // CSRF protection
        http//.csrf((authorize) -> authorize.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            .csrf(AbstractHttpConfigurer::disable) // TODO: Enable CSRF protection
            //.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/*").permitAll())
            // Authorize requests
            .authorizeHttpRequests((authorize) -> authorize.requestMatchers(AUTH_PATH).permitAll())
            .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/**").permitAll())
            //.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/register").permitAll())
            //.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/login").permitAll())
            //.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/users/\\d").permitAll())
            .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
            .sessionManagement((authorize) -> authorize.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // JWT filter
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
            .logout((customizer) -> customizer.logoutUrl(LOGOUT_URL)
                                              .deleteCookies(JWT_COOKIE_NAME)
                                              .logoutSuccessHandler((request, response, authentication) -> response.setStatus(
                                                              HttpServletResponse.SC_OK)));

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
