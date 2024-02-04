package pu.master.tmsapi.views.projects;


import java.util.List;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import pu.master.tmsapi.models.dtos.ProjectDto;
import pu.master.tmsapi.models.dtos.TaskDto;
import pu.master.tmsapi.models.enums.ProjectPriority;
import pu.master.tmsapi.models.requests.ProjectRequest;
import pu.master.tmsapi.services.ProjectService;
import pu.master.tmsapi.services.TaskService;
import pu.master.tmsapi.views.MainLayout;


@PageTitle("Projects")
@Route(value = "/projects-view")
public class ProjectView extends Composite<MainLayout>
{

    private final ProjectService projectService;
    private final TaskService taskService;

    private final Grid<ProjectDto> projectGrid;

    private final TextField titleField;
    private final TextArea descriptionField;
    private final DatePicker dueDatePicker;
    private final ComboBox<ProjectPriority> priorityComboBox;
    private final ComboBox<TaskDto> taskComboBox;

    private final Button addButton;
    private final Button updateButton;
    private final Button deleteButton;
    private final Button searchButton;

    private final Binder<ProjectDto> projectBinder;


    public ProjectView(ProjectService projectService, final TaskService taskService)
    {
        this.projectService = projectService;
        this.taskService = taskService;

        this.taskComboBox = new ComboBox<>("Select Task");
        this.taskComboBox.setItemLabelGenerator(TaskDto::getTitle);
        this.taskComboBox.setItems(this.taskService.getAllTaskDtos());

        this.projectGrid = createProjectGrid();
        this.titleField = new TextField("Title");
        this.descriptionField = new TextArea("Description");
        this.dueDatePicker = new DatePicker("Due Date");
        this.priorityComboBox = new ComboBox<>("Priority");

        this.addButton = new Button("Add", event -> addProject());
        this.updateButton = new Button("Update", event -> updateProject());
        this.deleteButton = new Button("Delete", event -> deleteProject());
        this.searchButton = new Button("Search", event -> searchProjects());

        this.projectBinder = new Binder<>(ProjectDto.class);

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

        getContent().add(formLayout, actionsLayout, projectGrid);
    }


    private HorizontalLayout createFormLayout()
    {
        final HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("100%");
        formLayout.setSpacing(true);

        final FormLayout projectForm = new FormLayout();
        projectForm.setWidth("100%");
        projectForm.add(this.titleField,
                        this.descriptionField,
                        this.dueDatePicker,
                        this.priorityComboBox,
                        this.taskComboBox);

        formLayout.add(projectForm, createProjectDetailsLayout());
        return formLayout;
    }


    private VerticalLayout createProjectDetailsLayout()
    {
        final VerticalLayout projectDetailsLayout = new VerticalLayout();
        projectDetailsLayout.setWidth("300px");
        projectDetailsLayout.setSpacing(true);

        projectDetailsLayout.add(addButton, updateButton, deleteButton);
        return projectDetailsLayout;
    }


    private VerticalLayout createActionsLayout()
    {
        final VerticalLayout actionsLayout = new VerticalLayout();
        actionsLayout.setWidth("100%");
        actionsLayout.setSpacing(true);

        this.priorityComboBox.setItems(ProjectPriority.values());

        return actionsLayout;
    }


    private Grid<ProjectDto> createProjectGrid()
    {
        final Grid<ProjectDto> grid = new Grid<>();
        grid.setSizeFull();
        grid.addColumn(ProjectDto::getTitle).setHeader("Title");
        grid.addColumn(ProjectDto::getDescription).setHeader("Description");
        grid.addColumn(ProjectDto::getDueDate).setHeader("Due Date");
        grid.addColumn(ProjectDto::getPriorityLevel).setHeader("Priority");

        grid.asSingleSelect().addValueChangeListener(event -> populateForm(event.getValue()));

        return grid;
    }


    private void bindFields()
    {
        this.projectBinder.bind(this.titleField, ProjectDto::getTitle, ProjectDto::setTitle);
        this.projectBinder.bind(this.descriptionField, ProjectDto::getDescription, ProjectDto::setDescription);
        this.projectBinder.bind(this.dueDatePicker, ProjectDto::getDueDate, ProjectDto::setDueDate);
        this.projectBinder.bind(this.priorityComboBox, ProjectDto::getPriorityLevel, ProjectDto::setPriorityLevel);
    }


    private void updateGrid()
    {
        final List<ProjectDto> allProjects = this.projectService.getAllProjectDtos();

        projectGrid.setItems(allProjects);
        clearForm();
    }


    private void populateForm(ProjectDto projectDto)
    {
        if (projectDto != null)
        {
            projectBinder.setBean(projectDto);
        }
    }


    private void clearForm()
    {
        this.projectBinder.setBean(new ProjectDto());
    }


    private void addProject()
    {
        final ProjectDto newProject = this.projectBinder.getBean();

        final TaskDto selectedTask = this.taskComboBox.getValue();
        if (selectedTask != null)
        {
            newProject.addTask(selectedTask);
        }

        final ProjectRequest projectRequest = this.projectService.mapProjectDtoToProjecctRequest(newProject);
        this.projectService.createProject(projectRequest);
        updateGrid();
    }


    private void searchProjects()
    {
        final String title = this.titleField.getValue();
        final ProjectPriority priority = this.priorityComboBox.getValue();

        if (title != null)
        {
            final List<ProjectDto> projectsByTitle = this.projectService.getProjectsByTitle(title);
            this.projectGrid.setItems(projectsByTitle);
        }
        else if (priority != null)
        {
            final List<ProjectDto> projectsByPriorityLevel = this.projectService.getProjectsByPriorityLevel(priority);
            this.projectGrid.setItems(projectsByPriorityLevel);
        }
        else
        {
            updateGrid();
        }
    }


    private void updateProject()
    {
        final ProjectDto selectedProject = this.projectGrid.asSingleSelect().getValue();
        if (selectedProject != null)
        {
            final ProjectDto updatedProject = projectBinder.getBean();
            selectedProject.setTitle(updatedProject.getTitle());
            selectedProject.setDescription(updatedProject.getDescription());
            selectedProject.setDueDate(updatedProject.getDueDate());
            selectedProject.setPriorityLevel(updatedProject.getPriorityLevel());

            this.projectService.updateProject(updatedProject);
            updateGrid();
        }
    }


    private void deleteProject()
    {
        final ProjectDto selectedProject = this.projectGrid.asSingleSelect().getValue();
        if (selectedProject != null)
        {
            this.projectService.deleteProjectById(selectedProject.getId());
            updateGrid();
        }
    }
}