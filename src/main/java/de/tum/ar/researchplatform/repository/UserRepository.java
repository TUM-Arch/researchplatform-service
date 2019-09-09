package de.tum.ar.researchplatform.repository;

import de.tum.ar.researchplatform.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by karthik on 9/9/2019
 */

/**
 * Data Repository class used to access User collection
 */
@RestResource(exported = false)
public interface UserRepository extends MongoRepository<User,String> {

    /**
     * Find User object by TUM ID
     * @param tumid
     * @return User object
     */
    public User findByTumid(String tumid);

    /**
     * Find User object by name
     * @param name
     * @return User object
     */
    public User findByName(String name);
}
