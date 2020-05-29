package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.User;
import de.tum.ar.researchplatform.model.request.UsersRequestObject;
import de.tum.ar.researchplatform.service.user.UserService;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;

/**
 * Created by karthik on 3/29/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest extends AbstractControllerIntegrationTest {

    private String endpoint = "/api/users";

    @Autowired
    private UserService userService;

    @Override
    public void localSetup() {
        // Create two objects
        User user1 = new User();
        user1.setTumId("1");
        User user2 = new User();
        user2.setTumId("2");
        userService.saveOrUpdate(user1);
        userService.saveOrUpdate(user2);
    }

    @After
    public void breakdown() {
        userService.deleteAll();
    }

    @Test
    public void testGET_OK() {
        given()
                .header("Authorization", this.jwtAdmin)
                .when()
                .request(Method.GET, endpoint)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testGET_NOT_FOUND() {
        given()
                .header("Authorization", this.jwtAdmin)
                .when()
                .request(Method.GET, endpoint + "/non_existing_id")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void testDELETE_OK() {
        given()
                .header("Authorization", this.jwtAdmin)
                .when()
                .request(Method.DELETE, endpoint)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testPOST_OK() {
        UsersRequestObject usersRequestObject = new UsersRequestObject(
                "User"
                , "Xyz123"
                , false);

        with()
                .body(usersRequestObject)
                .contentType(ContentType.JSON)
                .given()
                .header("Authorization", this.jwtAdmin)
                .when()
                .request(Method.POST, endpoint)
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK);
    }
}
