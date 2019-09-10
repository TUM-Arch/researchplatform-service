package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.response.UsersResponseObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by karthik on 9/9/2019
 */
@RestController
@RequestMapping(value = "/api")
public interface UserController {

    /**
     * Endpoint to get all Users
     * @return UsersResponseObject as list of Users
     */
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersResponseObject getAllUsers();

}
