package pu.master.tmsapi.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import static pu.master.tmsapi.utils.constants.JwtConstants.JWT_COOKIE_NAME;


@Component
public class JwtRequestFilter extends OncePerRequestFilter
{

    private final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);

    private final JwtTokenUtil tokenUtil;
    private final JwtUserDetailsService userDetailsService;


    @Autowired
    public JwtRequestFilter(final JwtTokenUtil tokenUtil, final JwtUserDetailsService userDetailsService)
    {
        this.tokenUtil = tokenUtil;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain)
                    throws ServletException, IOException
    {
        final String token = getJwtToken(request.getCookies());
        String username = null;

        if (null != token && !token.isEmpty())
        {
            LOGGER.info("JWT found");
            username = this.tokenUtil.getUsernameFromToken(token);
        }

        if (null == SecurityContextHolder.getContext().getAuthentication() && null != username)
        {
            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (this.tokenUtil.validateToken(token, userDetails))
            {
                final UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails,
                                                                        null,
                                                                        userDetails.getAuthorities());

                authenticationToken.setDetails(request);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                LOGGER.info(String.format("Authentication set to user with username [%s]", username));
            }
        }
        filterChain.doFilter(request, response);
    }


    private String getJwtToken(final Cookie[] cookies)
    {
        LOGGER.debug("Retrieving a JWT token from all cookies");
        if (null != cookies)
        {
            return Arrays.stream(cookies)
                         .filter(cookie -> JWT_COOKIE_NAME.equals(cookie.getName()))
                         .findFirst()
                         .map(Cookie::getValue)
                         .orElse(null);
        }
        return null;
    }
}
