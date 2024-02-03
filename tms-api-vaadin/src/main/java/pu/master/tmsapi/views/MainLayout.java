package pu.master.tmsapi.views;


import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;


@Route(value = "")
public class MainLayout extends VerticalLayout
{
    public MainLayout()
    {
        add(addNavigation());
    }


    private HorizontalLayout addNavigation()
    {
        final HorizontalLayout navigation = new HorizontalLayout();
        navigation.add(new RouterLink("AboutView", AboutView.class));
        navigation.add(new RouterLink("TasksView", TasksView.class));
        navigation.add(new RouterLink("RegisterView", RegisterView.class));

        return navigation;
    }
}
