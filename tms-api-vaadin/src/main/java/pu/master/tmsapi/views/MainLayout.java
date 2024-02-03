package pu.master.tmsapi.views;


import java.util.stream.Stream;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;


@CssImport("./styles/main-layout.css")
@Route(value = "")
public class MainLayout extends VerticalLayout
{

    public MainLayout() {
        setSpacing(false);
        setMargin(false);
        add(createHeader());
        add(createNavigation());
    }

    private Component createHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        header.add(new H1("TMS"));
        return header;
    }

    private Component createNavigation() {
        final HorizontalLayout navigation = new HorizontalLayout();
        navigation.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        navigation.addClassName("main-navigation");

        RouterLink aboutLink = new RouterLink("About", AboutView.class);
        RouterLink tasksLink = new RouterLink("Tasks", TasksView.class);
        RouterLink registerLink = new RouterLink("Register", RegisterView.class);

        Stream.of(aboutLink, tasksLink, registerLink).forEach(link -> link.addClassName("nav-link"));

        navigation.add(aboutLink, tasksLink, registerLink);

        return navigation;
    }
}
