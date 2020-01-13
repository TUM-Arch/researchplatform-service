package de.tum.ar.researchplatform.model.response;

import de.tum.ar.researchplatform.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by karthik on 9/9/2019
 */

/**
 * Response object for list of all users
 */
@Getter
@NoArgsConstructor
public class UsersResponseObject {

    @Setter
    private int numberOfUsers;
    private List<User> usersList;

    /**
     * Set Users list
     * @param usersList
     */
    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
        this.numberOfUsers = usersList.size();
    }

    /**
     * Add single User object to Users list
     * @param user
     */
    public void addUser(User user) {
        this.usersList.add(user);
        this.numberOfUsers ++;
    }
}
