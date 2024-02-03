package pu.master.tmsapi.views.tasks;


import java.util.List;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import pu.master.tmsapi.models.dtos.TaskDto;
import pu.master.tmsapi.models.enums.TaskPriority;
import pu.master.tmsapi.models.enums.TaskStatus;
import pu.master.tmsapi.models.requests.TaskRequest;
import pu.master.tmsapi.services.TaskService;
import pu.master.tmsapi.views.MainLayout;


@PageTitle("Tasks")
@Route(value = "/tasks-view")
public class TasksView extends Composite<MainLayout>
{

    private final TaskService taskService;

    private final Grid<TaskDto> taskGrid;

    private final TextField titleField;
    private final TextArea descriptionField;
    private final ComboBox<TaskPriority> priorityComboBox;
    private final ComboBox<TaskStatus> statusComboBox;

    private final Button addButton;
    private final Button updateButton;
    private final Button deleteButton;

    private final Binder<TaskDto> taskBinder;


    public TasksView(final TaskService taskService)
    {
        this.taskService = taskService;

        this.taskGrid = createTaskGrid();
        this.titleField = new TextField("Title");
        this.descriptionField = new TextArea("Description");
        this.priorityComboBox = new ComboBox<>("Priority");
        this.statusComboBox = new ComboBox<>("Status");

        this.addButton = new Button("Add", event -> addTask());
        this.updateButton = new Button("Update", event -> updateTask());
        this.deleteButton = new Button("Delete", event -> deleteTask());

        this.taskBinder = new Binder<>(TaskDto.class);

        initLayout();
        bindFields();
        updateGrid();
    }


    private void initLayout()
    {
        getContent().setSizeFull();
        getContent().setPadding(true);

        HorizontalLayout formLayout = createFormLayout();
        VerticalLayout actionsLayout = createActionsLayout();

        getContent().add(formLayout, actionsLayout, taskGrid);
    }


    private HorizontalLayout createFormLayout()
    {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("100%");
        formLayout.setSpacing(true);

        FormLayout taskForm = new FormLayout();
        taskForm.setWidth("100%");
        taskForm.add(this.titleField, this.descriptionField, this.priorityComboBox, this.statusComboBox);

        formLayout.add(taskForm, createTaskDetailsLayout());
        return formLayout;
    }


    private VerticalLayout createTaskDetailsLayout()
    {
        VerticalLayout taskDetailsLayout = new VerticalLayout();
        taskDetailsLayout.setWidth("300px");
        taskDetailsLayout.setSpacing(true);

        taskDetailsLayout.add(this.addButton, this.updateButton, this.deleteButton);
        return taskDetailsLayout;
    }


    private VerticalLayout createActionsLayout()
    {
        final VerticalLayout actionsLayout = new VerticalLayout();
        actionsLayout.setWidth("100%");
        actionsLayout.setSpacing(true);

        this.priorityComboBox.setItems(TaskPriority.values());
        this.statusComboBox.setItems(TaskStatus.values());

        return actionsLayout;
    }


    private Grid<TaskDto> createTaskGrid()
    {
        final Grid<TaskDto> grid = new Grid<>();
        grid.setSizeFull();
        grid.addColumn(TaskDto::getTitle).setHeader("Title");
        grid.addColumn(TaskDto::getDescription).setHeader("Description");
        grid.addColumn(TaskDto::getPriorityLevel).setHeader("Priority");
        grid.addColumn(TaskDto::getStatus).setHeader("Status");

        grid.asSingleSelect().addValueChangeListener(event -> populateForm(event.getValue()));

        return grid;
    }


    private void bindFields()
    {
        this.taskBinder.bind(this.titleField, TaskDto::getTitle, TaskDto::setTitle);
        this.taskBinder.bind(this.descriptionField, TaskDto::getDescription, TaskDto::setDescription);
        this.taskBinder.bind(this.priorityComboBox, TaskDto::getPriorityLevel, TaskDto::setPriorityLevel);
        this.taskBinder.bind(this.statusComboBox, TaskDto::getStatus, TaskDto::setStatus);
    }


    private void updateGrid()
    {
        final List<TaskDto> allTasks = this.taskService.getAllTaskDtos();

        this.taskGrid.setItems(allTasks);
        clearForm();
    }


    private void populateForm(TaskDto taskDto)
    {
        if (taskDto != null)
        {
            this.taskBinder.setBean(taskDto);
        }
    }


    private void clearForm()
    {
        taskBinder.setBean(new TaskDto());
    }


    private void addTask()
    {
        final TaskDto newTask = taskBinder.getBean();
        final TaskRequest task = this.taskService.mapTaskDtoToRequest(newTask);
        this.taskService.createTask(task);
        updateGrid();
    }


    private void updateTask()
    {
        final TaskDto selectedTask = this.taskGrid.asSingleSelect().getValue();
        if (selectedTask != null)
        {
            final TaskDto updatedTask = taskBinder.getBean();
            selectedTask.setTitle(updatedTask.getTitle());
            selectedTask.setDescription(updatedTask.getDescription());
            selectedTask.setPriorityLevel(updatedTask.getPriorityLevel());
            selectedTask.setStatus(updatedTask.getStatus());

            this.taskService.updateTask(updatedTask);
            updateGrid();
        }
    }


    private void deleteTask()
    {
        final TaskDto selectedTask = this.taskGrid.asSingleSelect().getValue();
        if (selectedTask != null)
        {
            taskService.deleteTaskById(selectedTask.getId());
            updateGrid();
        }
    }

}
