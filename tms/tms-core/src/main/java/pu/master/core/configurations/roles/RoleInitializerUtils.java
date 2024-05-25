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
    private static final Set<String> adminRightNames = Arrays.stream(AdminRightNames.values())
                                                             .map(Enum::name)
                                                             .collect(Collectors.toSet());

    static Set<String> ALL_RIGHT_NAMES = Arrays.stream(UserRightNames.values())
                                               .map(Enum::name)
                                               .filter(name -> !adminRightNames.contains(name))
                                               .collect(Collectors.toSet());

}
