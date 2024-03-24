package pu.master.tmsapi.testUtils.factories;


import pu.master.tmsapi.models.dtos.RoleDto;
import pu.master.tmsapi.models.entities.Role;
import pu.master.tmsapi.models.requests.RoleRequest;

import static pu.master.tmsapi.testUtils.constants.RoleConstants.ROLE_DTO_RIGHTS;
import static pu.master.tmsapi.testUtils.constants.RoleConstants.ROLE_ID;
import static pu.master.tmsapi.testUtils.constants.RoleConstants.ROLE_NAME;
import static pu.master.tmsapi.testUtils.constants.RoleConstants.ROLE_REQUEST_RIGHTS;
import static pu.master.tmsapi.testUtils.constants.RoleConstants.ROLE_RIGHTS;


public final class RoleFactory
{

    public static final Role DEFAULT_ROLE = new Role(ROLE_ID, ROLE_NAME, ROLE_RIGHTS);

    public static final RoleDto DEFAULT_ROLE_DTO = new RoleDto(ROLE_ID, ROLE_NAME, ROLE_DTO_RIGHTS);

    public static final RoleRequest DEFAULT_ROLE_REQUEST = new RoleRequest(ROLE_NAME, ROLE_REQUEST_RIGHTS);

    private RoleFactory() {}
}