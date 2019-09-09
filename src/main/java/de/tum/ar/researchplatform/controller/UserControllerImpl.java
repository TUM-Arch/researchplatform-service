package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.User;
import de.tum.ar.researchplatform.model.response.UsersResponseObject;
import de.tum.ar.researchplatform.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by karthik on 9/9/2019
 */
@Component
public class UserControllerImpl implements UserController {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public UsersResponseObject getAllUsers() {
        UsersResponseObject usersResponseObject = new UsersResponseObject();
        Iterable<User> userList = userService.listAll();
        for (User user: userList) {
            usersResponseObject.addUser(user);
        }
        return usersResponseObject;
    }
}
