package pu.master.tmsapi.services;


import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaadin.flow.component.notification.Notification;

import pu.master.tmsapi.exceptions.CommentNotFoundException;
import pu.master.tmsapi.exceptions.EmptyTaskException;
import pu.master.tmsapi.models.dtos.CommentDto;
import pu.master.tmsapi.models.dtos.TaskDto;
import pu.master.tmsapi.models.entities.Comment;
import pu.master.tmsapi.models.entities.Task;
import pu.master.tmsapi.models.requests.CommentRequest;
import pu.master.tmsapi.repositories.CommentRepository;


@Service
public class CommentService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class.getName());

    private final CommentRepository commentRepository;

    private final UserService userService;
    private final TaskService taskService;

    private final ModelMapper modelMapper;


    @Autowired
    public CommentService(final CommentRepository commentRepository,
                          final UserService userService,
                          final TaskService taskService,
                          final ModelMapper modelMapper)
    {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }


    public Comment createComment(final CommentRequest commentRequest)
    {
        if (commentRequest.getTask() == null)
        {
            final String message = "Task cannot be empty!";

            Notification.show(message, 3000, Notification.Position.TOP_CENTER);
            LOGGER.error(message);
            throw new EmptyTaskException(message);
        }
        final Comment comment = mapCommentRequestToComment(commentRequest);
        final Task task = this.taskService.getTaskById(commentRequest.getTask());

        comment.setTask(task);
        comment.setTimePosted(LocalDateTime.now());


        return this.commentRepository.save(comment);
    }


    public List<CommentDto> getCommentsByTaskId(final long taskId)
    {
        final Task task = this.taskService.getTaskById(taskId);

        return this.commentRepository.findCommentsByTaskId(task.getId()).stream()
                                     .map(this::mapCommentToCommentDto)
                                     .toList();
    }


    public Comment getCommentById(final long commentId)
    {
        LOGGER.info(String.format("Trying to retrieve task with id %d", commentId));
        return this.commentRepository.findById(commentId).orElseThrow(() -> {

            LOGGER.error(String.format("Tried to retrieve a comment with id %d that does not exist!", commentId));
            throw new CommentNotFoundException(String.format("Comment with id %d does not exist!", commentId));

        });
    }


    public List<CommentDto> getAllCommentDtos()
    {
        final List<Comment> allComments = this.commentRepository.findAll();

        final List<CommentDto> commentDtos = allComments.stream().map(this::mapCommentToCommentDto).toList();

        return commentDtos;
    }


    public Comment updateComment(final CommentDto commentDto)
    {
        final Comment comment = mapCommentDtoToComment(commentDto);
        return this.commentRepository.save(comment);
    }


    public Comment deleteCommentById(final long commentId)
    {
        LOGGER.error(String.format("Tried to delete a comment with id %d that does not exist", commentId));
        final Comment commentById = getCommentById(commentId);
        this.commentRepository.delete(commentById);

        return commentById;
    }


    public Comment mapCommentDtoToComment(final CommentDto commentDto)
    {
        return this.modelMapper.map(commentDto, Comment.class);
    }


    public Comment mapCommentRequestToComment(final CommentRequest commentRequest)
    {
        return this.modelMapper.map(commentRequest, Comment.class);
    }


    public CommentDto mapCommentToCommentDto(final Comment comment)
    {
        final CommentDto commentDto = this.modelMapper.map(comment, CommentDto.class);

        final Task task = comment.getTask();
        if (task != null)
        {
            final TaskDto taskDto = this.taskService.mapTaskToTaskDto(task);
            commentDto.setTaskDto(taskDto);
        }

        return commentDto;
    }


    public CommentRequest mapCommentDtoToCommentRequest(final CommentDto commentDto)
    {
        final CommentRequest commentRequest = new CommentRequest();

        commentRequest.setText(commentDto.getText());
        commentRequest.setTimePosted(commentDto.getTimePosted());

        if (commentDto.getTaskDto() != null)
        {
            commentRequest.setTask(commentDto.getTaskDto().getId());
        }

        return commentRequest;
    }

}
