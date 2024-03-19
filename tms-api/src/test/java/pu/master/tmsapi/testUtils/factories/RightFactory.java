package pu.master.tmsapi.testUtils.factories;


import pu.master.tmsapi.models.dtos.RightDto;
import pu.master.tmsapi.models.entities.Right;
import pu.master.tmsapi.models.requests.RightRequest;

import static pu.master.tmsapi.testUtils.constants.RightConstants.RIGHT_ID;
import static pu.master.tmsapi.testUtils.constants.RightConstants.RIGHT_NAME;


public final class RightFactory
{

    public static Right DEFAULT_RIGHT = new Right(RIGHT_ID, RIGHT_NAME);

    public static RightDto DEFAULT_RIGHT_DTO = new RightDto(RIGHT_ID, RIGHT_NAME);

    public static RightRequest DEFAULT_RIGHT_REQUEST = new RightRequest(RIGHT_NAME);

    private RightFactory() {}

}
