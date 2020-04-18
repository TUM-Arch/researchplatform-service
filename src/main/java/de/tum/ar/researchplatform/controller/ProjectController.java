package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.exception.CustomNotFoundException;
import de.tum.ar.researchplatform.model.Project;
import de.tum.ar.researchplatform.model.request.ProjectsRequestObject;
import de.tum.ar.researchplatform.model.response.ProjectWorkflowAdvancedResponseObject;
import de.tum.ar.researchplatform.model.response.ProjectsResponseObject;
import de.tum.ar.researchplatform.service.project.ProjectServiceImpl;
import de.tum.ar.researchplatform.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
        List<Project> projectList = projectService.listAll();
        projectsResponseObject.setProjectsList(projectList);
        return projectsResponseObject;
    }

    /**
     * Endpoint to get all Projects for User
     * @return ProjectsResponseObject as list of Projects
     */
    @GetMapping(value = "/projects/my", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectsResponseObject getAllProjectsForUser(@RequestHeader(value="userId" , required = true) String userId
            , @RequestHeader(value="status" , required = false) Constants.ProjectStatus status) {
        ProjectsResponseObject projectsResponseObject = new ProjectsResponseObject();
        List<Project> projectList = new ArrayList<>();
        if(userId != null || !userId.isBlank()) {
            projectList = projectService.findByUserId(userId);
        }
        if(status != null) {
            projectList = projectService.filterByStatus(projectList, status);
        }
        projectsResponseObject.setProjectsList(projectList);
        return projectsResponseObject;
    }

    /**
     * Endpoint to get all Projects for an admin
     * @return ProjectsResponseObject as list of Projects
     */
    @GetMapping(value = "/projects/manage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectsResponseObject getAllProjectsForAdmin() {
        ProjectsResponseObject projectsResponseObject = new ProjectsResponseObject();
        List<Project> projectList = new ArrayList<>();
        projectList = projectService.filterBySubmittedAndApproved(projectList);
        projectsResponseObject.setProjectsList(projectList);
        return projectsResponseObject;
    }

    /**
     * Endpoint to get a single Project by id
     * @return a single Project
     */
    @GetMapping(value = "/projects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Project getProjectById(@PathVariable String id) throws CustomNotFoundException {
        Project project = projectService.findById(id);
        return project;
    }

    /**
     * Endpoint to add a Project
     * @return a single Project
     */
    @PostMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public Project addProject(@Valid @RequestBody ProjectsRequestObject projectDetails) {
        Project project = new Project(projectDetails.getUserId(),
                projectDetails.getName(),
                projectDetails.getChairName(),
                projectDetails.getDescription(),
                projectDetails.getImageId(),
                projectDetails.getTags(),
                projectDetails.getFields());
        return projectService.saveOrUpdate(project);
    }

    /**
     * Endpoint to update a Project
     * @return a single Project
     */
    @PutMapping(value = "/projects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Project updateProject(@PathVariable String id, @Valid @RequestBody ProjectsRequestObject projectDetails) throws CustomNotFoundException {
        Project project = projectService.findById(id);
        project.setName(projectDetails.getName());
        project.setChairName(projectDetails.getChairName());
        project.setDescription(projectDetails.getDescription());
        project.setImageId(projectDetails.getImageId());
        project.setUserId(projectDetails.getUserId());
        project.setTags(projectDetails.getTags());
        project.setFields(projectDetails.getFields());
        return projectService.saveOrUpdate(project);
    }

    /**
     * Endpoint to delete all Projects
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

    /**
     * Endpoint to submit project
     * @return a single Project
     */
    @PutMapping(value = "/projects/submit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectWorkflowAdvancedResponseObject submitProject(@PathVariable String id) throws CustomNotFoundException {
        ProjectWorkflowAdvancedResponseObject responseObject = new ProjectWorkflowAdvancedResponseObject();
        Project project = projectService.advanceWorkflow(id);
        responseObject.setId(project.getId());
        responseObject.setStatus(project.getStatus());
        return responseObject;
    }

    /**
     * Endpoint to approve project
     * @return a single Project
     */
    @PutMapping(value = "/projects/approve/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectWorkflowAdvancedResponseObject approveProject(@PathVariable String id) throws CustomNotFoundException {
        ProjectWorkflowAdvancedResponseObject responseObject = new ProjectWorkflowAdvancedResponseObject();
        Project project = projectService.advanceWorkflow(id);
        responseObject.setId(project.getId());
        responseObject.setStatus(project.getStatus());
        return responseObject;
    }
}
