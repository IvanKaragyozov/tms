package pu.master.tmsapi.mappers;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pu.master.tmsapi.models.dtos.ProjectDto;
import pu.master.tmsapi.models.entities.Project;
import pu.master.tmsapi.models.requests.ProjectRequest;


@Mapper
public class ProjectMapper
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectMapper.class);

    private final ModelMapper modelMapper;


    @Autowired
    public ProjectMapper(final ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }


    public Project mapProjectRequestToProject(final ProjectRequest projectRequest)
    {
        LOGGER.debug("Mapping ProjectRequest to Project");
        return this.modelMapper.map(projectRequest, Project.class);
    }


    public ProjectDto mapProjectToDto(final Project project)
    {
        LOGGER.debug("Mapping Project to ProjectDto");
        return this.modelMapper.map(project, ProjectDto.class);
    }
}
