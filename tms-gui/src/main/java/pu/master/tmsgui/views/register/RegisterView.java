package pu.master.tmsgui.views.register;


import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import pu.master.tmsgui.models.requests.UserRequest;
import pu.master.tmsgui.services.UserService;
import pu.master.tmsgui.views.MainLayout;
import pu.master.tmsgui.views.about.AboutView;


@PageTitle("Register")
@Route(value = "/register", layout = MainLayout.class)
@AnonymousAllowed
@Uses(Icon.class)
public class RegisterView extends Composite<VerticalLayout>
{

    private final UserService userService;

    @Autowired
    public RegisterView(final UserService userService)
    {
        this.userService = userService;
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        EmailField emailField = new EmailField();
        TextField textField4 = new TextField();
        PasswordField passwordField = new PasswordField();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button buttonPrimary = new Button();
        buttonPrimary.addClickListener(event -> {
            // Retrieve data from the form fields
            final String firstName = textField.getValue();
            final String lastName = textField2.getValue();
            final String phoneNumber = textField3.getValue();
            final String email = emailField.getValue();
            final String username = textField4.getValue();
            final String password = passwordField.getValue();

            // Create a UserRequest object with the retrieved data
            final UserRequest userRequest = new UserRequest();
            userRequest.setFirstName(firstName);
            userRequest.setLastName(lastName);
            userRequest.setPhoneNumber(phoneNumber);
            userRequest.setEmail(email);
            userRequest.setUsername(username);
            userRequest.setPassword(password);

            // Call the UserService to create the user
            this.userService.createUser(userRequest);

            // Optionally, you can navigate to another view or perform other actions
            getUI().ifPresent(ui -> ui.navigate(AboutView.class));
        });
        final Button buttonSecondary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h3.setText("Personal Information");
        h3.setWidth("100%");
        formLayout2Col.setWidth("100%");
        textField.setLabel("First Name");
        textField2.setLabel("Last Name");
        textField3.setLabel("Phone Number");
        emailField.setLabel("Email");
        textField4.setLabel("Username");
        passwordField.setLabel("Password");
        passwordField.setWidth("min-content");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        buttonPrimary.setText("Save");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSecondary.setText("Cancel");
        buttonSecondary.setWidth("min-content");
        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(textField);
        formLayout2Col.add(textField2);
        formLayout2Col.add(textField3);
        formLayout2Col.add(emailField);
        formLayout2Col.add(textField4);
        formLayout2Col.add(passwordField);
        layoutColumn2.add(layoutRow);
        layoutRow.add(buttonPrimary);
        layoutRow.add(buttonSecondary);
    }
}
