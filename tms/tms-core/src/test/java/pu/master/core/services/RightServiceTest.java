package pu.master.tmsapi.services;


import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pu.master.tmsapi.exceptions.RightNameAlreadyExistsException;
import pu.master.tmsapi.exceptions.RightNotFoundException;
import pu.master.tmsapi.mappers.RightMapper;
import pu.master.tmsapi.models.dtos.RightDto;
import pu.master.tmsapi.models.entities.Right;
import pu.master.tmsapi.models.requests.RightRequest;
import pu.master.tmsapi.repositories.RightRepository;
import pu.master.tmsapi.testUtils.constants.RightConstants;
import pu.master.tmsapi.testUtils.factories.RightFactory;


@ExtendWith(MockitoExtension.class)
public class RightServiceTest
{

    @Mock
    private RightRepository rightRepository;

    @Mock
    private RightMapper rightMapper;

    @InjectMocks
    private RightService rightService;


    @Test
    void testCreateRight_success()
    {
        Mockito.when(rightMapper.mapRightRequestToRight(Mockito.any(RightRequest.class)))
               .thenReturn(RightFactory.DEFAULT_RIGHT);
        Mockito.when(rightRepository.save(Mockito.any())).thenReturn(RightFactory.DEFAULT_RIGHT);

        final Right result = rightService.createRight(RightFactory.DEFAULT_RIGHT_REQUEST);

        Assertions.assertEquals(RightFactory.DEFAULT_RIGHT, result);
    }


    @Test
    void testCreateRight_existingRight_throwsRightAlreadyExistsException()
    {
        Mockito.when(rightRepository.existsByName(Mockito.anyString())).thenReturn(true);

        final RightNameAlreadyExistsException exception =
                        Assertions.assertThrows(RightNameAlreadyExistsException.class,
                                                () -> rightService.createRight(RightFactory.DEFAULT_RIGHT_REQUEST));

        final String exceptionMessage = String.format("Right with name [%s] already exists!",
                                                      RightConstants.RIGHT_NAME);

        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void testGetAllRightDtos_success()
    {
        Mockito.when(rightRepository.findAll()).thenReturn(RightFactory.DEFAULT_RIGHT_LIST);
        Mockito.when(rightMapper.mapRightToDto(Mockito.any(Right.class))).thenReturn(RightFactory.DEFAULT_RIGHT_DTO);

        final List<RightDto> result = rightService.getAllRightsDtos();

        Assertions.assertEquals(RightFactory.DEFAULT_RIGHT_DTO_LIST, result);
    }

    @Test
    void testGetRightById_success()
    {
        Mockito.when(rightRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(RightFactory.DEFAULT_RIGHT));

        final Right result = rightService.getRightById(RightConstants.RIGHT_ID);

        Assertions.assertEquals(RightFactory.DEFAULT_RIGHT, result);
    }

    @Test
    void testGetRightById_rightNotFound_throwsRightNotFoundException()
    {
        Mockito.when(rightRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        final RightNotFoundException exception =
                        Assertions.assertThrows(RightNotFoundException.class,
                                                () -> rightService.getRightById(RightConstants.RIGHT_ID));

        final String exceptionMessage = String.format("Right with id [%s] not found!", RightConstants.RIGHT_ID);

        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void testGetRightByName_success()
    {
        Mockito.when(rightRepository.findRightByName(Mockito.anyString())).thenReturn(Optional.of(RightFactory.DEFAULT_RIGHT));

        final Right result = rightService.getRightByName(RightConstants.RIGHT_NAME);

        Assertions.assertEquals(RightFactory.DEFAULT_RIGHT, result);
    }

    @Test
    void testGetRightByName_rightNotFound_throwsRightNotFoundException()
    {
        Mockito.when(rightRepository.findRightByName(Mockito.anyString())).thenReturn(Optional.empty());

        final RightNotFoundException exception =
                        Assertions.assertThrows(RightNotFoundException.class,
                                                () -> rightService.getRightByName(RightConstants.RIGHT_NAME));

        final String exceptionMessage = String.format("Right with name [%s] not found!", RightConstants.RIGHT_NAME);

        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }

}
