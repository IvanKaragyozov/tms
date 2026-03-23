package pu.master.gui.views;


import java.io.ByteArrayInputStream;

import org.vaadin.lineawesome.LineAwesomeIcon;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.lumo.LumoUtility;

import pu.master.core.utils.SecurityUtils;
import pu.master.domain.models.entities.User;
import pu.master.gui.views.home.HomeView;
import pu.master.gui.views.utils.Routes;


public class MainLayout extends AppLayout implements AfterNavigationObserver
{

    private final H1 viewTitle = new H1();
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
        final User currentUser = securityUtils.getCurrentLoggedInUser();

//        final String firstLetter = currentUser.getUsername().substring(0, 1);

        final Avatar avatar = new Avatar();
        final byte[] pfpByteArray = currentUser.getProfilePicture();
        if (pfpByteArray != null && pfpByteArray.length > 0)
        {

            avatar.setImageResource(new StreamResource("profile.png", () -> new ByteArrayInputStream(pfpByteArray)));
        }
        else
        {
            avatar.setName(currentUser.getUsername());
        }
//        avatar.setName(firstLetter);
//        avatar.setAbbreviation(firstLetter);

        final ContextMenu avatarMenu = createAvatarMenu();
        avatarMenu.setTarget(avatar);

        return avatar;
    }


    private ContextMenu createAvatarMenu()
    {
        final ContextMenu avatarMenu = new ContextMenu();
        avatarMenu.setOpenOnClick(true);
        avatarMenu.addItem("Profile", e -> handleProfile());
        avatarMenu.addItem("Logout", e -> handleLogout());

        return avatarMenu;
    }


    private void handleProfile()
    {
        getUI().ifPresent(ui -> ui.navigate(Routes.PROFILE));
    }


    private void handleLogout()
    {
        securityUtils.logout();
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
    public void afterNavigation(final AfterNavigationEvent event)
    {
        viewTitle.setText(getViewHeader());
    }


    private String getViewHeader()
    {
        final ViewHeader header = getContent().getClass().getAnnotation(ViewHeader.class);
        return (header == null) ? "" : header.value();
    }
}
