package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.Project;
import de.tum.ar.researchplatform.model.response.ProjectsResponseObject;
import de.tum.ar.researchplatform.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by karthik on 9/11/2019
 */
@Component
public class ProjectControllerImpl implements ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;

    @Override
    public ProjectsResponseObject getAllProjects() {
        ProjectsResponseObject projectsResponseObject = new ProjectsResponseObject();
        Iterable<Project> projectList = projectService.listAll();
        for (Project project: projectList) {
            projectsResponseObject.addProject(project);
        }
        return projectsResponseObject;
    }
}
