package pu.master.gui.views.home;


import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import pu.master.tms.views.MainLayout;


@PageTitle("Home")
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
        return new Image("images/home_page_img.jpg", "person completing tasks placeholder");
    }


    private Paragraph createDescriptionParagraph()
    {
        final String paragraphMsg = "Task Management System is an open source application that helps "
                              + "and encourages people to be more productive by providing an easy way "
                              + "to store and manage their day to day tasks.";

        final Paragraph paragraph = new Paragraph(paragraphMsg);
        paragraph.setWidth(400, Unit.PIXELS);
        paragraph.getStyle().set("white-space", "pre-line");

        return paragraph;
    }


    private Paragraph createSupportParagraph()
    {
        return new Paragraph("Support: taskmanagementserviceteam@gmail.com");
    }
}
