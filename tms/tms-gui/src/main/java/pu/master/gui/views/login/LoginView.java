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
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
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

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        createBackgroundImage();

        username.setWidthFull();
        password.setWidthFull();

        binder.bindInstanceFields(this);
        binder.setBean(loginRequest);

        final Button loginButton = new Button("Login", VaadinIcon.SIGN_IN.create(), e -> handleLoginButtonClick());
        loginButton.addClickShortcut(Key.ENTER);

        final Div linkContainer = createRegisterHereLink();

        final H1 loginTitle = new H1("Task Management System");
        loginTitle.getStyle().set("text-align", "center");

        final VerticalLayout form = createLoginForm(loginTitle, loginButton, linkContainer);

        final Div card = createLoginFormStyle();
        card.add(form);
        add(card);
    }


    private VerticalLayout createLoginForm(final H1 loginTitle, final Button loginButton, final Div linkContainer)
    {
        final VerticalLayout form = new VerticalLayout(loginTitle, username, password, loginButton, linkContainer);
        form.setWidthFull();
        form.setPadding(true);
        form.setSpacing(true);
        form.setAlignItems(Alignment.STRETCH);

        return form;
    }


    private static Div createRegisterHereLink()
    {
        final Span registerInstruction = new Span("Don't have an account? Register ");
        final Anchor registerLink = new Anchor("/register", "here");
        registerLink.getStyle().set("color", "blue");
        registerLink.add(registerInstruction);

        final Div linkContainer = new Div(registerInstruction, registerLink);
        linkContainer.getStyle().set("color", "black");

        return linkContainer;
    }


    private Div createLoginFormStyle()
    {
        final Div div = new Div();
        div.getStyle()
            .set("padding", "2rem")
            .set("border-radius", "16px")
            .set("background", "rgba(255,255,255,0.85)")
            .set("box-shadow", "0 8px 24px rgba(0,0,0,0.15)")
            .set("backdrop-filter", "blur(5px)")
            .set("width", "380px");

        return div;
    }


    private void createBackgroundImage()
    {
        super.getStyle().set("background-image", "url('images/tms_login_background.png')")
             .set("background-size", "cover")
             .set("background-position", "center")
             .set("background-repeat", "no-repeat");
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
