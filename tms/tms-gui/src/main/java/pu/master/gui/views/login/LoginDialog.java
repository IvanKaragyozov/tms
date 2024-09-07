package pu.master.gui.views.login;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pu.master.tms.models.requests.LoginRequest;


public class LoginDialog extends Dialog {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginDialog.class);

    private final BeanValidationBinder<LoginRequest> binder;
    private final LoginRequest loginRequest;

    private final TextField usernameField;
    private final PasswordField passwordField;

    public LoginDialog() {
        setWidth("400px");

        // Create a new instance of LoginRequest
        loginRequest = new LoginRequest();

        // Create a binder for LoginRequest and bind fields
        binder = new BeanValidationBinder<>(LoginRequest.class);
        binder.setBean(loginRequest);

        usernameField = new TextField("Username");
        usernameField.setWidthFull();
        usernameField.setRequired(true);
        usernameField.setErrorMessage("Username cannot be blank");

        passwordField = new PasswordField("Password");
        passwordField.setWidthFull();
        passwordField.setRequired(true);
        passwordField.setErrorMessage("Password cannot be blank");

        // Debug: Log initial states
        LOGGER.info("Username Field - ReadOnly: {}, Disabled: {}", usernameField.isReadOnly(), usernameField.isEnabled());
        LOGGER.info("Password Field - ReadOnly: {}, Disabled: {}", passwordField.isReadOnly(), passwordField.isEnabled());

        // Bind fields to LoginRequest
        binder.bind(usernameField, "username");
        binder.bind(passwordField, "password");

        // Ensure fields are not read-only
        usernameField.setReadOnly(false);
        passwordField.setReadOnly(false);

        Button loginButton = new Button("Login", VaadinIcon.SIGN_IN.create(), e -> handleLoginButtonClick());

        VerticalLayout loginLayout = new VerticalLayout(usernameField, passwordField, loginButton);
        loginLayout.setPadding(true);
        loginLayout.setSpacing(true);
        loginLayout.setWidthFull();
        loginLayout.getStyle().set("padding", "16px"); // Add padding to the layout

        add(loginLayout);
    }

    private void handleLoginButtonClick() {
        try {
            binder.writeBean(loginRequest);

            boolean loginSuccessful = true; // Replace with actual login logic

            if (loginSuccessful) {
                Notification.show("Login successful");
                this.close();
            } else {
                Notification.show("Login failed, please check your credentials");
            }
        } catch (ValidationException e) {
            LOGGER.error("Validation exception occurred during login", e);
            Notification.show("Please fix the errors in the form.");
        }
    }
}