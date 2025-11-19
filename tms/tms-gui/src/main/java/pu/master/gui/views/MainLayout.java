package pu.master.gui.views;


import org.vaadin.lineawesome.LineAwesomeIcon;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;

import pu.master.core.utils.SecurityUtils;
import pu.master.gui.views.home.HomeView;
import pu.master.gui.views.login.LoginView;


public class MainLayout extends AppLayout implements AfterNavigationObserver
{

    private H1 viewTitle;
    private final SecurityUtils securityUtils;


    public MainLayout(final SecurityUtils securityUtils)
    {
        this.securityUtils = securityUtils;

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }


    private void addHeaderContent()
    {
        final DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        final Avatar avatar = createAvatar();
        final HorizontalLayout headerLayout = createHeader(toggle, avatar);

        addToNavbar(headerLayout);
    }


    private HorizontalLayout createHeader(final DrawerToggle toggle, final Avatar avatar)
    {
        final HorizontalLayout headerLayout = new HorizontalLayout(toggle, viewTitle, avatar);
        headerLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        headerLayout.expand(viewTitle);
        headerLayout.setWidthFull();
        headerLayout.setSpacing(true);
        headerLayout.getStyle().set("padding-right", "var(--lumo-space-m)");

        return headerLayout;
    }


    // TODO: Update avatar functionality
    private Avatar createAvatar()
    {
        final String firstLetter = securityUtils.getCurrentLoggedInUser().getUsername().substring(0, 1);

        final Avatar avatar = new Avatar();
        avatar.setName(firstLetter);
        avatar.setAbbreviation(firstLetter);

        final ContextMenu avatarMenu = createAvatarMenu();
        avatarMenu.setTarget(avatar);

        return avatar;
    }


    private ContextMenu createAvatarMenu()
    {
        final ContextMenu avatarMenu = new ContextMenu();
        avatarMenu.setOpenOnClick(true);
        // TODO: Add profile view
        avatarMenu.addItem("Profile", e -> Notification.show("Work in progress"));
        avatarMenu.addItem("Logout", e -> handleLogout());

        return avatarMenu;
    }


    private void handleLogout()
    {
        securityUtils.logout();
        getUI().ifPresent(ui -> ui.navigate(LoginView.class));
    }


    private void addDrawerContent()
    {
        final Span appName = new Span("TMS");
        appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);

        final Header header = new Header(appName);
        header.addClassNames(LumoUtility.Padding.Horizontal.MEDIUM, LumoUtility.Padding.Vertical.SMALL);

        final Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }


    private SideNav createNavigation()
    {
        final SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("Home", HomeView.class, LineAwesomeIcon.HOME_SOLID.create()));

        return nav;
    }


    private Footer createFooter()
    {
        return new Footer();
    }


    @Override
    public void afterNavigation(AfterNavigationEvent event)
    {
        viewTitle.setText(getCurrentPageTitle());
    }


    private String getCurrentPageTitle()
    {
        final PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
