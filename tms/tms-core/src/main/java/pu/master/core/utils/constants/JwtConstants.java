package pu.master.core.utils.constants;


public final class JwtConstants
{

    public static final long JWT_VALIDITY_DURATION = 5 * 60 * 60;

    public static final String JWT_COOKIE_NAME = "JwtCookie";


    private JwtConstants() throws IllegalAccessException
    {
        throw new IllegalAccessException("JwtConstants is not supposed to be instantiated!");
    }
}
