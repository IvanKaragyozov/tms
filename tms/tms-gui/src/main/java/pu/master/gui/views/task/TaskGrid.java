package pu.master.gui.views.task;


import com.vaadin.flow.component.grid.Grid;
import pu.master.domain.models.entities.Task;


public class TaskGrid extends Grid<Task>
{
    public TaskGrid()
    {
        super(Task.class, false);
        initializeGrid();
    }

    private void initializeGrid()
    {
        super.setSizeFull();
        super.addColumn("title").setFrozen(true).setHeader("Title");
        super.addColumn(Task::getDescription).setHeader("Description");
        super.addColumn(Task::getPriorityLevel).setHeader("Priority");
        super.addColumn(Task::getStatus).setHeader("Status");

    }
}
