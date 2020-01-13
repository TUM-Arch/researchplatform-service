package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.User;
import de.tum.ar.researchplatform.model.response.UsersResponseObject;
import de.tum.ar.researchplatform.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by karthik on 9/9/2019
 */
@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * Endpoint to get all Users
     * @return UsersResponseObject as list of Users
     */
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersResponseObject getAllUsers() {
        UsersResponseObject usersResponseObject = new UsersResponseObject();
        usersResponseObject.setUsersList(userService.listAll());
        return usersResponseObject;
    }
}
