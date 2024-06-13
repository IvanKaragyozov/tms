package pu.master.gui.views;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;


@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout
{

    public MainLayout()
    {
        createHeader();
        createDrawer();
    }


    private void createHeader()
    {
        H1 logo = new H1("Task Management System");
        logo.addClassName("logo");

        HorizontalLayout header = new HorizontalLayout(logo);
        header.addClassName("header");

        addToNavbar(header);
    }


    private void createDrawer()
    {
        RouterLink taskLink = new RouterLink("Tasks", TaskView.class);
        RouterLink userLink = new RouterLink("Users", UserView.class);

        addToDrawer(taskLink, userLink);
    }
}