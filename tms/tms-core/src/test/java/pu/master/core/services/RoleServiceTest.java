package pu.master.core.services;


import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pu.master.core.exceptions.RoleNameAlreadyExistsException;
import pu.master.core.exceptions.RoleNotFoundException;
import pu.master.core.mappers.RoleMapper;
import pu.master.core.repositories.RoleRepository;
import pu.master.core.testUtils.constants.RoleConstants;
import pu.master.core.testUtils.factories.RightFactory;
import pu.master.core.testUtils.factories.RoleFactory;
import pu.master.domain.models.dtos.RoleDto;
import pu.master.domain.models.entities.Role;
import pu.master.domain.models.requests.RoleRequest;


@ExtendWith(MockitoExtension.class)
public class RoleServiceTest
{

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private RightService rightService;

    @Mock
    private RoleMapper roleMapper;

    @InjectMocks
    private RoleService roleService;


    @Test
    void createRole_success()
    {
        Mockito.when(roleMapper.mapRoleRequestToRole(Mockito.any(RoleRequest.class)))
               .thenReturn(RoleFactory.DEFAULT_ROLE);
        Mockito.when(rightService.getRightByName(Mockito.anyString())).thenReturn(RightFactory.DEFAULT_RIGHT);
        Mockito.when(roleRepository.save(Mockito.any(Role.class))).thenReturn(RoleFactory.DEFAULT_ROLE);

        final Role result = roleService.createRole(RoleFactory.DEFAULT_ROLE_REQUEST);

        Assertions.assertEquals(RoleFactory.DEFAULT_ROLE, result);
    }


    @Test
    void createRole_roleAlreadyExists_throwsRoleNameAlreadyExistsException()
    {
        Mockito.when(roleRepository.existsByName(Mockito.anyString())).thenReturn(true);

        final RoleNameAlreadyExistsException exception = Assertions.assertThrows(
                        RoleNameAlreadyExistsException.class,
                        () -> roleService.createRole(RoleFactory.DEFAULT_ROLE_REQUEST));

        final String exceptionMessage = String.format("Role with name [%s] already exists!", RoleConstants.ROLE_NAME);

        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }


    @Test
    void testGetAllRightDtos_success()
    {
        Mockito.when(roleRepository.findAll()).thenReturn(RoleFactory.DEFAULT_ROLE_LIST);
        Mockito.when(roleMapper.mapRoleToDto(Mockito.any(Role.class))).thenReturn(RoleFactory.DEFAULT_ROLE_DTO);

        final List<RoleDto> result = roleService.getAllRolesDtos();

        Assertions.assertEquals(RoleFactory.DEFAULT_ROLE_DTO_LIST, result);
    }


    @Test
    void testGetRightById_success()
    {
        Mockito.when(roleRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(RoleFactory.DEFAULT_ROLE));

        final Role result = roleService.getRoleById(RoleConstants.ROLE_ID);

        Assertions.assertEquals(RoleFactory.DEFAULT_ROLE, result);
    }


    @Test
    void testGetRoleById_roleNotFound_throwsRoleNotFoundException()
    {
        Mockito.when(roleRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        final RoleNotFoundException exception =
                        Assertions.assertThrows(RoleNotFoundException.class,
                                                () -> roleService.getRoleById(RoleConstants.ROLE_ID));

        final String exceptionMessage = String.format("Role with id [%s] not found!", RoleConstants.ROLE_ID);

        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }


    @Test
    void testGetRoleByName_success()
    {
        Mockito.when(roleRepository.findRoleByName(Mockito.anyString()))
               .thenReturn(Optional.of(RoleFactory.DEFAULT_ROLE));

        final Role result = roleService.getRoleByName(RoleConstants.ROLE_NAME);

        Assertions.assertEquals(RoleFactory.DEFAULT_ROLE, result);
    }


    @Test
    void testGetRoleByName_roleNotFound_throwsRoleNotFoundException()
    {
        Mockito.when(roleRepository.findRoleByName(Mockito.anyString())).thenReturn(Optional.empty());

        final RoleNotFoundException exception =
                        Assertions.assertThrows(RoleNotFoundException.class,
                                                () -> roleService.getRoleByName(RoleConstants.ROLE_NAME));

        final String exceptionMessage = String.format("Role with name [%s] not found!", RoleConstants.ROLE_NAME);

        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }
}
