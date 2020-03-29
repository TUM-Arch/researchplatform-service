package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.User;
import de.tum.ar.researchplatform.model.request.UsersRequestObject;
import de.tum.ar.researchplatform.model.response.UsersResponseObject;
import de.tum.ar.researchplatform.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

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

    /**
     * Endpoint to get a single User by id
     * @return a single User
     */
    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable String id) {
        User user = userService.findById(id);
        return user;
    }
    
    /**
     * Endpoint to add a User
     * @return a single User
     */
    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@Valid @RequestBody UsersRequestObject userDetails) {
    	User user = new User(userDetails.getName(), userDetails.getTumId(), userDetails.getProjectIds(), userDetails.isAdmin());
        return userService.saveOrUpdate(user);
    }
    
    /**
     * Endpoint to update a User
     * @return a single User
     */
    @PutMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@PathVariable String id, @Valid @RequestBody UsersRequestObject userDetails) {
    	User user = userService.findById(id);
    	user.setName(userDetails.getName());
    	user.setProjectIds(userDetails.getProjectIds());
        user.setTumId(userDetails.getTumId());
        user.setAdmin(userDetails.isAdmin());
    	return userService.saveOrUpdate(user);
    }
    
    /**
     * Endpoint to delete all Users
     */
    @DeleteMapping(value = "/users")
    public void deleteAllUsers() {
        userService.deleteAll();
    }
    
    /**
     * Endpoint to delete a User by id
     */
    @DeleteMapping(value = "/users/{id}")
    public void deleteUserById(@PathVariable String id) {
        userService.deleteById(id);
    }
    
}
