package pu.master.core.services;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pu.master.core.exceptions.RightNameAlreadyExistsException;
import pu.master.core.exceptions.RightNotFoundException;
import pu.master.core.mappers.RightMapper;
import pu.master.core.repositories.RightRepository;
import pu.master.domain.models.dtos.RightDto;
import pu.master.domain.models.entities.Right;
import pu.master.domain.models.requests.RightRequest;


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

        if (this.rightRepository.existsByName(rightRequest.getName()))
        {
            LOGGER.error(String.format("Right with name [%s] already exists!", rightRequest.getName()));
            throw new RightNameAlreadyExistsException(String.format("Right with name [%s] already exists!",
                                                                    rightRequest.getName()));
        }

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
            LOGGER.error(String.format("Could not find right with id [%s]", rightId));
            return new RightNotFoundException(String.format("Right with id [%s] not found!", rightId));
        });
    }


    public Right getRightByName(final String name)
    {
        return this.rightRepository.findRightByName(name).orElseThrow(() -> {
            LOGGER.error(String.format("Could not find right with name [%s]!", name));
            return new RightNotFoundException(String.format("Right with name [%s] not found!", name));
        });
    }

}
