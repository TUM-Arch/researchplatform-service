package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.Project;
import de.tum.ar.researchplatform.model.request.ProjectsRequestObject;
import de.tum.ar.researchplatform.model.response.ProjectsResponseObject;
import de.tum.ar.researchplatform.service.project.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    /**
     * Endpoint to get a single Project by id
     * @return a single Project
     */
    @GetMapping(value = "/project/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Project getProjectById(@PathVariable String id) {
        Project project = projectService.findById(id);
        return project;
    }

    /**
     * Endpoint to get Projects by userid
     * @return a single Project
     */
    @GetMapping(value = "/projects/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectsResponseObject getProjectByUserId(@PathVariable String userid) {
        ProjectsResponseObject projectsResponseObject = new ProjectsResponseObject();
        projectsResponseObject.setProjectsList(projectService.findByUserId(userid));
        return projectsResponseObject;
    }

    /**
     * Endpoint to add a Project
     * @return a single Project
     */
    @PostMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public Project addProject(@Valid @RequestBody ProjectsRequestObject projectDetails) {
        Project project = new Project(projectDetails.getUserId(),
                projectDetails.getStatus(),
                projectDetails.getName(),
                projectDetails.getChairName(),
                projectDetails.getDescription(),
                projectDetails.getTags(),
                projectDetails.getFields());
        return projectService.saveOrUpdate(project);
    }

    /**
     * Endpoint to update a Project
     * @return a single Project
     */
    @PutMapping(value = "/projects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Project updateProject(@PathVariable String id, @Valid @RequestBody ProjectsRequestObject projectDetails) {
        Project project = projectService.findById(id);
        project.setName(projectDetails.getName());
        project.setChairName(projectDetails.getChairName());
        project.setDescription(projectDetails.getDescription());
        project.setUserId(projectDetails.getUserId());
        project.setTags(projectDetails.getTags());
        project.setFields(projectDetails.getFields());
        project.setStatus(projectDetails.getStatus());
        return projectService.saveOrUpdate(project);
    }

    /**
     * Endpoint to delete a Project
     */
    @DeleteMapping(value = "/projects")
    public void deleteAllProjects() {
        projectService.deleteAll();
    }

    /**
     * Endpoint to delete a Project by id
     */
    @DeleteMapping(value = "/projects/{id}")
    public void deleteProjectById(@PathVariable String id) {
        projectService.deleteById(id);
    }
}
