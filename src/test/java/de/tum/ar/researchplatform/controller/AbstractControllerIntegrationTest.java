package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.component.security.JwtBuilder;
import de.tum.ar.researchplatform.exception.CustomNotFoundException;
import de.tum.ar.researchplatform.model.User;
import de.tum.ar.researchplatform.service.user.UserService;
import io.restassured.RestAssured;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * Created by karthik on 5/30/2020
 */
public abstract class AbstractControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private UserService userService;

    protected String jwtAdmin = "Bearer " + new JwtBuilder().buildJwtForAdmin("testAdmin", "Test", "test");;

    @Before
    public void setup() throws Exception {
        RestAssured.port = port;
        // Add user if it does not exist
        User testAdmin;
        try {
            testAdmin = userService.findByTumId("testAdmin");
        } catch (CustomNotFoundException e) {
            testAdmin = new User();
            testAdmin.setTumId("testAdmin");
            userService.saveOrUpdate(testAdmin);
        }
        localSetup();
    }

    public abstract void localSetup();
}
