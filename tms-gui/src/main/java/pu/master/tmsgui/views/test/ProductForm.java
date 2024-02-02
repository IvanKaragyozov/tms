package pu.master.tmsgui.views.test;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;


@Route("/products")
@AnonymousAllowed
public class ProductForm extends VerticalLayout
{

    public ProductForm()
    {

        add(new TextField("Name"));
        add(new TextField("Description"));

        final NumberField price = new NumberField("Price");
        price.setSuffixComponent(new Span("$"));
        price.setStep(0.01);

        add(new DatePicker("Available"));

        final ComboBox<String> category = new ComboBox<>("Category");
        category.setItems("A", "B", "C");
        add(category);

        final Button save = new Button("Save");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        final Button cancel = new Button("Cancel");
        final HorizontalLayout buttons = new HorizontalLayout(save, cancel);
        add(buttons);

    }
}
