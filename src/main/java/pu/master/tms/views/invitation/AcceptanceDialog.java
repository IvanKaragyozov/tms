package pu.master.tms.views.invitation;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import java.time.LocalDateTime;
import pu.master.tms.models.enums.TaskPriority;
import pu.master.tms.models.enums.TaskStatus;


public class AcceptanceDialog
{

    private Dialog mainDialog;
    private Dialog taskDetailDialog;


    public AcceptanceDialog()
    {
        createMainDialog();
        createTaskDetailDialog();
    }


    private void createMainDialog()
    {
        mainDialog = new Dialog();
        mainDialog.setWidth("400px");
        mainDialog.setHeight("250px");

        // Create the message labels with new lines
        NativeLabel messageLabel = new NativeLabel("You have been invited to collaborate on the project ");

        // Create link for task name
        Anchor taskLink = new Anchor("#", "Курсова работа по ЛААГ"); // Example task name
        taskLink.getStyle().set("color", "blue");
        taskLink.getElement().addEventListener("click", event -> {
            mainDialog.close();
            taskDetailDialog.open(); // Open the detailed task dialog when the link is clicked
        });

        // Additional message label with a new line
        NativeLabel additionalMessageLabel = new NativeLabel("\nfrom user Ivan K."); // Example username

        // Layout for the message
        VerticalLayout messageLayout = new VerticalLayout(messageLabel, taskLink, additionalMessageLabel);
        messageLayout.setAlignItems(FlexComponent.Alignment.START);
        messageLayout.setSpacing(false); // No extra space between components
        messageLayout.setPadding(false); // Remove padding for a tighter look

        // Create the 'Accept' button
        Button acceptButton = new Button("Accept", event -> {
            // Accept logic
            mainDialog.close();
        });
        acceptButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS); // Green button

        // Create the 'Decline' button
        Button cancelButton = new Button("Decline", event -> {
            // Decline logic
            mainDialog.close();
        });
        cancelButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR); // Red button

        // Align buttons with text alignment
        acceptButton.getStyle().set("margin-left", "0px"); // Align left with the start of the text
        cancelButton.getStyle().set("margin-right", "0px"); // Align right with the end of the text

        // Layout for buttons
        HorizontalLayout buttonLayout = new HorizontalLayout(acceptButton, cancelButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN); // Spread buttons apart

        // Combine elements into a vertical layout
        VerticalLayout contentLayout = new VerticalLayout(messageLayout, buttonLayout);
        contentLayout.setPadding(true);
        contentLayout.setSpacing(true);
        contentLayout.setAlignItems(FlexComponent.Alignment.START); // Align items to the start

        mainDialog.add(contentLayout);
    }

    private void createTaskDetailDialog() {
        taskDetailDialog = new Dialog();
        taskDetailDialog.setWidth("600px");
        taskDetailDialog.setHeight("800px");

        // Create fields for task details
        TextField titleField = new TextField("Title");
        titleField.setWidthFull();
        titleField.setValue("Покупки за морето");
        titleField.setReadOnly(true);

        TextArea descriptionField = new TextArea("Description");
        descriptionField.setValue("Тук добавяме каквото се сетим да купим за морето.");
        descriptionField.setWidthFull();
        descriptionField.setSizeFull();
        descriptionField.getStyle().set("resize", "both");
        descriptionField.setReadOnly(true);

        ComboBox<TaskPriority> priorityComboBox = new ComboBox<>("Priority Level", TaskPriority.values());
        priorityComboBox.setValue(TaskPriority.Medium); // Example priority
        priorityComboBox.setWidthFull();
        priorityComboBox.setReadOnly(true);

        ComboBox<TaskStatus> statusComboBox = new ComboBox<>("Status", TaskStatus.values());
        statusComboBox.setWidthFull();
        statusComboBox.setValue(TaskStatus.IN_PROGRESS); // Example status
        statusComboBox.setReadOnly(true);

        DateTimePicker dueDateTimePicker = new DateTimePicker("Due Date");
        dueDateTimePicker.setValue(LocalDateTime.of(2024, 8, 16, 18, 00)); // Example due date
        dueDateTimePicker.setWidthFull();
        dueDateTimePicker.setReadOnly(true);

        // MultiSelectComboBox for collaborators
        MultiSelectComboBox<String> collaboratorsComboBox = new MultiSelectComboBox<>("Collaborators");
        collaboratorsComboBox.setWidthFull();
        collaboratorsComboBox.setItems("Ivan K.", "Alex GG", "Georgi"); // Example users
        //collaboratorsComboBox.select("User1", "User2");
        collaboratorsComboBox.setReadOnly(true);

        // Task Items List
        VerticalLayout taskItemsLayout = new VerticalLayout();
        taskItemsLayout.setWidthFull();

        taskItemsLayout.add(new NativeLabel("Subtasks"));
        Checkbox subtask1 = new Checkbox("Кола Zero 2L 3 броя", true);
        Checkbox subtask2 = new Checkbox("Солети за изпът", true);
        Checkbox subtask3 = new Checkbox("Вода 1.5L 2 стека", false);
        subtask1.setEnabled(false);
        subtask2.setEnabled(false);
        subtask3.setEnabled(false);
        taskItemsLayout.add(subtask1, subtask2, subtask3);

        // 'Back' button
        Button backButton = new Button("Back", VaadinIcon.ARROW_LEFT.create(), event -> {
            taskDetailDialog.close();
            mainDialog.open(); // Return to the acceptance dialog
        });

        // Layout for the back button
        HorizontalLayout buttonLayout = new HorizontalLayout(backButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END); // Align button to the right

        // Combine elements into a vertical layout
        VerticalLayout contentLayout = new VerticalLayout(
                        titleField,
                        descriptionField,
                        priorityComboBox,
                        statusComboBox,
                        dueDateTimePicker,
                        collaboratorsComboBox,
                        taskItemsLayout,
                        buttonLayout
        );
        contentLayout.setPadding(true);
        contentLayout.setSpacing(true);
        contentLayout.setSizeFull();
        contentLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        taskDetailDialog.add(contentLayout);
    }

    public void open() {
        mainDialog.open();
    }
}