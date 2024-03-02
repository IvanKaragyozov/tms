package pu.master.tmsapi.mappers;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pu.master.tmsapi.models.dtos.TaskDto;
import pu.master.tmsapi.models.entities.Task;
import pu.master.tmsapi.models.requests.TaskRequest;


@Mapper
public class TaskMapper
{

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskMapper.class);

    private final ModelMapper modelMapper;


    @Autowired
    public TaskMapper(final ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }


    public Task mapTaskRequestToTask(final TaskRequest taskRequest)
    {
        LOGGER.debug("Mapping TaskRequest To Task");
        return this.modelMapper.map(taskRequest, Task.class);
    }


    public TaskDto mapTaskToDto(final Task task)
    {
        LOGGER.debug("Mapping Task to TaskDto");
        return this.modelMapper.map(task, TaskDto.class);
    }
}
