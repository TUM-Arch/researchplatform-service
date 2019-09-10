package de.tum.ar.researchplatform.service;

import de.tum.ar.researchplatform.model.User;
import de.tum.ar.researchplatform.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by karthik on 9/9/2019
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Iterable listAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User saveOrUpdate(User user) {
        userRepository.save(user);
        logger.info("Updated User: " + user);
        return user;
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
        logger.info("Deleted User: " + user);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        if(userRepository.existsById(id)) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
        logger.info("Deleted All Users");
    }

    @Override
    public User findByTumId(String tumId) {
        return userRepository.findByTumId(tumId);
    }
}
