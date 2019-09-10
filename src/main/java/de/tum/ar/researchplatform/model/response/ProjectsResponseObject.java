package de.tum.ar.researchplatform.model.response;

/**
 * Created by karthik on 9/11/2019
 */

import de.tum.ar.researchplatform.model.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Response object for list of all projects
 */
public class ProjectsResponseObject {

    private List<Project> projectsList;

    /**
     * No arg constructor
     */
    public ProjectsResponseObject() {
        this.projectsList = new ArrayList<>();
    }

    /**
     * Get list of Projects
     * @return list of Projects
     */
    public List<Project> getProjectsList() {
        return projectsList;
    }

    /**
     * Set Projects list
     * @param projectsList
     */
    public void setProjectsList(List<Project> projectsList) {
        this.projectsList = projectsList;
    }

    /**
     * Add single Project object to Projects list
     * @param project
     */
    public void addProject(Project project) {
        this.projectsList.add(project);
    }
}
