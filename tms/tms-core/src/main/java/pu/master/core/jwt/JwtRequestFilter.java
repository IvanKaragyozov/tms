package pu.master.core.jwt;


import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import static pu.master.core.jwt.JwtConstants.JWT_COOKIE_NAME;


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
                                    final FilterChain filterChain) throws ServletException, IOException
    {
        final String token = getJwtToken(request.getCookies());
        String username = null;
        boolean tokenExpired = false;

        if (token != null && !token.isEmpty())
        {
            LOGGER.debug("JWT found");
            try
            {
                username = this.tokenUtil.getUsernameFromToken(token);
            }
            catch (final ExpiredJwtException e)
            {
                LOGGER.debug("JWT token is expired");
                tokenExpired = true;
                username = e.getClaims().getSubject();
                clearJwtCookie(response);
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (tokenExpired)
            {
                LOGGER.debug("Generating a new token for user: {}", username);
                final String newToken = this.tokenUtil.generateToken(userDetails);
                final Cookie newCookie = new Cookie(JWT_COOKIE_NAME, newToken);
                newCookie.setHttpOnly(true);
                newCookie.setPath("/");
                newCookie.setMaxAge((int) JwtConstants.JWT_VALIDITY_DURATION / 1000);
                response.addCookie(newCookie);
                LOGGER.debug("New JWT token generated and set in response cookie");

                username = this.tokenUtil.getUsernameFromToken(newToken);
            }

            if (this.tokenUtil.validateToken(token, userDetails))
            {
                final UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());

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
        if (cookies != null)
        {
            return Arrays.stream(cookies)
                         .filter(cookie -> JWT_COOKIE_NAME.equals(cookie.getName()))
                         .findFirst()
                         .map(Cookie::getValue)
                         .orElse(null);
        }
        return null;
    }


    private void clearJwtCookie(HttpServletResponse response)
    {
        LOGGER.debug("Clearing JWT cookie");
        Cookie cookie = new Cookie(JWT_COOKIE_NAME, "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}