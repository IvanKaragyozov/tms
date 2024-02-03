package pu.master.tmsgui.data;


import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "application_user")
public class VadUser extends AbstractEntity
{

    private String username;
    private String name;
    @JsonIgnore
    private String hashedPassword;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<VadRole> vadRoles;
    @Lob
    @Column(length = 1000000)
    private byte[] profilePicture;


    public String getUsername()
    {
        return username;
    }


    public void setUsername(String username)
    {
        this.username = username;
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public String getHashedPassword()
    {
        return hashedPassword;
    }


    public void setHashedPassword(String hashedPassword)
    {
        this.hashedPassword = hashedPassword;
    }


    public Set<VadRole> getRoles()
    {
        return vadRoles;
    }


    public void setRoles(Set<VadRole> vadRoles)
    {
        this.vadRoles = vadRoles;
    }


    public byte[] getProfilePicture()
    {
        return profilePicture;
    }


    public void setProfilePicture(byte[] profilePicture)
    {
        this.profilePicture = profilePicture;
    }

}