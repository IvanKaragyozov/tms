package pu.master.tmsapi.mappers;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pu.master.tmsapi.models.dtos.CommentDto;
import pu.master.tmsapi.models.entities.Comment;
import pu.master.tmsapi.models.requests.CommentRequest;


@Mapper
public class CommentMapper
{

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentMapper.class);

    private final ModelMapper modelMapper;


    @Autowired
    public CommentMapper(final ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }


    public Comment mapCommentRequestToComment(final CommentRequest commentRequest)
    {
        LOGGER.debug("Mapping CommentRequest to Comment");
        return this.modelMapper.map(commentRequest, Comment.class);
    }


    public CommentDto mapCommentToDto(final Comment comment)
    {
        LOGGER.debug("Mapping Comment to CommentDto");
        return this.modelMapper.map(comment, CommentDto.class);
    }
}
