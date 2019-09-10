package de.tum.ar.researchplatform.repository;

import de.tum.ar.researchplatform.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by karthik on 9/10/2019
 */

/**
 * Data Repository class used to access Project collection
 */
@RestResource(exported = false)
public interface ProjectRepository extends MongoRepository<Project,String> {

    /**
     * Find Project object by userId
     * @param userId
     * @return Project object
     */
    public Project findByUserId(String userId);
}