package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.response.ProjectsResponseObject;
import de.tum.ar.researchplatform.service.project.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by karthik on 3/29/2020
 */
@RestController
@RequestMapping(value = "/api/search")
public class SearchController {

    @Autowired
    private ProjectServiceImpl projectService;

    /**
     * Endpoint to get searched Projects by userid
     * @return a list of matched Projects
     */
    @GetMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectsResponseObject getSearchedProjectsByUserId(@RequestHeader(value="userId" , required = true) String userId, @RequestHeader(value="name" , required = true) String name) {
        ProjectsResponseObject projectsResponseObject = new ProjectsResponseObject();
        projectsResponseObject.setProjectsList(projectService.findByNameForUser(userId, name));
        return projectsResponseObject;
    }
}
