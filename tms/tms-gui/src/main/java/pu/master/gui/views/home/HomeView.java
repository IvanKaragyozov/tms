package pu.master.gui.views.home;


import jakarta.annotation.security.PermitAll;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import pu.master.gui.views.MainLayout;


@PermitAll
@Route(value = "/home", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout
{

    public HomeView()
    {
        super.setSpacing(false);
        super.setSizeFull();
        super.setJustifyContentMode(JustifyContentMode.CENTER);
        super.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        super.getStyle().set("text-align", "center");

        final Image homeImage = createHomeImage();
        super.add(homeImage);

        final Paragraph descriptionParagraph = createDescriptionParagraph();
        super.add(descriptionParagraph);

        final Paragraph supportParagraph = createSupportParagraph();
        super.add(supportParagraph);
    }


    private Image createHomeImage()
    {
        final Image homeImage = new Image("images/home_page_img.jpg", "Missing home image");
        homeImage.setHeight(60, Unit.PERCENTAGE);
        homeImage.setWidth(40, Unit.PERCENTAGE);

        return homeImage;
    }


    private Paragraph createDescriptionParagraph()
    {
        final String paragraphMsg = "Task Management System is an open source application that helps "
                                    + "and encourages people to be more productive by providing an easy way "
                                    + "to store and manage their day to day tasks.";

        final Paragraph paragraph = new Paragraph(paragraphMsg);
        paragraph.setWidth(20, Unit.PERCENTAGE);
        paragraph.getStyle().set("white-space", "pre-line");

        return paragraph;
    }


    private Paragraph createSupportParagraph()
    {
        return new Paragraph("Support: taskmanagementserviceteam@gmail.com");
    }
}
