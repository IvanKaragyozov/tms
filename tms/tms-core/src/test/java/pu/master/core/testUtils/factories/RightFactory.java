package pu.master.core.testUtils.factories;


import java.util.Collections;
import java.util.List;
import pu.master.domain.models.dtos.RightDto;
import pu.master.domain.models.entities.Right;
import pu.master.domain.models.requests.RightRequest;

import static pu.master.core.testUtils.constants.RightConstants.RIGHT_ID;
import static pu.master.core.testUtils.constants.RightConstants.RIGHT_NAME;


public final class RightFactory
{

    public static Right DEFAULT_RIGHT = new Right(RIGHT_ID, RIGHT_NAME);

    public static RightDto DEFAULT_RIGHT_DTO = new RightDto(RIGHT_ID, RIGHT_NAME);

    public static RightRequest DEFAULT_RIGHT_REQUEST = new RightRequest(RIGHT_NAME);

    public static List<Right> DEFAULT_RIGHT_LIST = Collections.singletonList(DEFAULT_RIGHT);

    public static List<RightDto> DEFAULT_RIGHT_DTO_LIST = Collections.singletonList(DEFAULT_RIGHT_DTO);

    public static List<RightRequest> DEFAULT_RIGHT_REQUEST_LIST = Collections.singletonList(DEFAULT_RIGHT_REQUEST);


    private RightFactory() {}

}
