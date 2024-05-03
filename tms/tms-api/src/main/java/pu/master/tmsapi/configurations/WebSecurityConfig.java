package pu.master.tmsapi.configurations;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
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

        final CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        // Overriding default attribute to: XSRF_TOKEN
        csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName(null);

        http
            // CSRF protection
            .csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            .csrf((csrf) -> csrf.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler))
            // Authorize requests
            .authorizeHttpRequests((authorize) -> authorize.requestMatchers(AUTH_PATH).permitAll())
            // TODO: add paths for each authority
            // TODO: Implement GUEST role
            .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/**").permitAll())
            .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
            .sessionManagement((authorize) -> authorize.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // JWT filter
            .addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
            .logout((customizer) -> customizer.logoutUrl(LOGOUT_URL)
                                              .deleteCookies(JWT_COOKIE_NAME)
                                              .logoutSuccessHandler((request, response, authentication) -> response.setStatus(
                                                              HttpServletResponse.SC_NO_CONTENT)));

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
