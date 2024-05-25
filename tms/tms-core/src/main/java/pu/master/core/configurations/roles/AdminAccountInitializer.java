package pu.master.core.configurations;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pu.master.core.repositories.UserRepository;
import pu.master.core.services.UserService;
import pu.master.domain.models.requests.RegistrationRequest;


@RequiredArgsConstructor
@Component
public class AdminAccountInitializer
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminAccountInitializer.class);

    @Value("${application.admin.username}")
    private String adminUsername;

    @Value("${application.admin.password}")
    private String adminPassword;

    private final UserRepository userRepository;
    private final UserService userService;


    @EventListener(ApplicationReadyEvent.class)
    public void createAdmin()
    {
        if (this.userRepository.existsByUsername(adminUsername))
        {
            return;
        }

        final RegistrationRequest adminRequest = createAdminData();
        this.userService.registerAdmin(adminRequest);
        LOGGER.info(String.format("Created ADMIN with username [%s]", adminRequest.getUsername()));
    }


    private RegistrationRequest createAdminData()
    {
        final RegistrationRequest adminRequest = new RegistrationRequest();
        final String requiredEmail = "admin-email@email.com";

        adminRequest.setUsername(adminUsername);
        adminRequest.setPassword(adminPassword);
        // Need to set an email because of NOT NULL constraint
        adminRequest.setEmail(requiredEmail);

        return adminRequest;
    }
}
