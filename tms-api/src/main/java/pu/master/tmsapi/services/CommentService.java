package pu.master.tmsapi.services;


import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pu.master.tmsapi.mappers.CommentMapper;
import pu.master.tmsapi.models.dtos.CommentDto;
import pu.master.tmsapi.models.entities.Comment;
import pu.master.tmsapi.models.entities.Task;
import pu.master.tmsapi.models.entities.User;
import pu.master.tmsapi.models.requests.CommentRequest;
import pu.master.tmsapi.repositories.CommentRepository;


@Service
public class CommentService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;

    private final UserService userService;
    private final TaskService taskService;

    private final CommentMapper commentMapper;


    @Autowired
    public CommentService(final CommentRepository commentRepository,
                          final UserService userService,
                          final TaskService taskService,
                          final CommentMapper commentMapper)
    {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.taskService = taskService;
        this.commentMapper = commentMapper;
    }


    public Comment createComment(final CommentRequest commentRequest)
    {
        final Comment comment = this.commentMapper.mapCommentRequestToComment(commentRequest);
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
                                     .map(this.commentMapper::mapCommentToDto)
                                     .toList();
    }

}
