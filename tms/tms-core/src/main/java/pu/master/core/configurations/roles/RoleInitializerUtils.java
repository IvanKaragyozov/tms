package pu.master.core.configurations.roles;


import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import pu.master.core.utils.constants.rightNames.AdminRightNames;
import pu.master.core.utils.constants.rightNames.UserRightNames;
import pu.master.domain.models.entities.Right;


final class RoleInitializerUtils
{

    static Set<Right> DEFAULT_USER_RIGHTS = Arrays.stream(UserRightNames.values())
                                                  .map(right -> new Right(right.name()))
                                                  .collect(Collectors.toSet());

    static Set<Right> ADMIN_RIGHTS = Arrays.stream(AdminRightNames.values())
                                           .map(right -> new Right(right.name()))
                                           .collect(Collectors.toSet());

}
