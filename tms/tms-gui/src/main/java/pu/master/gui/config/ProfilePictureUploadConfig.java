package pu.master.gui.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration for profile picture uploads.
 * Loads values from application.yml under 'application.upload.profile-picture'
 */
@Component
@ConfigurationProperties(prefix = "application.upload.profile-picture")
public class ProfilePictureUploadConfig
{
    private long maxSize = 10 * 1024 * 1024; // 10 MB default
    private String[] allowedMimeTypes = {"image/png", "image/jpeg", "image/jpg"};

    public long getMaxSize()
    {
        return maxSize;
    }

    public void setMaxSize(final long maxSize)
    {
        this.maxSize = maxSize;
    }

    public String[] getAllowedMimeTypes()
    {
        return allowedMimeTypes;
    }

    public void setAllowedMimeTypes(final String[] allowedMimeTypes)
    {
        this.allowedMimeTypes = allowedMimeTypes;
    }

    public boolean isValidMimeType(final String mimeType)
    {
        for (final String allowed : allowedMimeTypes)
        {
            if (allowed.equals(mimeType))
            {
                return true;
            }
        }
        return false;
    }
}
