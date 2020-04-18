package de.tum.ar.researchplatform.repository;

import de.tum.ar.researchplatform.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by karthik on 9/9/2019
 */

/**
 * Data Repository class used to access User collection
 */
public interface UserRepository extends MongoRepository<User,String> {

    /**
     * Find User object by TUM ID
     * @param tumId
     * @return User object
     */
    public User findByTumId(String tumId);

    /**
     * Find User object by name
     * @param name
     * @return User object
     */
    public User findByName(String name);
}
