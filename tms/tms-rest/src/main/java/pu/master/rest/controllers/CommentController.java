package pu.master.rest.controllers;


import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import pu.master.core.services.CommentService;
import pu.master.domain.models.dtos.CommentDto;
import pu.master.domain.models.entities.Comment;
import pu.master.domain.models.requests.CommentRequest;


@RestController
public class CommentController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;


    @Autowired
    public CommentController(final CommentService commentService)
    {
        this.commentService = commentService;
    }


    @PostMapping("/comments")
    public ResponseEntity<Void> createComment(@RequestBody @Valid final CommentRequest commentRequest)
    {
        LOGGER.debug("Trying to save comment to the database");
        final Comment comment = this.commentService.createComment(commentRequest);
        LOGGER.info("Created new comment");

        final URI location = UriComponentsBuilder.fromUriString("/comments/{id}")
                                                 .buildAndExpand(comment.getId())
                                                 .toUri();

        return ResponseEntity.created(location).build();
    }


    @GetMapping("/tasks/{id}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByTaskId(@PathVariable final long id)
    {
        final List<CommentDto> commentDtos = this.commentService.getCommentsByTaskId(id);

        LOGGER.info(String.format("Request sent for all comments by task with id %d", id));

        return ResponseEntity.ok(commentDtos);
    }
}
