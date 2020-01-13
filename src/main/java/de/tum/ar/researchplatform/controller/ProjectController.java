package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.Project;
import de.tum.ar.researchplatform.model.response.ProjectsResponseObject;
import de.tum.ar.researchplatform.service.project.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by karthik on 9/11/2019
 */
@RestController
@RequestMapping(value = "/api")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;

    /**
     * Endpoint to get all Projects
     * @return ProjectsResponseObject as list of Projects
     */
    @GetMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectsResponseObject getAllProjects() {
        ProjectsResponseObject projectsResponseObject = new ProjectsResponseObject();
        projectsResponseObject.setProjectsList(projectService.listAll());
        return projectsResponseObject;
    }
}
