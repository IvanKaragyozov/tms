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

    private final JwtRequestFilter jwtRequestFilter;


    @Autowired
    public WebSecurityConfig(final JwtRequestFilter jwtRequestFilter)
    {
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception
    {


        /*http
                        // CSRF protection
                        .csrf()
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .and()
                        // Authorization requests
                        .authorizeRequests()
                        .antMatchers(AUTH_PATHS)
                        .permitAll()
                        .antMatchers(HttpMethod.GET, GUEST_GET_PATTERNS)
                        .permitAll()
                        .antMatchers(USER_PATTERNS)
                        .hasAnyAuthority(DEFAULT_USER_ROLE, DEFAULT_VENDOR_ROLE, DEFAULT_ADMIN_ROLE)
                        .antMatchers(VENDOR_PATTERNS)
                        .hasAnyAuthority(DEFAULT_VENDOR_ROLE, DEFAULT_ADMIN_ROLE)
                        .antMatchers(ADMIN_PATTERNS)
                        .hasAuthority(DEFAULT_ADMIN_ROLE)
                        .anyRequest()
                        .authenticated()
                        .and()
                        // Session management
                        .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                        // JWT filter
                        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                        .logout()
                        .logoutUrl(LOGOUT_URL)
                        .deleteCookies(JWT_COOKIE_NAME)
                        .logoutSuccessHandler((request, response, authentication) -> response.setStatus(
                                        HttpServletResponse.SC_OK));*/

        // CSRF protection
        http//.csrf((authorize) -> authorize.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                        .csrf(AbstractHttpConfigurer::disable) // TODO: Enable CSRF protection
            // Authorize requests
            .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/register").permitAll())
            .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/login").permitAll())
            .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/users/1").permitAll())
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
