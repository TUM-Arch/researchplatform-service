package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.response.UsersResponseObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by karthik on 9/9/2019
 */
@RestController
public interface UserController {

    /**
     * Endpoint to get all Users
     * @return UsersResponseObject as list of Users
     */
    @GetMapping(value = "/users")
    public UsersResponseObject getAllUsers();

}
