package pu.master.gui.views.login;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;

import pu.master.domain.models.requests.LoginRequest;

public class LoginDialog extends Dialog
{

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginDialog.class);

    private final LoginRequest loginRequest = new LoginRequest();
    private final BeanValidationBinder<LoginRequest> binder = new BeanValidationBinder<>(LoginRequest.class);

    private final TextField username = new TextField("Username");
    private final PasswordField password = new PasswordField("Password");


    public LoginDialog()
    {
        setWidth("400px");
        setCloseOnEsc(true);

        username.setWidthFull();
        username.setErrorMessage("Username cannot be blank");

        password.setWidthFull();
        password.setErrorMessage("Password cannot be blank");

        binder.setBean(loginRequest);
        binder.bindInstanceFields(this);

        final Button loginButton = new Button("Login", VaadinIcon.SIGN_IN.create(), e -> handleLoginButtonClick());
        loginButton.addClickShortcut(Key.ENTER);

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

            boolean loginSuccessful = true; // Replace with actual login logic

            if (loginSuccessful)
            {
                Notification.show("Login successful");
                this.close();
            }
            else
            {
                Notification.show("Login failed, please check your credentials.");
            }
        }
        catch (final ValidationException e)
        {
            LOGGER.error("Validation exception occurred during login.", e);
            Notification.show("Please fix the errors in the form.");
        }
    }
}
