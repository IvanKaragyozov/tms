package pu.master.gui.views.task;


import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.ValidationException;
import pu.master.core.services.TaskService;
import pu.master.domain.models.entities.Task;
import pu.master.domain.models.enums.TaskPriority;
import pu.master.domain.models.enums.TaskStatus;


public class TaskDialog extends Dialog
{
    private final Task task;
    private final TaskService taskService;
    private final TextField titleField = new TextField("Title");
    private final TextField descriptionField = new TextField("Description");
    private final ComboBox<TaskPriority> priorityComboBox = new ComboBox<>("Priority", TaskPriority.values());
    private final ComboBox<TaskStatus> statusComboBox = new ComboBox<>("Status", TaskStatus.values());
    private final BeanValidationBinder<Task> taskBinder = new BeanValidationBinder<>(Task.class);
    private final boolean isEditMode;
    private Button saveButton;


    public TaskDialog(final Task task, final TaskService taskService)
    {
        this.task = task;
        this.taskService = taskService;
        this.isEditMode = task.getId() != 0;
        initializeContent();
    }


    private void initializeContent()
    {
        super.setHeaderTitle(isEditMode ? "Edit task" : "Create new task");
        createMainContent();
        configureFooter();
    }


    private void createMainContent()
    {
        this.taskBinder.bindInstanceFields(this);
        final FormLayout formLayout = new FormLayout(titleField, descriptionField, priorityComboBox, statusComboBox);
        formLayout.setSizeFull();
        super.add(formLayout);
    }


    private void configureFooter()
    {
        this.saveButton = new Button("Save changes");
        this.saveButton.addClickListener(l -> {
            final BinderValidationStatus<Task> validatedData = this.taskBinder.validate();
            if (validatedData.isOk())
            {
                try
                {
                    taskBinder.writeBean(task);

                    if (isEditMode)
                    {
                        this.taskService.createTask(task);
                    }
                    else
                    {
                        this.taskService.updateTask(task);
                    }
                }
                catch (final ValidationException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });

        final Button cancelButton = new Button("Cancel", l -> close());
        getFooter().add(cancelButton, this.saveButton);
    }


    public void addSaveClickListener(final ComponentEventListener<ClickEvent<Button>> listener)
    {
        this.saveButton.addClickListener(listener);
    }
}