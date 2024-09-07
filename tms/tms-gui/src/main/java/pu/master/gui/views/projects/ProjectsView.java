package pu.master.gui.views.projects;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import pu.master.tms.models.dtos.ProjectDto;
import pu.master.tms.models.dtos.TaskDto;
import pu.master.tms.models.dtos.UserDto;
import pu.master.tms.models.enums.ProjectPriority;
import pu.master.tms.models.enums.ProjectStatus;
import pu.master.tms.models.enums.TaskPriority;
import pu.master.tms.models.enums.TaskStatus;
import pu.master.tms.services.ProjectService;
import pu.master.tms.services.TaskService;
import pu.master.tms.views.MainLayout;

@PageTitle("Projects")
@Route(value = "projects", layout = MainLayout.class)
public class ProjectsView extends VerticalLayout {

    private final ProjectService projectService = new ProjectService();
    private final TaskService taskService = new TaskService();
    private MultiSelectComboBox<UserDto> userComboBox;
    private Grid<TaskDto> taskGrid;
    private final Grid<ProjectDto> projectGrid = new Grid<>(ProjectDto.class, false);
    private final TextField titleFilter = new TextField("Title");
    private final TextField priorityFilter = new TextField("Priority");
    private final TextField dateDueFilter = new TextField("Due Date");
    private final TextField dateCreatedFilter = new TextField("Date Created");
    private final TextField lastModifiedFilter = new TextField("Last Modified");
    private final Div filterContainer = new Div(titleFilter, priorityFilter, dateDueFilter, dateCreatedFilter, lastModifiedFilter);
    private final Button filterButton = new Button("Filter");
    private final Button addButton = new Button(VaadinIcon.PLUS.create());

    public ProjectsView() {
        setSizeFull();
        addClassNames("projects-view");

        add(createTabs(), createButtonLayout(), createFilterContainer(), createGridWrapper());

        loadProjects("Mine");

        // Initially hide filters
        filterContainer.setVisible(false);
    }

    private Component createButtonLayout() {
        addButton.addClickListener(e -> openAddProjectDialog());

        filterButton.addClickListener(e -> {
            boolean visible = !filterContainer.isVisible();
            filterContainer.setVisible(visible);
            filterButton.setText(visible ? "Hide" : "Filter");
        });

        HorizontalLayout buttonLayout = new HorizontalLayout(addButton, filterButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(JustifyContentMode.BETWEEN);

        return buttonLayout;
    }

    private Component createFilterContainer() {
        filterContainer.addClassName("filter-container");
        filterContainer.setVisible(false);

        titleFilter.setWidth("200px");
        titleFilter.setPlaceholder("Filter by Title");
        titleFilter.addValueChangeListener(e -> applyFilter());

        priorityFilter.setWidth("200px");
        priorityFilter.setPlaceholder("Filter by Priority");
        priorityFilter.addValueChangeListener(e -> applyFilter());

        dateCreatedFilter.setWidth("200px");
        dateCreatedFilter.setPlaceholder("Filter by Date Created");
        dateCreatedFilter.addValueChangeListener(e -> applyFilter());

        lastModifiedFilter.setWidth("200px");
        lastModifiedFilter.setPlaceholder("Filter by Last Modified");
        lastModifiedFilter.addValueChangeListener(e -> applyFilter());

        dateDueFilter.setWidth("200px");
        dateDueFilter.setPlaceholder("Filter by Due Date");
        dateDueFilter.addValueChangeListener(e -> applyFilter());

        filterContainer.getStyle().set("padding", "5px");
        filterContainer.getStyle().set("display", "flex");
        filterContainer.getStyle().set("gap", "10px");
        filterContainer.getStyle().set("flex-wrap", "wrap");
        filterContainer.getStyle().set("justify-content", "flex-start");

        filterContainer.setWidthFull();

        return filterContainer;
    }

    private Component createGridWrapper() {
        projectGrid.addColumn(ProjectDto::getTitle).setHeader("Title").setAutoWidth(true).setSortable(true);
        projectGrid.addColumn(ProjectDto::getPriorityLevel).setHeader("Priority").setAutoWidth(true).setSortable(true);
        projectGrid.addColumn(project -> project.getProjectStatus().getName())
                   .setHeader("Status")
                   .setAutoWidth(true)
                   .setSortable(true);
        projectGrid.addColumn(ProjectDto::getProjectOwner).setKey("Owner").setHeader("Owner").setAutoWidth(true).setSortable(true); // Add Project Status column
        projectGrid.addColumn(ProjectDto::getDateCreated).setHeader("Date Created").setAutoWidth(true).setSortable(true);
        projectGrid.addColumn(ProjectDto::getDateLastModified).setHeader("Last Modified").setAutoWidth(true).setSortable(true);
        projectGrid.addColumn(ProjectDto::getDateDue).setHeader("Due Date").setAutoWidth(true).setSortable(true);

        projectGrid.setItems(projectService.findAll());
        projectGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        projectGrid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        projectGrid.addItemDoubleClickListener(e -> openProjectDialog(e.getItem()));

        Div gridWrapper = new Div(projectGrid);
        gridWrapper.setSizeFull();
        gridWrapper.getStyle().set("overflow-y", "auto");
        gridWrapper.getStyle().set("flex-grow", "1");

        return gridWrapper;
    }

    private void applyFilter() {
        String title = titleFilter.getValue().toLowerCase();
        String priority = priorityFilter.getValue().toLowerCase();
        String dateCreated = dateCreatedFilter.getValue().toLowerCase();
        String lastModified = lastModifiedFilter.getValue().toLowerCase();
        String dateDue = dateDueFilter.getValue().toLowerCase();

        List<ProjectDto> projects = projectService.findAll().stream()
                                                  .filter(project -> project.getTitle().toLowerCase().contains(title))
                                                  .filter(project -> project.getPriorityLevel().toString().toLowerCase().contains(priority))
                                                  .filter(project -> project.getDateCreated().toLowerCase().contains(dateCreated))
                                                  .filter(project -> project.getDateLastModified().toLowerCase().contains(lastModified))
                                                  .filter(project -> project.getDateDue().toLowerCase().contains(dateDue))
                                                  .collect(Collectors.toList());

        projectGrid.setItems(projects);
    }

    private void openAddProjectDialog() {
        Dialog dialog = new Dialog();
        dialog.setWidth("600px");

        VerticalLayout layout = new VerticalLayout();

        TextField titleField = new TextField("Title");
        titleField.setWidthFull();

        TextArea descriptionField = new TextArea("Description");
        descriptionField.setWidthFull();
        descriptionField.setSizeFull();
        descriptionField.getStyle().set("resize", "both");

        DateTimePicker dueDateField = new DateTimePicker("Due Date");
        dueDateField.setWidthFull();

        ComboBox<ProjectPriority> priorityField = new ComboBox<>("Priority");
        priorityField.setItems(ProjectPriority.values());
        priorityField.setWidthFull();

        ComboBox<ProjectStatus> statusField = new ComboBox<>("Status"); // New ComboBox for Project Status
        statusField.setItems(ProjectStatus.values());
        statusField.setWidthFull();

        Button saveButton = new Button("Save", e -> {
            ProjectDto newProject = new ProjectDto();
            newProject.setTitle(titleField.getValue());
            newProject.setDescription(descriptionField.getValue());
            newProject.setDateDue(dueDateField.getValue() != null ? dueDateField.getValue().toString() : null);
            newProject.setPriorityLevel(priorityField.getValue());
            newProject.setProjectStatus(statusField.getValue()); // Set project status

            projectService.save(newProject); // Save the new project using the ProjectService
            loadProjects();

            dialog.close();
        });

        layout.add(titleField, descriptionField, dueDateField, priorityField, statusField, saveButton);
        dialog.add(layout);
        dialog.open();
    }

    private void openProjectDialog(ProjectDto project) {
        Dialog dialog = new Dialog();
        dialog.setWidth("60%");
        dialog.setHeight("80%");

        // Create form components
        TextField titleField = new TextField("Title");
        titleField.setValue(project.getTitle());
        titleField.setWidthFull();

        TextField descriptionField = new TextField("Description");
        descriptionField.setWidthFull();
        descriptionField.setSizeFull();
        descriptionField.getStyle().set("resize", "both");
        descriptionField.setValue(project.getDescription());

        DateTimePicker dueDateField = new DateTimePicker("Due Date");
        dueDateField.setWidthFull();

        final Set<UserDto> collaborators = project.getCollaborators();
        if (!collaborators.isEmpty())
        {
            userComboBox = new MultiSelectComboBox<>("Users");
            userComboBox.setItems(collaborators);
            userComboBox.select(collaborators);

            userComboBox.setWidthFull();
        }

        // Create and configure task grid
        taskGrid = new Grid<>(TaskDto.class, false);
        taskGrid.setVisible(!project.getTasks().isEmpty());
        taskGrid.addColumn(TaskDto::getTitle).setHeader("Task Title").setAutoWidth(true);
        taskGrid.addColumn(task -> task.getOwnerUsername()).setHeader("Owner").setAutoWidth(true);
        taskGrid.addColumn(TaskDto::getPriorityLevel).setHeader("Priority Level").setAutoWidth(true);
        taskGrid.addColumn(TaskDto::getStatus).setHeader("Status").setAutoWidth(true);
        taskGrid.addColumn(TaskDto::getDateCreated).setHeader("Date Created").setAutoWidth(true);
        taskGrid.addColumn(TaskDto::getDateLastModified).setHeader("Date Last Modified").setAutoWidth(true);
        taskGrid.addColumn(TaskDto::getDateDue).setHeader("Date Due").setAutoWidth(true);

        taskGrid.setHeight("300px"); // Set a fixed height for the grid
        taskGrid.setItems(project.getTasks());
        taskGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        // Create the "New Task" button
        Button addTaskButton = new Button("New Task", e -> openNewTaskDialog(project));

        // Create the "Delete Tasks" button with a trash icon, disabled by default
        Button deleteTasksButton = new Button(VaadinIcon.TRASH.create(), e -> {
            Set<TaskDto> selectedTasks = taskGrid.getSelectedItems();
            projectService.deleteTasks(project.getId(), selectedTasks);
            loadProjects();
            dialog.close(); // Close the dialog after deleting tasks
        });
        deleteTasksButton.setEnabled(false); // Initially disabled

        // Enable the "Delete Tasks" button only when a task is selected
        taskGrid.addSelectionListener(event -> {
            deleteTasksButton.setEnabled(!event.getAllSelectedItems().isEmpty());
        });

        // Position the buttons on top of the task grid
        HorizontalLayout taskButtonLayout = new HorizontalLayout(addTaskButton, deleteTasksButton);
        taskButtonLayout.setWidthFull();
        taskButtonLayout.setJustifyContentMode(JustifyContentMode.BETWEEN); // "New Task" on left, "Delete Tasks" on right

        // Create a layout for tasks
        VerticalLayout taskLayout = new VerticalLayout(taskButtonLayout); // Buttons on top, then the grid
        if (taskGrid.isVisible())
        {
            taskLayout.add(taskGrid);
        }
        taskLayout.setWidthFull();

        // Wrap everything in a scrollable layout
        VerticalLayout dialogContent = new VerticalLayout(titleField, descriptionField, dueDateField, userComboBox, taskLayout);
        dialogContent.setPadding(false); // Remove padding for tighter layout
        dialogContent.setSpacing(false);
        dialogContent.setSizeFull();
        dialogContent.getStyle().set("overflow", "auto"); // Make the dialog content scrollable

        // Add scrollable content to the dialog
        dialog.add(dialogContent);
        dialog.open();
    }

    private void openNewTaskDialog(ProjectDto project) {
        Dialog dialog = new Dialog();
        dialog.setWidth("400px");

        VerticalLayout layout = new VerticalLayout();

        // Create components for new task creation
        TextField taskTitleField = new TextField("Task Title");
        taskTitleField.setWidthFull();

        TextArea taskDescriptionField = new TextArea("Description");
        taskDescriptionField.setWidthFull();

        ComboBox<TaskPriority> priorityComboBox = new ComboBox<>("Priority");
        priorityComboBox.setWidthFull();
        priorityComboBox.setItems(TaskPriority.values());

        ComboBox<TaskStatus> statusComboBox = new ComboBox<>("Status");
        statusComboBox.setWidthFull();
        statusComboBox.setItems(TaskStatus.values());

        // Add DatePicker for due date
        DateTimePicker dueDatePicker = new DateTimePicker("Due Date");
        dueDatePicker.setWidthFull();

        // Save button for new task creation
        Button saveButton = new Button("Save", e -> {
            TaskDto newTask = new TaskDto();
            newTask.setTitle(taskTitleField.getValue());
            newTask.setDescription(taskDescriptionField.getValue());
            newTask.setPriorityLevel(priorityComboBox.getValue());
            newTask.setStatus(statusComboBox.getValue());
            newTask.setDateDue(dueDatePicker.getValue() != null ? dueDatePicker.getValue() : null); // Set due date

            projectService.saveTask(project.getId(), newTask); // Save task using projectService
            loadProjects();

            dialog.close();
        });

        // Button to switch to "Add Existing Task" dialog
        Button addExistingTaskButton = new Button("Add existing task", e -> {
            dialog.close(); // Close current dialog
            openAddExistingTaskDialog(project); // Open the dialog for adding existing tasks
        });

        // Layout with buttons aligned to the right
        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, addExistingTaskButton);
        buttonLayout.setWidthFull(); // Make the button layout take full width
        buttonLayout.setJustifyContentMode(JustifyContentMode.BETWEEN); // Align "Save" to left, "Add existing task" to right

        // Add components to layout
        layout.add(taskTitleField, taskDescriptionField, priorityComboBox, statusComboBox, dueDatePicker, buttonLayout);

        dialog.add(layout);
        dialog.open();
    }

    private void openAddExistingTaskDialog(ProjectDto project) {
        Dialog dialog = new Dialog();
        dialog.setWidth("400px");

        VerticalLayout layout = new VerticalLayout();

        // Create components for existing task selection
        MultiSelectComboBox<TaskDto> existingTaskComboBox = new MultiSelectComboBox<>("Existing Tasks");
        existingTaskComboBox.setWidthFull();
        List<TaskDto> availableTasks = taskService.getAllTasks().stream()
                                                  .filter(task -> !project.getTasks().contains(task)) // Filter out tasks already in the project
                                                  .collect(Collectors.toList());
        existingTaskComboBox.setItems(availableTasks);
        existingTaskComboBox.setItemLabelGenerator(TaskDto::getTitle);

        // Button to add existing tasks to the project
        Button addExistingTasksButton = new Button("Add selected tasks", e -> {
            Set<TaskDto> selectedTasks = existingTaskComboBox.getSelectedItems();
            projectService.addTasks(project.getId(), selectedTasks); // Add selected tasks to the project using projectService
            loadProjects();
            dialog.close();
        });

        // "Back" button with an icon to return to "Add New Task" dialog
        Button backButton = new Button(new Icon(VaadinIcon.ARROW_LEFT), e -> {
            dialog.close(); // Close current dialog
            openNewTaskDialog(project); // Open the dialog for adding new tasks
        });

        // Layout with "Add selected tasks" button aligned to the left and "Back" button aligned to the right
        HorizontalLayout buttonLayout = new HorizontalLayout(addExistingTasksButton, backButton);
        buttonLayout.setWidthFull(); // Make the button layout take full width
        buttonLayout.setJustifyContentMode(JustifyContentMode.BETWEEN); // Align buttons on opposite ends

        // Add components to layout
        layout.add(existingTaskComboBox, buttonLayout);

        dialog.add(layout);
        dialog.open();
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
            loadProjects(selectedTab);
        });

        return tabs;
    }

    private void loadProjects()
    {
        this.projectGrid.setItems(projectService.findAll());
    }

    private void loadProjects(String tab) {
        List<ProjectDto> projects;

        switch (tab)
        {
            case "Mine":
                projects = projectService.getProjectsCreatedByCurrentUser();
                projectGrid.getColumnByKey("Owner").setVisible(false); // Hide "Owner" column for "Mine" tab
                break;
            case "Collab":
                projects = projectService.getCollaborativeProjectsForCurrentUser();
                projectGrid.getColumnByKey("Owner").setVisible(true); // Show "Owner" column for other tabs
                break;
            case "Archived":
                projects = projectService.getArchivedColaborativeForCurrentUser();
                projectGrid.getColumnByKey("Owner").setVisible(true); // Show "Owner" column for other tabs
                break;
            default:
                projects = Collections.emptyList();
        }

        projectGrid.setItems(projects);
    }

}
