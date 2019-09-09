package de.tum.ar.researchplatform.service;

import de.tum.ar.researchplatform.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by karthik on 9/9/2019
 */
public interface UserService {

    /**
     * List all User objects
     * @return All User objects
     */
    Iterable listAll();

    /**
     * Find User object by id
     * @param id
     * @return User object found
     */
    User findById(String id);

    /**
     * Find User object by TUM ID
     * @param tumid
     * @return User object found
     */
    User findByTumid(String tumid);

    /**
     * Find User object by name
     * @param name
     * @return User object found
     */
    User findByName(String name);

    /**
     * Save or Update User object
     * @param user
     * @return User object saved or updated
     */
    User saveOrUpdate(User user);

    /**
     * Delete User object
     * @param user
     */
    void delete(User user);

    /**
     * Delete User object by id
     * @param id
     */
    void deleteById(String id);

    /**
     * Check if User object exists by id
     * @param id
     * @return
     */
    boolean existsById(String id);

    /**
     * Delete all User objects
     */
    void deleteAll();
}
