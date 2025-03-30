package pu.master.tms.views.registration;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pu.master.tms.models.requests.RegistrationRequest;
import pu.master.tms.views.invitation.AcceptanceDialog;
import pu.master.tms.views.login.LoginDialog;


public class RegistrationDialog extends Dialog
{

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationDialog.class);

    private final BeanValidationBinder<RegistrationRequest> binder;
    private final RegistrationRequest registrationRequest;

    private final TextField usernameField;
    private final PasswordField passwordField;
    private final EmailField emailField;
    private final TextField firstNameField;
    private final TextField lastNameField;
    private final TextField phoneNumberField;


    public RegistrationDialog()
    {
        setWidth("400px");

        registrationRequest = new RegistrationRequest();
        binder = new BeanValidationBinder<>(RegistrationRequest.class);
        binder.setBean(registrationRequest);

        usernameField = new TextField("Username");
        usernameField.setWidthFull();

        passwordField = new PasswordField("Password");
        passwordField.setWidthFull();

        emailField = new EmailField("Email");
        emailField.setWidthFull();

        firstNameField = new TextField("First Name");
        firstNameField.setWidthFull();

        lastNameField = new TextField("Last Name");
        lastNameField.setWidthFull();

        phoneNumberField = new TextField("Phone Number");
        phoneNumberField.setWidthFull();

        binder.bind(usernameField, "username");
        binder.bind(passwordField, "password");
        binder.bind(emailField, "email");
        binder.bind(firstNameField, "firstName");
        binder.bind(lastNameField, "lastName");
        binder.bind(phoneNumberField, "phoneNumber");

        final Button registerButton = new Button("Register", e -> handleRegisterButtonClick());

        Span instructionText = new Span("Already have an account? Click ");
        Anchor loginLink = new Anchor("#", "here"); // Placeholder link
        loginLink.getElement().addEventListener("click", e -> openLoginDialog());
        loginLink.getStyle().set("color", "blue"); // Make the "here" link blue
        Div linkContainer = new Div(instructionText, loginLink);
        linkContainer.getStyle().set("color", "black"); // Make the rest of the text black

        final VerticalLayout dialogLayout = new VerticalLayout(
                        usernameField,
                        passwordField,
                        emailField,
                        firstNameField,
                        lastNameField,
                        phoneNumberField,
                        registerButton,
                        linkContainer
        );
        dialogLayout.setPadding(true);
        dialogLayout.setSpacing(true);
        dialogLayout.setWidthFull();
        dialogLayout.getStyle().set("padding", "16px");

        add(dialogLayout);
    }


    private void handleRegisterButtonClick()
    {
        try
        {
            binder.writeBean(registrationRequest);
            Notification.show("Registration successful!");
            this.close();
        }
        catch (final ValidationException ex)
        {
            LOGGER.error("Validation exception occurred during registration", ex);
            Notification.show("Please fix the errors in the form.");
        }
    }

    private void openLoginDialog() {
        LoginDialog loginDialog = new LoginDialog();
        //final AcceptanceDialog acceptanceDialog = new AcceptanceDialog();
        //acceptanceDialog.open();
        this.close();
        loginDialog.open();
    }
}
