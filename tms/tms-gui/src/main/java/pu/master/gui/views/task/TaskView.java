package pu.master.gui.views.task;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import pu.master.core.services.TaskService;
import pu.master.domain.models.entities.Task;
import pu.master.gui.views.MainLayout;


@PageTitle("Tasks")
@Route(value = "/tasks", layout = MainLayout.class)
//@RolesAllowed(value = {"ADMIN", "USER"})
@AnonymousAllowed
public class TaskView extends VerticalLayout
{
    private final TaskService taskService;
    private TaskGrid taskGrid;
    private Button editButton;
    private Button removeButton;


    @Autowired
    public TaskView(TaskService taskService, TaskGrid taskGrid)
    {
        this.taskService = taskService;
        this.taskGrid = taskGrid;
        initializeContent();
    }


    private void initializeContent()
    {
        super.setSizeFull();
        super.add(createButtons(), createGrid());
    }


    private Component createGrid()
    {
        this.taskGrid = new TaskGrid();
        this.taskGrid.setSizeFull();
        reloadGrid();

        final SingleSelect<Grid<Task>, Task> singleSelect = this.taskGrid.asSingleSelect();
        singleSelect.addValueChangeListener(l -> {
            final Task value = l.getValue();
            editButton.setEnabled(value != null);
            removeButton.setEnabled(value != null);
        });

        return taskGrid;
    }


    private HorizontalLayout createButtons()
    {
        final Button createButton = new Button(new Icon(VaadinIcon.PLAY_CIRCLE), l -> openTaskDialog(new Task()));
        this.editButton = new Button(new Icon(VaadinIcon.PENCIL),
                                     l -> openTaskDialog(taskGrid.asSingleSelect().getValue()));
        this.removeButton = new Button(new Icon(VaadinIcon.TRASH));
        this.removeButton.setEnabled(false);

        return new HorizontalLayout(createButton, editButton, removeButton);
    }


    private void openTaskDialog(final Task task)
    {
        TaskDialog productDialog = new TaskDialog(task, taskService);
        productDialog.addSaveClickListener(l -> reloadGrid());
        productDialog.open();
    }


    private void reloadGrid()
    {
        this.taskGrid.setItems(this.taskService.getTasksByCurrentLoggedInUser());
    }
}
