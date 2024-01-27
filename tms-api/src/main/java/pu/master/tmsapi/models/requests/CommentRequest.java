package pu.master.tmsapi.models.requests;


import java.time.LocalDateTime;


// TODO: Add validation
public class CommentRequest
{

    private String text;

    private LocalDateTime timePosted;

    private UserRequest author;

    private TaskRequest task;
}
