package pu.master.rest.controllers;


import java.util.List;
import org.springframework.http.ResponseEntity;


public class ProjectController
{

    public ResponseEntity<Void> createProject(final ProjectRequest projectRequest)
    {
        return null;
    }


    public ResponseEntity<ProjectDto> getProjectById(final long id)
    {
        return null;
    }


    public ResponseEntity<List<ProjectDto>> getProjectsByUserId(final long id)
    {
        return null;
    }

    private class ProjectRequest {}
    private class ProjectDto {}
}
