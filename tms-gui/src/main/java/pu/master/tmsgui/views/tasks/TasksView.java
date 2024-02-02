package pu.master.tmsgui.views.tasks;


import java.time.LocalDateTime;
import java.time.ZoneOffset;

import jakarta.annotation.security.RolesAllowed;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import pu.master.tmsgui.views.MainLayout;


@PageTitle("Tasks")
@Route(value = "/tasks", layout = MainLayout.class)
@RolesAllowed("USER")
@Uses(Icon.class)
public class TasksView extends Composite<VerticalLayout>
{

    public TasksView()
    {
        HorizontalLayout layoutRow = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Tabs tabs = new Tabs();
        CheckboxGroup checkboxGroup = new CheckboxGroup();
        MessageList messageList = new MessageList();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.START, tabs);
        tabs.setWidth("100%");
        setTabsSampleData(tabs);
        checkboxGroup.setLabel("Checkbox Group");
        checkboxGroup.setWidth("min-content");
        checkboxGroup.setItems("Order ID", "Product Name", "Customer", "Status");
        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        messageList.setWidth("100%");
        setMessageListSampleData(messageList);
        getContent().add(layoutRow);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(tabs);
        layoutColumn2.add(checkboxGroup);
        layoutColumn2.add(messageList);
    }


    private void setTabsSampleData(Tabs tabs)
    {
        tabs.add(new Tab("Dashboard"));
        tabs.add(new Tab("Payment"));
        tabs.add(new Tab("Shipping"));
    }


    private void setMessageListSampleData(final MessageList messageList)
    {
        MessageListItem message1 = new MessageListItem("Nature does not hurry, yet everything gets accomplished.",
                                                       LocalDateTime.now().minusDays(1).toInstant(ZoneOffset.UTC),
                                                       "Matt Mambo");
        message1.setUserColorIndex(1);
        MessageListItem message2 = new MessageListItem(
                        "Using your talent, hobby or profession in a way that makes you contribute with something " +
                        "good to this world is truly the way to go.",
                        LocalDateTime.now().minusMinutes(55).toInstant(ZoneOffset.UTC),
                        "Linsey Listy");
        message2.setUserColorIndex(2);
        messageList.setItems(message1, message2);
    }
}
