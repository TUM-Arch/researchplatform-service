package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.User;
import de.tum.ar.researchplatform.model.response.UsersResponseObject;
import de.tum.ar.researchplatform.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

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
    
	@Override
	public User getUserById(String id) {
		User user = userService.findById(id);
		return user;
	}

	@Override
	public User getUserByName(String name) {
		User user = userService.findByName(name);
		return user;
	}

	@Override
	public User addUser(User user) {
		return userService.saveOrUpdate(user);
	}

	@Override
	public User updateUser(User user) {
		return userService.saveOrUpdate(user);
	}

	@Override
	public void deleteAllUsers() {
		userService.deleteAll();
	}

	@Override
	public void deleteUserById(String id) {
		userService.deleteById(id);
	}
}
