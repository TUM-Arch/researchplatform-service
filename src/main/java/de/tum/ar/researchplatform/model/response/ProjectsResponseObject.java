package de.tum.ar.researchplatform.model.response;

/**
 * Created by karthik on 9/11/2019
 */

import de.tum.ar.researchplatform.model.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Response object for list of all projects
 */
@Getter
@NoArgsConstructor
public class ProjectsResponseObject {

    @Setter
    private int numberOfProjects;
    private List<Project> projectsList;

    /**
     * Set Projects list
     * @param projectsList
     */
    public void setProjectsList(List<Project> projectsList) {
        this.projectsList = projectsList;
        this.numberOfProjects = projectsList.size();
    }

    /**
     * Add single Project object to Projects list
     * @param project
     */
    public void addProject(Project project) {
        this.projectsList.add(project);
        this.numberOfProjects++;
    }
}
