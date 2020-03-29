package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.Project;
import de.tum.ar.researchplatform.service.project.ProjectService;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by karthik on 3/29/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SearchControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private String endpoint = "/api/search";

    @Autowired
    private ProjectService projectService;

    @Before
    public void setup() throws Exception {
        RestAssured.port = port;
        // Create two objects
        Project project1 = new Project();
        project1.setUserId("Abc");
        project1.setName("IJK");
        Project project2 = new Project();
        project2.setUserId("Xyz");
        project2.setName("IJK");
        projectService.saveOrUpdate(project1);
        projectService.saveOrUpdate(project2);
    }

    @After
    public void breakdown() {
        projectService.deleteAll();
    }

    @Test
    public void testSearchProjects_GETwithHeader_OK() {
        given()
                .header("userId", "Abc")
                .header("name", "IJK")
                .when()
                .request(Method.GET, endpoint + "/projects")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .body("numberOfProjects", equalTo(1));
    }

}
