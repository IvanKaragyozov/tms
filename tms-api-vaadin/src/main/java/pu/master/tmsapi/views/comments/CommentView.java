package pu.master.tmsapi.views.comments;


import java.util.List;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import pu.master.tmsapi.models.dtos.CommentDto;
import pu.master.tmsapi.models.dtos.TaskDto;
import pu.master.tmsapi.models.requests.CommentRequest;
import pu.master.tmsapi.services.CommentService;
import pu.master.tmsapi.services.TaskService;
import pu.master.tmsapi.views.MainLayout;


@PageTitle("Comments")
@Route(value = "/comments-view")
public class CommentView extends Composite<MainLayout>
{

    private final CommentService commentService;
    private final TaskService taskService;

    private final Grid<CommentDto> commentGrid;

    private final TextArea textArea;
    private final ComboBox<TaskDto> taskComboBox;

    private final Button addButton;
    private final Button updateButton;
    private final Button deleteButton;

    private final Binder<CommentDto> commentBinder;


    public CommentView(CommentService commentService, TaskService taskService)
    {
        this.commentService = commentService;
        this.taskService = taskService;

        this.taskComboBox = new ComboBox<>("Select Task");
        this.taskComboBox.setItemLabelGenerator(TaskDto::getTitle);
        this.taskComboBox.setItems(this.taskService.getAllTaskDtos());

        this.commentGrid = createCommentGrid();
        this.textArea = new TextArea("Comment Text");

        this.addButton = new Button("Add", event -> addComment());
        this.updateButton = new Button("Update", event -> updateComment());
        this.deleteButton = new Button("Delete", event -> deleteComment());

        this.commentBinder = new Binder<>(CommentDto.class);

        initLayout();
        bindFields();
        updateGrid();
    }


    private void initLayout()
    {
        getContent().setSizeFull();
        getContent().setPadding(true);

        final HorizontalLayout formLayout = createFormLayout();
        final VerticalLayout actionsLayout = createActionsLayout();

        getContent().add(formLayout, actionsLayout, commentGrid);
    }


    private HorizontalLayout createFormLayout()
    {
        final HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("100%");
        formLayout.setSpacing(true);

        final FormLayout commentForm = new FormLayout();
        commentForm.setWidth("100%");
        commentForm.add(this.textArea, this.taskComboBox);

        formLayout.add(commentForm, createCommentDetailsLayout());
        return formLayout;
    }


    private VerticalLayout createCommentDetailsLayout()
    {
        final VerticalLayout commentDetailsLayout = new VerticalLayout();
        commentDetailsLayout.setWidth("300px");
        commentDetailsLayout.setSpacing(true);

        commentDetailsLayout.add(this.addButton, this.updateButton, this.deleteButton);
        return commentDetailsLayout;
    }


    private VerticalLayout createActionsLayout()
    {
        final VerticalLayout actionsLayout = new VerticalLayout();
        actionsLayout.setWidth("100%");
        actionsLayout.setSpacing(true);

        return actionsLayout;
    }


    private Grid<CommentDto> createCommentGrid()
    {
        final Grid<CommentDto> grid = new Grid<>();
        grid.setSizeFull();
        grid.addColumn(CommentDto::getText).setHeader("Comment Text");
        grid.addColumn(commentDto -> commentDto.getTaskDto() != null ? commentDto.getTaskDto().getTitle() : "")
            .setHeader("Task");

        grid.asSingleSelect().addValueChangeListener(event -> populateForm(event.getValue()));

        return grid;
    }


    private void bindFields()
    {
        this.commentBinder.bind(this.textArea, CommentDto::getText, CommentDto::setText);
        this.commentBinder.bind(this.taskComboBox, CommentDto::getTaskDto, CommentDto::setTaskDto);
    }


    private void updateGrid()
    {
        final List<CommentDto> allComments = this.commentService.getAllCommentDtos();

        this.commentGrid.setItems(allComments);
        clearForm();
    }


    private void populateForm(CommentDto commentDto)
    {
        if (commentDto != null)
        {
            this.commentBinder.setBean(commentDto);
        }
    }


    private void clearForm()
    {
        this.commentBinder.setBean(new CommentDto());
    }


    private void addComment()
    {
        final CommentDto newComment = this.commentBinder.getBean();

        final TaskDto selectedTask = this.taskComboBox.getValue();
        if (selectedTask != null)
        {
            newComment.setTaskDto(selectedTask);
        }

        final CommentRequest commentRequest = this.commentService.mapCommentDtoToCommentRequest(newComment);
        this.commentService.createComment(commentRequest);
        updateGrid();
    }


    private void updateComment()
    {
        final CommentDto selectedComment = this.commentGrid.asSingleSelect().getValue();
        if (selectedComment != null)
        {
            final CommentDto updatedComment = this.commentBinder.getBean();
            selectedComment.setText(updatedComment.getText());
            selectedComment.setTaskDto(updatedComment.getTaskDto());

            this.commentService.updateComment(updatedComment);
            updateGrid();
        }
    }


    private void deleteComment()
    {
        final CommentDto selectedComment = this.commentGrid.asSingleSelect().getValue();
        if (selectedComment != null)
        {
            this.commentService.deleteCommentById(selectedComment.getId());
            updateGrid();
        }
    }
}