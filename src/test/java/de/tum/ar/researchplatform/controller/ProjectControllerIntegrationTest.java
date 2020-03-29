package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.Field;
import de.tum.ar.researchplatform.model.Project;
import de.tum.ar.researchplatform.model.request.ProjectsRequestObject;
import de.tum.ar.researchplatform.service.project.ProjectService;
import de.tum.ar.researchplatform.util.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by karthik on 3/29/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private String endpoint = "/api/projects";

    @Autowired
    private ProjectService projectService;

    @Before
    public void setup() throws Exception {
        RestAssured.port = port;
        // Create two objects
        Project project1 = new Project();
        project1.setUserId("Abc");
        Project project2 = new Project();
        project2.setUserId("Xyz");
        projectService.saveOrUpdate(project1);
        projectService.saveOrUpdate(project2);
    }

    @After
    public void breakdown() {
        projectService.deleteAll();
    }

    @Test
    public void testGET_OK() {
        when()
                .request(Method.GET, endpoint)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testGETwithHeader_OK() {
        given()
                .header("userId", "Abc")
                .when()
                .request(Method.GET, endpoint)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .body("numberOfProjects", equalTo(1));
    }

    @Test
    public void testDELETE_OK() {
        when()
                .request(Method.DELETE, endpoint)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testPOST_OK() {
        ProjectsRequestObject projectsRequestObject = new ProjectsRequestObject(
                "Name"
                , "ChairName"
                , "Description"
                , "UserId"
                , new ArrayList<String>()
                , new ArrayList<Field>()
                , Constants.ProjectStatus.SUBMITTED);

        with()
                .body(projectsRequestObject)
                .contentType(ContentType.JSON)
                .when()
                .request(Method.POST, endpoint)
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK);
    }
}
