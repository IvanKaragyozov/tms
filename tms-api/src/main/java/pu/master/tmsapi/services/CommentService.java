package pu.master.tmsapi.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.models.dtos.CommentDto;
import pu.master.tmsapi.models.entities.Comment;
import pu.master.tmsapi.models.entities.Task;
import pu.master.tmsapi.models.entities.User;
import pu.master.tmsapi.models.requests.CommentRequest;
import pu.master.tmsapi.repositories.CommentRepository;


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

        return this.commentRepository.save(comment);
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
