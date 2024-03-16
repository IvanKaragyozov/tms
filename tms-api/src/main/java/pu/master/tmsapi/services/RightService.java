package pu.master.tmsapi.services;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pu.master.tmsapi.exceptions.RightNotFoundException;
import pu.master.tmsapi.mappers.RightMapper;
import pu.master.tmsapi.models.dtos.RightDto;
import pu.master.tmsapi.models.entities.Right;
import pu.master.tmsapi.models.requests.RightRequest;
import pu.master.tmsapi.repositories.RightRepository;


@Service
public class RightService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(RightService.class);

    private final RightRepository rightRepository;

    private final RightMapper rightMapper;


    @Autowired
    public RightService(final RightRepository rightRepository, final RightMapper rightMapper)
    {
        this.rightRepository = rightRepository;
        this.rightMapper = rightMapper;
    }


    public Right createRight(final RightRequest rightRequest)
    {
        final Right right = this.rightMapper.mapRightRequestToRight(rightRequest);
        return this.rightRepository.save(right);
    }


    public List<RightDto> getAllRightsDtos()
    {
        final List<Right> allRights = this.rightRepository.findAll();

        return allRights.stream()
                        .map(this.rightMapper::mapRightToDto)
                        .toList();
    }


    public Right getRightById(final long rightId)
    {
        return this.rightRepository.findById(rightId).orElseThrow(() -> {
            LOGGER.error(String.format("Could not find right with name [%s]", rightId));
            return new RightNotFoundException(String.format("Right with name [%s] not found", rightId));
        });
    }

    public Right getRightByName(final String name)
    {
        return this.rightRepository.getRightByName(name).orElseThrow(() -> {
            LOGGER.error(String.format("Could not find right with name [%s]", name));
            return new RightNotFoundException(String.format("Right with name [%s] not found", name));
        });
    }

}
