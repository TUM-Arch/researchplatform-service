package de.tum.ar.researchplatform.service.user;

import de.tum.ar.researchplatform.model.User;

import java.util.List;

/**
 * Created by karthik on 9/9/2019
 */
public interface UserService {

    /**
     * List all User objects
     * @return All User objects
     */
    List<User> listAll();

    /**
     * Find User object by id
     * @param id
     * @return User object found
     */
    User findById(String id);

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
     * @return true if it exists
     */
    boolean existsById(String id);

    /**
     * Delete all User objects
     */
    void deleteAll();

    /**
     * Find User object by TUM ID
     * @param tumId
     * @return User object found
     */
    User findByTumId(String tumId);
}
