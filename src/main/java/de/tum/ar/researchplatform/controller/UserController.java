package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.User;
import de.tum.ar.researchplatform.model.response.UsersResponseObject;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    /**
     * Endpoint to get a single User by id
     * @return a single User
     */
    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable String id);
    
    /**
     * Endpoint to get a single User by name
     * @return a single User
     */
    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserByName(@RequestParam String name);
    
    /**
     * Endpoint to add a User
     * @return a single User
     */
    @PostMapping(value = "/users")
    public User addUser(@RequestBody User user);
    
    /**
     * Endpoint to update a User
     * @return a single User
     */
    @PutMapping(value = "/users")
    public User updateUser(User user);
    
    /**
     * Endpoint to delete a User
     */
    @DeleteMapping(value = "/users")
    public void deleteAllUsers();
    
    /**
     * Endpoint to delete a User by id
     */
    @DeleteMapping(value = "/users/{id}")
    public void deleteUserById(@PathVariable String id);
    
}
