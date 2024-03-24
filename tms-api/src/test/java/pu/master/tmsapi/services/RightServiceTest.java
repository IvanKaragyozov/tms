package pu.master.tmsapi.services;


import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pu.master.tmsapi.exceptions.RightNameAlreadyExistsException;
import pu.master.tmsapi.mappers.RightMapper;
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
    public void testCreateRight_success()
    {
        Mockito.when(rightMapper.mapRightRequestToRight(Mockito.any(RightRequest.class)))
               .thenReturn(RightFactory.DEFAULT_RIGHT);
        Mockito.when(rightRepository.save(Mockito.any())).thenReturn(RightFactory.DEFAULT_RIGHT);

        final Right result = rightService.createRight(RightFactory.DEFAULT_RIGHT_REQUEST);

        Assertions.assertEquals(RightFactory.DEFAULT_RIGHT, result);
    }


    @Test
    public void testCreateRight_existingRight_throwsRightAlreadyExistsException()
    {
        Mockito.when(rightMapper.mapRightRequestToRight(Mockito.any(RightRequest.class)))
               .thenReturn(RightFactory.DEFAULT_RIGHT);
        Mockito.when(rightRepository.getRightByName(Mockito.anyString()))
               .thenReturn(Optional.of(RightFactory.DEFAULT_RIGHT));

        final String exceptionMessage = String.format("Right with name [%s] already exists!", RightConstants.RIGHT_NAME);

        Assertions.assertThrows(RightNameAlreadyExistsException.class,
                                () -> rightService.createRight(RightFactory.DEFAULT_RIGHT_REQUEST),
                                exceptionMessage);
    }
}
