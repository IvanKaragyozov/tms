package pu.master.tmsapi.models.requests;


import java.time.LocalDateTime;
import lombok.Data;


@Data
public class CommentRequest
{

    private String text;

    private LocalDateTime timePosted;

    private Long author;

    private Long task;

}
