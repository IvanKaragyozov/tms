package pu.master.tmsapi.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.models.dtos.CommentDto;
import pu.master.tmsapi.models.dtos.UserDto;
import pu.master.tmsapi.models.entities.Comment;
import pu.master.tmsapi.models.entities.User;
import pu.master.tmsapi.models.requests.CommentRequest;
import pu.master.tmsapi.models.requests.UserRequest;
import pu.master.tmsapi.repositories.CommentRepository;


@Service
public class CommentService
{

    private final CommentRepository commentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CommentService(final CommentRepository commentRepository, final ModelMapper modelMapper)
    {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    public Comment createComment(final CommentRequest commentRequest)
    {
        final Comment comment = mapCommentRequestToComment(commentRequest);
        // TODO: Set task and user

        return comment;
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
