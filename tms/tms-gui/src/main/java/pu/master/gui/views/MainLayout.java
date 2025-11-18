package pu.master.gui.views;


import org.vaadin.lineawesome.LineAwesomeIcon;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;

import pu.master.gui.views.home.HomeView;


/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout implements AfterNavigationObserver
{

    private H1 viewTitle;
    private HorizontalLayout headerLayout;
    private Avatar avatar;


    public MainLayout()
    {
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

        this.avatar = createAvatar();

        headerLayout = new HorizontalLayout(toggle, viewTitle, avatar);
        headerLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        headerLayout.expand(viewTitle);
        headerLayout.setWidthFull();
        headerLayout.setSpacing(true);
        headerLayout.getStyle().set("padding-right", "var(--lumo-space-m)");

        addToNavbar(headerLayout);
    }


    // TODO: This is just a placeholder for "Profile" and "Logout". Add real avatars.
    private Avatar createAvatar()
    {
        final Avatar avatar = new Avatar();
        avatar.setName("?");
        avatar.setAbbreviation("?");

        return avatar;
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
