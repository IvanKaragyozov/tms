package pu.master.tmsapi.services;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pu.master.tmsapi.models.dtos.RightDto;
import pu.master.tmsapi.models.entities.Right;
import pu.master.tmsapi.models.requests.RightRequest;
import pu.master.tmsapi.repositories.RightRepository;


@Service
public class RightService
{

    private final RightRepository rightRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public RightService(final RightRepository rightRepository, final ModelMapper modelMapper)
    {
        this.rightRepository = rightRepository;
        this.modelMapper = modelMapper;
    }


    public Right createRight(final RightRequest rightRequest)
    {
        final Right right = mapRightRequestToRight(rightRequest);
        return this.rightRepository.save(right);
    }


    public List<RightDto> getAllRights()
    {
        final List<Right> allRights = this.rightRepository.findAll();

        return allRights.stream()
                        .map(this::mapRightToRightDto)
                        .toList();
    }


    private Right mapRightRequestToRight(final RightRequest rightRequest)
    {
        return this.modelMapper.map(rightRequest, Right.class);
    }


    private RightDto mapRightToRightDto(final Right right)
    {
        return this.modelMapper.map(right, RightDto.class);
    }

}
