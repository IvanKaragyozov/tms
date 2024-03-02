package pu.master.tmsapi.mappers;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pu.master.tmsapi.models.dtos.RightDto;
import pu.master.tmsapi.models.entities.Right;
import pu.master.tmsapi.models.requests.RightRequest;


@Mapper
public class RightMapper
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RightMapper.class);

    private final ModelMapper modelMapper;


    @Autowired
    public RightMapper(final ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }


    public Right mapRightRequestToRight(final RightRequest rightRequest)
    {
        LOGGER.debug("Mapping RightRequest to Right");
        return this.modelMapper.map(rightRequest, Right.class);
    }


    public RightDto mapRightToDto(final Right right)
    {
        LOGGER.debug("Mapping Right to RightDto");
        return this.modelMapper.map(right, RightDto.class);
    }
}
