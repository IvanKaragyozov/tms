package pu.master.core.validators;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import pu.master.core.exceptions.EmailAlreadyExistsException;
import pu.master.core.exceptions.UsernameAlreadyExistsException;
import pu.master.core.repositories.UserRepository;
import pu.master.domain.models.requests.RegistrationRequest;


/**
 * Class used for validating user requests.
 */
@RequiredArgsConstructor
@Component
public class UserValidator
{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserValidator.class);

    private final UserRepository userRepository;


    /**
     * Validates a user registration request.
     *
     * @param registrationRequest The registration request to validate.
     * @return True if the registration request is valid, otherwise false.
     * @throws UsernameAlreadyExistsException If the username already exists.
     * @throws EmailAlreadyExistsException    If the email already exists.
     */
    public boolean validateRegistrationRequest(final RegistrationRequest registrationRequest)
    {
        if (userRepository.findUserByUsername(registrationRequest.getUsername()).isPresent())
        {
            LOGGER.error("Username [{}] already exists", registrationRequest.getUsername());
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        if (userRepository.findUserByEmail(registrationRequest.getEmail()).isPresent())
        {
            LOGGER.error("Email [{}] already exists", registrationRequest.getEmail());
            throw new EmailAlreadyExistsException("Email already exists");
        }

        return true;
    }
}
