package pu.master.tmsgui.services;


import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsgui.models.dtos.CommentDto;
import pu.master.tmsgui.models.entities.Comment;
import pu.master.tmsgui.models.entities.Task;
import pu.master.tmsgui.models.entities.User;
import pu.master.tmsgui.models.requests.CommentRequest;
import pu.master.tmsgui.repositories.CommentRepository;


@Service
public class CommentService
{

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
        final Comment comment = mapCommentRequestToComment(commentRequest);
        final User author = this.userService.getUserById(commentRequest.getAuthor());
        final Task task = this.taskService.getTaskById(commentRequest.getTask());

        comment.setAuthor(author);
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


    private Comment mapCommentRequestToComment(final CommentRequest commentRequest)
    {
        return this.modelMapper.map(commentRequest, Comment.class);
    }


    private CommentDto mapCommentToCommentDto(final Comment comment)
    {
        return this.modelMapper.map(comment, CommentDto.class);
    }
}
