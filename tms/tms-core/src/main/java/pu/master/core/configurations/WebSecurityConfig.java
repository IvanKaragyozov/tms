package pu.master.core.configurations;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import pu.master.core.jwt.JwtRequestFilter;
import pu.master.core.utils.constants.JwtConstants;
import pu.master.core.utils.constants.RoleNames;


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
                    "/projects",
                    "/tasks",
                    "/comments"
    };

    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public WebSecurityConfig(final JwtRequestFilter jwtRequestFilter)
    {
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {

        final CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName("_csrf");

        http
                        .csrf((csrf) -> csrf
                                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                                        .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        )
                        .authorizeHttpRequests((authorize) -> authorize
                                        .requestMatchers(AUTH_PATH).permitAll()
                                        .requestMatchers(ADMIN_PATH).hasAuthority(RoleNames.USER.name())
                                        .anyRequest().authenticated()
                        )
                        .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
                        .addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                        .logout((logout) -> logout
                                        .logoutUrl(LOGOUT_URL)
                                        .addLogoutHandler((request, response, authentication) -> {
                                            LOGGER.info("Processing logout for user: " + (authentication != null ? authentication.getName() : "anonymous"));
                                            if (request.getCookies() != null) {
                                                for (Cookie cookie : request.getCookies()) {
                                                    LOGGER.info("Cookie before logout: " + cookie.getName() + "=" + cookie.getValue());
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
                                            LOGGER.info("Logout successful. Clearing cookies.");
                                        })
                        );

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
