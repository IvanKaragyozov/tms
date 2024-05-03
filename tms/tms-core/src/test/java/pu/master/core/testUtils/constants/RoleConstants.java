package pu.master.core.testUtils.constants;


import java.util.Collections;
import java.util.Set;
import pu.master.core.testUtils.factories.RightFactory;
import pu.master.domain.models.dtos.RightDto;
import pu.master.domain.models.entities.Right;


public final class RoleConstants
{

    public static final long ROLE_ID = 1L;

    public static final String ROLE_NAME = "TEST_USER";

    public static final Set<Right> ROLE_RIGHTS = Collections.singleton(RightFactory.DEFAULT_RIGHT);

    public static final Set<RightDto> ROLE_DTO_RIGHTS = Collections.singleton(RightFactory.DEFAULT_RIGHT_DTO);

    public static final Set<String> ROLE_REQUEST_RIGHTS = Collections.singleton(RightConstants.RIGHT_NAME);


    private RoleConstants()
    {
    }
}
