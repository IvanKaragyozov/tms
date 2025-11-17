package pu.master.gui.views.login;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import pu.master.domain.models.requests.LoginRequest;

@AnonymousAllowed
@Route(value = "/login")
@PageTitle("Login | TMS")
public class LoginView extends VerticalLayout
{

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginView.class);

    private final LoginRequest loginRequest = new LoginRequest();
    private final BeanValidationBinder<LoginRequest> binder = new BeanValidationBinder<>(LoginRequest.class);

    private final TextField username = new TextField("Username");
    private final PasswordField password = new PasswordField("Password");

    private final AuthenticationManager authenticationManager;


    @Autowired
    public LoginView(final AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
        setWidth("400px");

        username.setWidthFull();
        username.setErrorMessage("Username cannot be blank");

        password.setWidthFull();
        password.setErrorMessage("Password cannot be blank");

        binder.setBean(loginRequest);
        binder.bindInstanceFields(this);

        final Button loginButton = new Button("Login", VaadinIcon.SIGN_IN.create(), e -> handleLoginButtonClick());
        loginButton.addClickShortcut(Key.ENTER);
        loginButton.setSizeFull();

        final VerticalLayout loginLayout = new VerticalLayout(username, password, loginButton);
        loginLayout.setWidthFull();
        loginLayout.setPadding(true);
        loginLayout.setSpacing(true);

        super.add(loginLayout);
    }


    private void handleLoginButtonClick()
    {
        try
        {
            binder.writeBean(loginRequest);

            final Authentication authentication = authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                            loginRequest.getUsername(),
                                            loginRequest.getPassword()
                            )
            );

            if (authentication.isAuthenticated())
            {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                Notification.show("Login successful");
                LOGGER.debug("User authenticated.");
            }

        }
        catch (final InternalAuthenticationServiceException e)
        {
            Notification.show("Incorrect username or password");
            LOGGER.error("User tried to login with wrong credentials.");
        }
        catch (final ValidationException e)
        {
            Notification.show("Please fix the errors in the form.");
        }
    }
}
