package pu.master.gui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pu.master.gui.services.ProfilePictureValidator;

/**
 * Spring configuration for GUI components and services.
 * Registers beans for profile picture upload functionality.
 */
@Configuration
public class GuiConfiguration
{
    @Bean
    public ProfilePictureValidator profilePictureValidator(
            final ProfilePictureUploadConfig uploadConfig)
    {
        return new ProfilePictureValidator(uploadConfig);
    }
}
