package pu.master.gui.services;

import java.util.Arrays;

import pu.master.gui.config.ProfilePictureUploadConfig;

/**
 * Validator for profile picture uploads.
 * Validates MIME type, file size, and other constraints.
 * Follows Single Responsibility Principle by handling only validation logic.
 */
public class ProfilePictureValidator
{
    private final ProfilePictureUploadConfig config;

    public ProfilePictureValidator(final ProfilePictureUploadConfig config)
    {
        this.config = config;
    }

    /**
     * Validates if the MIME type is allowed.
     *
     * @param mimeType the MIME type to validate
     * @return true if valid, false otherwise
     */
    public boolean isValidMimeType(final String mimeType)
    {
        return config.isValidMimeType(mimeType);
    }

    /**
     * Validates if the file size is within limits.
     *
     * @param fileSize the size in bytes
     * @return true if valid, false otherwise
     */
    public boolean isValidFileSize(final long fileSize)
    {
        return fileSize <= config.getMaxSize();
    }

    /**
     * Gets the maximum allowed file size in bytes.
     *
     * @return maximum file size
     */
    public long getMaxFileSize()
    {
        return config.getMaxSize();
    }

    /**
     * Gets the allowed MIME types.
     *
     * @return array of allowed MIME types
     */
    public String[] getAllowedMimeTypes()
    {
        return config.getAllowedMimeTypes();
    }

    /**
     * Gets a human-readable string of allowed file types.
     *
     * @return comma-separated list of allowed types (e.g., "PNG, JPEG")
     */
    public String getAllowedTypesAsString()
    {
        return String.join(", ", Arrays.stream(getAllowedMimeTypes())
                                       .map(this::mimeTypeToReadable)
                                       .toArray(String[]::new));
    }

    private String mimeTypeToReadable(final String mimeType)
    {
        return switch (mimeType)
        {
            case "image/png" -> "PNG";
            case "image/jpeg", "image/jpg" -> "JPEG";
            default -> mimeType.toUpperCase();
        };
    }
}
