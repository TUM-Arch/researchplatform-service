package de.tum.ar.researchplatform.model.response;

import de.tum.ar.researchplatform.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karthik on 9/9/2019
 */

/**
 * Response object for list of all users
 */
public class UsersResponseObject {

    private List<User> usersList;

    /**
     * No arg constructor
     */
    public UsersResponseObject() {
        this.usersList = new ArrayList<>();
    }

    /**
     * Get list of Users
     * @return list of Users
     */
    public List<User> getUsersList() {
        return usersList;
    }

    /**
     * Set Users list
     * @param usersList
     */
    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    /**
     * Add single User object to Users list
     * @param user
     */
    public void addUser(User user) {
        this.usersList.add(user);
    }
}
