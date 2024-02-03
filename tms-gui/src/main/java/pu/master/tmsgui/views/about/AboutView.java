package pu.master.tmsgui.views.about;


import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import pu.master.tmsgui.views.MainLayout;


@PageTitle("About")
@Route(value = "/about", layout = MainLayout.class)
@AnonymousAllowed
public class AboutView extends VerticalLayout
{

    public AboutView()
    {
        setSpacing(false);

        initContent();

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }


    private void initContent()
    {
        add(createAboutHeader());
        add(createAboutImage());
        add(createAboutParagraph());
    }


    private H2 createAboutHeader()
    {
        final H2 header = new H2();
        header.setText("Task Management System ER Diagram");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);

        return header;
    }


    private Image createAboutImage()
    {
        final Image img = new Image();
        img.setSrc("images/erd-v2.png");
        img.setAlt("placeholder ERD");
        img.setWidth("856px");
        img.setHeight("598px");

        return img;
    }


    private Paragraph createAboutParagraph()
    {
        final Paragraph paragraph = new Paragraph();
        paragraph.setText("Real about page coming soon! ( ๑‾̀◡‾́)σ\"");

        return paragraph;
    }
}
