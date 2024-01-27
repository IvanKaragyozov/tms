package pu.master.tmsapi.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.repositories.CommentRepository;


@Service
public class CommentService
{

    private final CommentRepository commentRepository;


    @Autowired
    public CommentService(final CommentRepository commentRepository)
    {
        this.commentRepository = commentRepository;
    }
}
