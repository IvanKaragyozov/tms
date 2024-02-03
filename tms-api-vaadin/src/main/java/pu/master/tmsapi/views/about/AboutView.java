package pu.master.tmsapi.views.about;


import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;


@PageTitle("About")
@Route(value = "/about")
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
        header.setText("Task Management System");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);

        return header;
    }


    private Image createAboutImage()
    {
        final Image img = new Image();
        img.setSrc("images/tms-logo.jpg");
        img.setAlt("tms logo placeholder");
        img.setWidth("1920px");
        img.setHeight("1920px");

        return img;
    }


    private Paragraph createAboutParagraph()
    {
        final Paragraph paragraph = new Paragraph();
        paragraph.setText("Real about page coming soon! ( ๑‾̀◡‾́)σ\"");

        return paragraph;
    }
}
