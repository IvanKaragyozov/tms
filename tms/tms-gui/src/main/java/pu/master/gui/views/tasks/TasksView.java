package pu.master.gui.views.tasks;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import pu.master.tms.models.dtos.TaskDto;
import pu.master.tms.models.dtos.TaskItemDto;
import pu.master.tms.models.dtos.UserDto;
import pu.master.tms.models.enums.TaskPriority;
import pu.master.tms.models.enums.TaskStatus;
import pu.master.tms.services.TaskService;
import pu.master.tms.views.MainLayout;

@PageTitle("Tasks")
@Route(value = "tasks", layout = MainLayout.class)
public class TasksView extends Div {

    private final Grid<TaskDto> taskGrid = new Grid<>(TaskDto.class, false);
    private final TaskService taskService = new TaskService();

    private final TextField titleFilter = new TextField("Title");
    private final TextField priorityFilter = new TextField("Priority Level");
    private final TextField statusFilter = new TextField("Status");
    private final TextField ownerFilter = new TextField("Owner");
    private final TextField dateDueFilter = new TextField("Date Due");

    private final Div filterContainer = new Div(titleFilter, priorityFilter, statusFilter, ownerFilter, dateDueFilter);
    private final Button filterButton = new Button("Filter");
    private final Button addButton = new Button(VaadinIcon.PLUS.create());


    public TasksView() {
        setSizeFull();
        addClassName("tasks-view");

        VerticalLayout layout = new VerticalLayout(
                        createTabs(),
                        createButtonLayout(),
                        createFilterContainer(),
                        createGridWrapper()
        );
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        add(layout);

        // Initially load the "Mine" tasks
        loadTasks("Mine");

        // Initially hide filters
        filterContainer.setVisible(false);

        // Add double-click listener for grid rows
        taskGrid.addItemDoubleClickListener(event -> openTaskDialog(event.getItem()));
    }


    private Component createTabs()
    {
        Tab mineTab = new Tab("Mine");
        Tab collabTab = new Tab("Collab");
        Tab archivedTab = new Tab("Archived");

        Tabs tabs = new Tabs(mineTab, collabTab, archivedTab);
        tabs.setWidthFull();

        tabs.addSelectedChangeListener(event -> {
            String selectedTab = event.getSelectedTab().getLabel();
            loadTasks(selectedTab);
        });

        return tabs;
    }


    private Component createButtonLayout() {
        // Add button click listener to open the add task dialog
        addButton.addClickListener(e -> openAddTaskDialog());

        addButton.getStyle().set("margin-left", "15px");  // Adds space to the left of the add button
        filterButton.getStyle().set("margin-right", "15px");  // Adds space to the right of the filter button

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN); // Place buttons on opposite ends
        buttonLayout.add(addButton, filterButton);

        buttonLayout.getStyle().set("margin-top", "10px");  // Adds space from the top

        // Toggle visibility of filter container when filter button is clicked
        filterButton.addClickListener(e -> {
            boolean visible = !filterContainer.isVisible();
            filterContainer.setVisible(visible);
            filterButton.setText(visible ? "Hide" : "Filter");
        });

        return buttonLayout;
    }


    private Component createFilterContainer() {
        filterContainer.addClassName("filter-container");
        filterContainer.setVisible(false);

        // Set fixed width for the filters to avoid stretching
        titleFilter.setWidth("200px");
        priorityFilter.setWidth("200px");
        statusFilter.setWidth("200px");
        ownerFilter.setWidth("200px");
        dateDueFilter.setWidth("200px");

        // Add placeholders for the filters
        titleFilter.setPlaceholder("Filter by Title");
        titleFilter.addValueChangeListener(e -> applyFilter());

        priorityFilter.setPlaceholder("Filter by Priority Level");
        priorityFilter.addValueChangeListener(e -> applyFilter());

        statusFilter.setPlaceholder("Filter by Status");
        statusFilter.addValueChangeListener(e -> applyFilter());

        ownerFilter.setPlaceholder("Filter by Owner");
        ownerFilter.addValueChangeListener(e -> applyFilter());

        dateDueFilter.setPlaceholder("Filter by Date Due");
        dateDueFilter.addValueChangeListener(e -> applyFilter());

        // Set padding and spacing for the filter container
        filterContainer.getStyle().set("padding", "5px");  // Adds padding around the entire container
        filterContainer.getStyle().set("display", "flex");  // Use flexbox for layout
        filterContainer.getStyle().set("gap", "10px");  // Adds spacing between filters
        filterContainer.getStyle().set("flex-wrap", "wrap");  // Allows filters to wrap if they exceed the width
        filterContainer.getStyle().set("justify-content", "flex-start");  // Aligns filters to the left

        // Set the width of the container to full
        filterContainer.setWidthFull();

        return filterContainer;
    }


    private Component createGridWrapper() {
        taskGrid.addColumn(TaskDto::getTitle).setHeader("Title").setAutoWidth(true).setSortable(true);
        taskGrid.addColumn(task -> task.getPriorityLevel().name())
                .setHeader("Priority")
                .setAutoWidth(true)
                .setSortable(true);
        taskGrid.addColumn(task -> task.getStatus().getName())
                .setHeader("Status")
                .setAutoWidth(true)
                .setSortable(true);
        taskGrid.addColumn(TaskDto::getOwnerUsername)
                .setKey("Owner")
                .setHeader("Owner")
                .setAutoWidth(true)
                .setSortable(true);
        taskGrid.addColumn(TaskDto::getDateCreated).setHeader("Date Created").setAutoWidth(true).setSortable(true);
        taskGrid.addColumn(TaskDto::getDateLastModified)
                .setHeader("Date Last Modified")
                .setAutoWidth(true)
                .setSortable(true);
        taskGrid.addColumn(TaskDto::getDateDue).setHeader("Date Due").setAutoWidth(true).setSortable(true);

        taskGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        taskGrid.setSizeFull();

        Div gridWrapper = new Div(taskGrid);
        gridWrapper.setSizeFull();
        gridWrapper.getStyle().set("overflow-y", "auto");
        gridWrapper.getStyle().set("flex-grow", "1");
        gridWrapper.getStyle().set("margin-left", "10px"); // Add left margin
        gridWrapper.getStyle().set("margin-right", "10px"); // Add right margin

        return gridWrapper;
    }


    private void applyFilter() {
        String title = titleFilter.getValue().toLowerCase();
        String priority = priorityFilter.getValue().toLowerCase();
        String status = statusFilter.getValue().toLowerCase();
        String owner = ownerFilter.getValue().toLowerCase();
        String dateDue = dateDueFilter.getValue().toLowerCase();

        List<TaskDto> tasks = taskService.getAllTasks().stream()
                                         .filter(task -> task.getTitle().toLowerCase().contains(title))
                                         .filter(task -> task.getPriorityLevel()
                                                             .name()
                                                             .toLowerCase()
                                                             .contains(priority))
                                         .filter(task -> task.getStatus().name().toLowerCase().contains(status))
                                         .filter(task -> task.getOwnerUsername().toLowerCase().contains(owner))
                                         .filter(task -> task.getDateDue().toString().contains(dateDue))
                                         .collect(Collectors.toList());

        taskGrid.setItems(tasks);
    }


    private void loadTasks(String tab) {
        List<TaskDto> tasks;

        switch (tab)
        {
            case "Mine":
                tasks = taskService.getTasksCreatedByCurrentUser();
                taskGrid.getColumnByKey("Owner").setVisible(false); // Hide "Owner" column for "Mine" tab
                break;
            case "Collab":
                tasks = taskService.getCollaborativeTasksForCurrentUser();
                taskGrid.getColumnByKey("Owner").setVisible(true); // Show "Owner" column for other tabs
                break;
            case "Archived":
                tasks = taskService.getArchivedTasksForCurrentUser();
                taskGrid.getColumnByKey("Owner").setVisible(true); // Show "Owner" column for other tabs
                break;
            default:
                tasks = Collections.emptyList();
        }

        taskGrid.setItems(tasks);
    }


    private void openTaskDialog(TaskDto task) {
        Dialog dialog = new Dialog();

        // Create the fields for displaying and editing the task details
        TextField titleField = new TextField("Title");
        titleField.setWidthFull();
        titleField.setValue(task.getTitle());
        titleField.setReadOnly(true);

        TextArea descriptionField = new TextArea("Description");
        descriptionField.setValue(task.getDescription());
        descriptionField.setWidthFull();
        descriptionField.setSizeFull();
        descriptionField.getStyle().set("resize", "both");
        descriptionField.setReadOnly(true);

        ComboBox<TaskPriority> priorityComboBox = new ComboBox<>("Priority Level", TaskPriority.values());
        priorityComboBox.setValue(task.getPriorityLevel());
        priorityComboBox.setWidthFull();
        priorityComboBox.setReadOnly(true);

        ComboBox<TaskStatus> statusComboBox = new ComboBox<>("Status", TaskStatus.values());
        statusComboBox.setWidthFull();
        statusComboBox.setValue(task.getStatus());
        statusComboBox.setReadOnly(true);

        DateTimePicker dueDateTimePicker = new DateTimePicker("Due Date");
        dueDateTimePicker.setValue(task.getDateDue());
        dueDateTimePicker.setWidthFull();

        // MultiSelectComboBox for selecting and managing collaborators
        MultiSelectComboBox<UserDto> collaboratorsComboBox = new MultiSelectComboBox<>("Collaborators");
        collaboratorsComboBox.setWidthFull();
        collaboratorsComboBox.setVisible(!task.getUsers().isEmpty());
        collaboratorsComboBox.setItems(taskService.getUsers());
        collaboratorsComboBox.setItemLabelGenerator(UserDto::getUsername);
        collaboratorsComboBox.select(task.getUsers()); // Pre-select existing collaborators
        collaboratorsComboBox.setReadOnly(true);

        // Task Items List
        VerticalLayout taskItemsLayout = new VerticalLayout();
        taskItemsLayout.setWidthFull();

        if (task.getTaskItems() != null && !task.getTaskItems().isEmpty()) {
            taskItemsLayout.add(new NativeLabel("Subtasks"));

            for (TaskItemDto item : task.getTaskItems()) {
                Checkbox checkbox = new Checkbox(item.getTitle(), item.isFinished());
                checkbox.setEnabled(false);
                taskItemsLayout.add(checkbox);
            }
        }

        // New subtask input and button (only visible when editing)
        HorizontalLayout subtaskInputLayout = new HorizontalLayout();
        subtaskInputLayout.setWidthFull();
        subtaskInputLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        Button addSubtaskButton = new Button(VaadinIcon.PLUS.create());
        addSubtaskButton.getStyle().set("margin-right", "5px");
        addSubtaskButton.setVisible(false);

        TextField subtaskField = new TextField();
        subtaskField.setPlaceholder("Add subtask");
        subtaskField.setWidthFull();
        subtaskField.setVisible(false);

        addSubtaskButton.addClickListener(event -> {
            String subtaskTitle = subtaskField.getValue().trim();
            if (!subtaskTitle.isEmpty()) {
                Checkbox subtaskCheckbox = new Checkbox(subtaskTitle);
                taskItemsLayout.add(subtaskCheckbox); // Add to the end of the taskItemsLayout
                subtaskField.clear();
            }
        });

        // Add the "+" button to the left and the text field to the right
        subtaskInputLayout.add(addSubtaskButton, subtaskField);

        // Create the edit button with a pencil icon
        Button editButton = new Button(VaadinIcon.EDIT.create());
        editButton.addClickListener(e -> {
            boolean isEditing = !titleField.isReadOnly();
            if (isEditing) {
                editButton.setIcon(VaadinIcon.EDIT.create());
                titleField.setReadOnly(true);
                descriptionField.setReadOnly(true);
                priorityComboBox.setReadOnly(true);
                statusComboBox.setReadOnly(true);
                dueDateTimePicker.setReadOnly(true);
                collaboratorsComboBox.setReadOnly(true);
                taskItemsLayout.getChildren().forEach(component -> {
                    if (component instanceof Checkbox) {
                        ((Checkbox) component).setEnabled(false);
                    }
                });
                subtaskInputLayout.setVisible(false);
            } else {
                editButton.setIcon(VaadinIcon.CHECK.create());
                titleField.setReadOnly(false);
                descriptionField.setReadOnly(false);
                priorityComboBox.setReadOnly(false);
                statusComboBox.setReadOnly(false);
                dueDateTimePicker.setReadOnly(false);
                collaboratorsComboBox.setReadOnly(false);
                taskItemsLayout.getChildren().forEach(component -> {
                    if (component instanceof Checkbox) {
                        ((Checkbox) component).setEnabled(true);
                    }
                });
                subtaskInputLayout.setVisible(true);
                subtaskField.setVisible(true);
                addSubtaskButton.setVisible(true);
            }
        });

        // Create the delete button with a trash can icon
        Button deleteButton = new Button(VaadinIcon.TRASH.create(), event -> {
            List<TaskDto> tasks = taskGrid.getListDataView().getItems().collect(Collectors.toList());
            tasks.remove(task);
            taskGrid.setItems(tasks);
            dialog.close();
        });

        // Place the edit button on the left and the delete button on the right
        HorizontalLayout buttonLayout = new HorizontalLayout(editButton, deleteButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN); // Edit button on left, delete on right

        // Add subtask input layout and task items layout to the dialog layout
        VerticalLayout dialogLayout = new VerticalLayout(
                        titleField,
                        descriptionField,
                        priorityComboBox,
                        statusComboBox,
                        dueDateTimePicker,
                        collaboratorsComboBox,
                        taskItemsLayout,
                        subtaskInputLayout, // Add subtask input layout after task items
                        buttonLayout
        );

        dialogLayout.setSizeFull();
        dialog.add(dialogLayout);

        dialog.setHeight("1000px");
        dialog.setWidth("600px");
        dialog.open();
    }

    private void openAddTaskDialog() {
        Dialog dialog = new Dialog();

        // Create fields for new task input
        TextField titleField = new TextField("Title");
        titleField.setWidthFull();

        TextArea descriptionField = new TextArea("Description");
        descriptionField.setSizeFull();
        descriptionField.setWidthFull();
        descriptionField.getStyle().set("resize", "both");

        ComboBox<TaskPriority> priorityComboBox = new ComboBox<>("Priority Level", TaskPriority.values());
        priorityComboBox.setWidthFull();
        ComboBox<TaskStatus> statusComboBox = new ComboBox<>("Status", TaskStatus.values());
        statusComboBox.setWidthFull();

        DateTimePicker dueDateTimePicker = new DateTimePicker("Due Date");
        dueDateTimePicker.setWidthFull();

        // MultiSelectComboBox for selecting users
        MultiSelectComboBox<UserDto> collaboratorsComboBox = new MultiSelectComboBox<>("Collaborators");
        collaboratorsComboBox.setItems(taskService.getUsers());
        collaboratorsComboBox.setItemLabelGenerator(UserDto::getUsername);
        collaboratorsComboBox.setWidthFull();
        collaboratorsComboBox.getStyle().set("flex-grow", "1");
        collaboratorsComboBox.getElement().getStyle().set("min-width", "150px");

        // Task Items List
        VerticalLayout taskItemsLayout = new VerticalLayout();
        taskItemsLayout.setWidthFull();

        // New subtask input and button
        HorizontalLayout subtaskInputLayout = new HorizontalLayout();
        subtaskInputLayout.setWidthFull();

        TextField subtaskField = new TextField();
        subtaskField.setPlaceholder("Add subtask");
        subtaskField.setWidthFull();

        Button addSubtaskButton = new Button(VaadinIcon.PLUS.create());
        addSubtaskButton.addClickListener(event -> {
            String subtaskTitle = subtaskField.getValue().trim();
            if (!subtaskTitle.isEmpty()) {
                Checkbox subtaskCheckbox = new Checkbox(subtaskTitle);
                taskItemsLayout.addComponentAtIndex(0, subtaskCheckbox);
                subtaskField.clear();
            }
        });

        subtaskInputLayout.add(addSubtaskButton, subtaskField);

        // Add a "Save" button to create the task
        Button saveButton = new Button("Save", event -> {
            TaskDto newTask = new TaskDto(
                            titleField.getValue(),
                            descriptionField.getValue(),
                            priorityComboBox.getValue(),
                            statusComboBox.getValue(),
                            LocalDateTime.now().toString(), // Date created
                            LocalDateTime.now().toString(), // Date last modified
                            dueDateTimePicker.getValue(), // Due date from DatePicker
                            "John Doe", // Set current user as owner
                            collaboratorsComboBox.getSelectedItems().stream().toList(),
                            taskItemsLayout.getChildren()
                                           .filter(component -> component instanceof Checkbox)
                                           .map(component -> new TaskItemDto(((Checkbox) component).getLabel(), ((Checkbox) component).getValue()))
                                           .collect(Collectors.toList()) // Collect task items from layout
            );

            // Add the new task to the grid
            List<TaskDto> tasks = taskGrid.getListDataView().getItems().collect(Collectors.toList());
            tasks.add(newTask);
            taskGrid.setItems(tasks);

            dialog.close();
        });

        VerticalLayout dialogLayout = new VerticalLayout(
                        titleField,
                        descriptionField,
                        priorityComboBox,
                        statusComboBox,
                        dueDateTimePicker,
                        collaboratorsComboBox,
                        subtaskInputLayout, // Add subtask input layout
                        taskItemsLayout,    // Add task items layout
                        saveButton
        );
        dialogLayout.setSizeFull();
        dialog.add(dialogLayout);

        dialog.setHeight("1000px");
        dialog.setWidth("400px");
        dialog.open();
    }
}
