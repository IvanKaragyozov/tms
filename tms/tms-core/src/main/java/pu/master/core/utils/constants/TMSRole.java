package pu.master.core.utils.constants;


/**
 * Enum representing each user authority.
 */
public enum TMSRole
{
    GUEST(0, "GUEST"),
    USER(1000, "USER"),
    ADMIN(9000, "ADMIN");

    private int authorityLevel;
    private String roleName;


    TMSRole(final int authorityLevel, final String roleName)
    {
        this.authorityLevel = authorityLevel;
        this.roleName = roleName;
    }


    public int getAuthorityLevel()
    {
        return authorityLevel;
    }


    public void setAuthorityLevel(final int authorityLevel)
    {
        this.authorityLevel = authorityLevel;
    }


    public String getRoleName()
    {
        return roleName;
    }


    public void setRoleName(final String roleName)
    {
        this.roleName = roleName;
    }
}
