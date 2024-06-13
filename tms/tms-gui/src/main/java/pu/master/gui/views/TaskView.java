package pu.master.gui.views;


import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import pu.master.core.services.TaskService;
import pu.master.core.utils.SecurityUtils;
import pu.master.domain.models.dtos.TaskDto;


@Route(value = "tasks", layout = MainLayout.class)
@PageTitle("Tasks | Task Management System")
public class TaskView extends VerticalLayout
{

    private final TaskService taskService;
    private final SecurityUtils securityUtils;
    private final Grid<TaskDto> grid;


    public TaskView(TaskService taskService, final SecurityUtils securityUtils)
    {
        this.taskService = taskService;
        this.securityUtils = securityUtils;

        this.grid = new Grid<>(TaskDto.class);

        addClassName("task-view");
        setSizeFull();

        configureGrid();
        add(getToolbar(), grid);

        updateList();
    }


    private void configureGrid()
    {
        grid.setSizeFull();
        grid.setColumns("id", "title", "description", "priorityLevel", "status");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }


    private HorizontalLayout getToolbar()
    {
        Button addTaskButton = new Button("Add Task");
        addTaskButton.addClickListener(click -> {
            // Open task form
        });

        HorizontalLayout toolbar = new HorizontalLayout(addTaskButton);
        toolbar.addClassName("toolbar");

        return toolbar;
    }


    private void updateList()
    {
        List<TaskDto> tasks = taskService.getTasksByUserEmail(this.securityUtils.getCurrentLoggedInUser().getEmail());
        grid.setItems(tasks);
    }
}