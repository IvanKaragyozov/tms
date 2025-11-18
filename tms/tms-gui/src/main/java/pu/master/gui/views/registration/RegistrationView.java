package pu.master.gui.views.registration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import pu.master.domain.models.requests.RegistrationRequest;


@AnonymousAllowed
@Route(value = "/register")
@PageTitle("Register | TMS")
public class RegistrationView extends VerticalLayout
{

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationView.class);

    private final RegistrationRequest registrationRequest = new RegistrationRequest();
    private final BeanValidationBinder<RegistrationRequest> binder =
                    new BeanValidationBinder<>(RegistrationRequest.class);

    private final TextField username = new TextField("Username");
    private final PasswordField password = new PasswordField("Password");
    private final EmailField email = new EmailField("Email");
    private final TextField firstName = new TextField("First Name");
    private final TextField lastName = new TextField("Last Name");
    private final TextField phoneNumber = new TextField("Phone Number");


    public RegistrationView()
    {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        createBackgroundImage();

        username.setWidthFull();
        password.setWidthFull();
        email.setWidthFull();
        firstName.setWidthFull();
        lastName.setWidthFull();
        phoneNumber.setWidthFull();

        binder.bindInstanceFields(this);
        binder.setBean(registrationRequest);

        final Button registerButton = new Button("Register", VaadinIcon.SIGN_IN_ALT.create(), e -> handleRegisterButtonClick());
        registerButton.addClickShortcut(Key.ENTER);

        final Div linkContainer = createLoginHereLink();

        final H1 title = new H1("Create Your Account");
        title.getStyle().set("text-align", "center");

        final VerticalLayout form = createRegisterForm(title, registerButton, linkContainer);

        final Div card = createCardStyle();
        card.add(form);

        add(card);
    }


    private VerticalLayout createRegisterForm(final H1 title, final Button registerButton, final Div linkContainer)
    {
        final VerticalLayout form = new VerticalLayout(title,
                                                       username,
                                                       password,
                                                       email,
                                                       firstName,
                                                       lastName,
                                                       phoneNumber,
                                                       registerButton,
                                                       linkContainer);
        form.setWidthFull();
        form.setPadding(true);
        form.setSpacing(true);
        form.setAlignItems(Alignment.STRETCH);

        return form;
    }


    private Div createLoginHereLink()
    {
        final Span instruction = new Span("Already have an account? Login ");
        final Anchor loginLink = new Anchor("/login", "here");
        loginLink.getStyle().set("color", "blue");

        final Div container = new Div(instruction, loginLink);
        container.getStyle().set("color", "black");

        return container;
    }


    private Div createCardStyle()
    {
        final Div div = new Div();
        div.getStyle()
           .set("padding", "2rem")
           .set("border-radius", "16px")
           .set("background", "rgba(255,255,255,0.85)")
           .set("box-shadow", "0 8px 24px rgba(0,0,0,0.15)")
           .set("backdrop-filter", "blur(5px)")
           .set("width", "420px");

        return div;
    }


    private void createBackgroundImage()
    {
        getStyle().set("background-image", "url('images/tms_login_background.png')")
                  .set("background-size", "cover")
                  .set("background-position", "center")
                  .set("background-repeat", "no-repeat");
    }


    private void handleRegisterButtonClick()
    {
        try
        {
            binder.writeBean(registrationRequest);

            // TODO: Add actual registration logic
            Notification.show("Registration successful!");

            getUI().ifPresent(ui -> ui.navigate("/home"));
        }
        catch (final ValidationException ex)
        {
            LOGGER.error("Validation issue during registration", ex);
            Notification.show("Please fix the errors in the form.");
        }
    }
}
