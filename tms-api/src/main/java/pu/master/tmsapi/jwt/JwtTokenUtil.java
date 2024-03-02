package pu.master.tmsapi.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static pu.master.tmsapi.utils.constants.JwtConstants.JWT_TOKEN_VALIDITY;


@Component
public class JwtTokenUtil implements Serializable
{

    private final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${jwt.secret}")
    private String secret;


    public void setSecret(final String secret)
    {
        this.secret = secret;
    }


    public String getUsernameFromToken(final String token)
    {
        LOGGER.debug("Extracting the username from the JWT token");
        return getClaimFromToken(token, Claims::getSubject);
    }


    public Date getExpirationDateFromToken(final String token)
    {
        LOGGER.debug("Extracting the expiration date from the JWT token");
        return getClaimFromToken(token, Claims::getExpiration);
    }


    public <T> T getClaimFromToken(final String token, final Function<Claims, T> claimsResolver)
    {
        final Claims claims = getAllClaimsFromToken(token);
        LOGGER.debug("Extracting the claim from the JWT token");
        return claimsResolver.apply(claims);
    }


    private Claims getAllClaimsFromToken(final String token)
    {
        LOGGER.debug("Extracting all claims from the JWT token");
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


    private boolean isTokenExpired(final String token)
    {
        final Date expirationDate = getExpirationDateFromToken(token);
        final Date now = new Date();

        LOGGER.debug("Checking whether the JWT has expired");
        return expirationDate.before(now);
    }


    public String generateToken(final UserDetails userDetails)
    {
        final Map<String, Object> claims = new HashMap<>();
        LOGGER.info("Generating a JWT token");
        return doGenerateToken(claims, userDetails.getUsername());
    }


    private String doGenerateToken(final Map<String, Object> claims, final String subject)
    {
        final Date expirationDate = new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY);
        final Date now = new Date(System.currentTimeMillis());
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(now)
                   .setExpiration(expirationDate)
                   .signWith(SignatureAlgorithm.HS512, secret).compact();
    }


    public boolean validateToken(final String token, final UserDetails userDetails)
    {
        final String username = getUsernameFromToken(token);

        final boolean isTokenValid = (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        LOGGER.debug("Validating a JWT token");
        return isTokenValid;
    }
}