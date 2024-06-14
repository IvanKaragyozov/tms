package pu.master.gui.views;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;

import pu.master.core.utils.SecurityUtils;
import pu.master.domain.models.entities.User;
import pu.master.gui.views.task.TaskView;


public class MainLayout extends AppLayout
{

    private final AccessAnnotationChecker accessChecker;
    private final SecurityUtils securityUtils;

    private H2 viewTitle;


    public MainLayout(final AccessAnnotationChecker accessChecker, final SecurityUtils securityUtils)
    {
        this.accessChecker = accessChecker;
        this.securityUtils = securityUtils;
        super.setPrimarySection(Section.DRAWER);
    }


    private void addHeaderContent()
    {
        final DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }


    private void addDrawerContent()
    {
        final H1 appName = new H1("My App");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        final Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }


    private SideNav createNavigation()
    {
        final SideNav nav = new SideNav();

        if (accessChecker.hasAccess(TaskView.class))
        {
            nav.addItem(new SideNavItem("Tasks", TaskView.class));
        }

        return nav;
    }


    private Footer createFooter()
    {
        final Footer layout = new Footer();

        final User currentLoggedInUser = this.securityUtils.getCurrentLoggedInUser();
        if (currentLoggedInUser != null)
        {

            Avatar avatar = new Avatar(currentLoggedInUser.getUsername());
            avatar.setThemeName("xsmall");
            avatar.getElement().setAttribute("tabindex", "-1");

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(avatar);
            div.add(currentLoggedInUser.getUsername());
            div.add(new Icon("lumo", "dropdown"));
            div.getElement().getStyle().set("display", "flex");
            div.getElement().getStyle().set("align-items", "center");
            div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
            userName.add(div);
            userName.getSubMenu().addItem("Sign out", e -> {
                this.securityUtils.logout();
            });

            layout.add(userMenu);
        }
        else
        {
            final Anchor loginLink = new Anchor("login", "Sign in");
            layout.add(loginLink);
        }

        return layout;
    }


    protected void afterNavigation()
    {
        super.afterNavigation();
        this.viewTitle.setText(getCurrentPageTitle());
    }


    private String getCurrentPageTitle()
    {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }

}