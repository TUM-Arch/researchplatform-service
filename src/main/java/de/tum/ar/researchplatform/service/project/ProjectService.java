package de.tum.ar.researchplatform.service.project;

import de.tum.ar.researchplatform.model.Project;
import de.tum.ar.researchplatform.util.Constants;

import java.util.List;

/**
 * Created by karthik on 9/10/2019
 */
public interface ProjectService {

    /**
     * List all Project objects
     * @return All Project objects
     */
    List<Project> listAll();

    /**
     * Find Project object by id
     * @param id
     * @return Project object found
     */
    Project findById(String id);

    /**
     * Find Projects for user with name
     * @param userId
     * @param name
     * @return projects found
     */
    List<Project> findByNameForUser(String userId, String name);

    /**
     * Save or Update Project object
     * @param project
     * @return Project object saved or updated
     */
    Project saveOrUpdate(Project project);

    /**
     * Delete Project object
     * @param project
     */
    void delete(Project project);

    /**
     * Delete Project object by id
     * @param id
     */
    void deleteById(String id);

    /**
     * Check if Project object exists by id
     * @param id
     * @return true if it exists
     */
    boolean existsById(String id);

    /**
     * Delete all Project objects
     */
    void deleteAll();

    /**
     * Find Project object by User ID
     * @param userId
     * @return All Project objects found
     */
    List<Project> findByUserId(String userId);

    /**
     * Filter projects by status
     * @param projects
     * @return
     */
    List<Project> filterByStatus(List<Project> projects, Constants.ProjectStatus status);

    /**
     * Filter projects by submitted and approved
     * @param projects
     * @return
     */
    List<Project> filterBySubmittedAndApproved(List<Project> projects);

    /**
     * Advance project workflow
     * @return updated project
     */
    Project advanceWorkflow(String id);
}
