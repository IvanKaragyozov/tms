package pu.master.gui.views.login;


import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import pu.master.gui.views.utils.Routes;


//@AnonymousAllowed
//@Route(value = Routes.LOGIN)
//@PageTitle("Login | TMS")
public class ExperimentalLogin extends VerticalLayout implements BeforeEnterObserver
{

    private final LoginForm loginForm = new LoginForm();


    public ExperimentalLogin()
    {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        createBackgroundImage();

        loginForm.setAction("login");
        loginForm.setForgotPasswordButtonVisible(false);
        loginForm.getStyle().set("background", "transparent");
        loginForm.getElement().getStyle().set("background", "transparent");

        /* TARGET INTERNAL PARTS */
        loginForm.getElement().executeJs("""
                                             this.shadowRoot.querySelector('[part="vaadin-login-form-wrapper"]')
                                                 ?.style.setProperty('background', 'transparent');
                                             this.shadowRoot.querySelector('[part="form"]')
                                                 ?.style.setProperty('background', 'transparent');
                                             this.shadowRoot.querySelector('[part="card"]')
                                                 ?.style.setProperty('background', 'transparent');
                                         """);


        final H1 title = new H1("Task Management System");
        title.getStyle().set("text-align", "center");

        final Div registerLink = createRegisterHereLink();

        final VerticalLayout form = new VerticalLayout(title, loginForm, registerLink);
        form.setWidthFull();
        form.setPadding(true);
        form.setSpacing(true);
        form.setAlignItems(Alignment.STRETCH);

        final Div card = createLoginFormStyle();
        card.add(form);

        add(card);
    }


    private static Div createRegisterHereLink()
    {
        final Span registerInstruction = new Span("Don't have an account? Register ");
        final Anchor registerAnchor = new Anchor(Routes.REGISTER, "here");
        registerAnchor.getStyle().set("color", "blue");

        final Div container = new Div(registerInstruction, registerAnchor);
        container.getStyle().set("color", "black");
        return container;
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
        this.getStyle()
            .set("background-image", "url('images/tms_login_background.png')")
            .set("background-size", "cover")
            .set("background-position", "center")
            .set("background-repeat", "no-repeat");
    }


    @Override
    public void beforeEnter(final BeforeEnterEvent event)
    {
        if (event.getLocation().getQueryParameters().getParameters().containsKey("error"))
        {
            loginForm.setError(true);
        }
    }
}
