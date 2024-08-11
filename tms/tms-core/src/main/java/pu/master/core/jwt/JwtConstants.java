package pu.master.core.jwt;


public final class JwtConstants
{

    // 5 hours in milliseconds
    public static final long JWT_VALIDITY_DURATION = 5 * 60 * 60 * 1000L;

    public static final String JWT_COOKIE_NAME = "JwtCookie";


    private JwtConstants() throws IllegalAccessException
    {
        throw new IllegalAccessException("JwtConstants is not supposed to be instantiated!");
    }
}
