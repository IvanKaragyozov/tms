package pu.master.tmsapi.views.register;


import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import pu.master.tmsapi.models.requests.UserRequest;
import pu.master.tmsapi.services.UserService;
import pu.master.tmsapi.views.about.AboutView;


@PageTitle("Register")
@Route(value = "/register")
@Uses(Icon.class)
public class RegisterView extends Composite<VerticalLayout>
{

    private final UserService userService;

    private TextField firstName;
    private TextField lastName;
    private TextField phoneNumber;
    private EmailField emailField;
    private TextField username;
    private PasswordField password;


    @Autowired
    public RegisterView(final UserService userService)
    {
        this.userService = userService;
        initLayout();
    }


    private void initLayout()
    {
        final VerticalLayout layout = createMainLayout();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        getContent().add(layout);
    }


    private VerticalLayout createMainLayout()
    {
        final VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setWidth("100%");
        verticalLayout.setMaxWidth("800px");
        verticalLayout.setHeight("min-content");

        final H3 header = new H3("Personal Information");
        header.setWidth("100%");

        final FormLayout formLayout2Col = createFormLayout();

        final HorizontalLayout layoutRow = createButtonLayout();

        verticalLayout.add(header, formLayout2Col, layoutRow);
        return verticalLayout;
    }


    private FormLayout createFormLayout()
    {
        final FormLayout formLayout = new FormLayout();

        this.firstName = createTextField("First Name");
        this.lastName = createTextField("Last Name");
        this.phoneNumber = createTextField("Phone Number");
        this.emailField = createEmailField("Email");
        this.username = createTextField("Username");
        this.password = createPasswordField("Password");

        formLayout.add(firstName, lastName, phoneNumber, emailField, username, password);
        formLayout.setWidth("100%");

        return formLayout;
    }


    private HorizontalLayout createButtonLayout()
    {
        final HorizontalLayout layoutRow = new HorizontalLayout();
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");

        final Button buttonPrimary = createPrimaryButton();
        final Button buttonSecondary = createSecondaryButton();

        layoutRow.add(buttonPrimary, buttonSecondary);
        return layoutRow;
    }


    private TextField createTextField(final String label)
    {
        final TextField textField = new TextField();
        textField.setLabel(label);
        return textField;
    }


    private EmailField createEmailField(final String label)
    {
        final EmailField emailField = new EmailField();
        emailField.setLabel(label);
        return emailField;
    }


    private PasswordField createPasswordField(final String label)
    {
        final PasswordField passwordField = new PasswordField();
        passwordField.setLabel(label);
        passwordField.setWidth("min-content");
        return passwordField;
    }


    private Button createPrimaryButton()
    {
        final Button save = new Button("Save");
        save.setWidth("min-content");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickListener(event -> handleUserRegistration());
        return save;
    }


    private Button createSecondaryButton()
    {
        final Button cancel = new Button("Cancel");
        cancel.setWidth("min-content");
        return cancel;
    }


    private void handleUserRegistration()
    {

        final String firstName = this.firstName.getValue();
        final String lastName = this.lastName.getValue();
        final String phoneNumber = this.phoneNumber.getValue();
        final String email = this.emailField.getValue();
        final String username = this.username.getValue();
        final String password = this.password.getValue();

        if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()
            || email.isEmpty() || username.isEmpty() || password.isEmpty())
        {
            Notification.show("Please fill in all fields", 3000, Notification.Position.MIDDLE);
            return;
        }

        final UserRequest userRequest = new UserRequest();
        userRequest.setFirstName(firstName);
        userRequest.setLastName(lastName);
        userRequest.setPhoneNumber(phoneNumber);
        userRequest.setEmail(email);
        userRequest.setUsername(username);
        userRequest.setPassword(password);

        // TODO add Notification for unsuccessful registration
        this.userService.createUser(userRequest);
        Notification.show("User successfully registered!", 3000, Notification.Position.TOP_CENTER);
        // TODO: Create HomeView and navigate to it
        getUI().ifPresent(ui -> ui.navigate(AboutView.class));
    }
}
