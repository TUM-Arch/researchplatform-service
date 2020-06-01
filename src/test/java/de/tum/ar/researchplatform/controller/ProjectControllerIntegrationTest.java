package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.Field;
import de.tum.ar.researchplatform.model.Project;
import de.tum.ar.researchplatform.model.Tag;
import de.tum.ar.researchplatform.model.request.ProjectsRequestObject;
import de.tum.ar.researchplatform.service.project.ProjectService;
import de.tum.ar.researchplatform.util.Constants;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by karthik on 3/29/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectControllerIntegrationTest extends AbstractControllerIntegrationTest {

    private String endpoint = "/api/projects";

    @Autowired
    private ProjectService projectService;

    @Override
    public void localSetup() {
        // Create two objects
        Project project1 = new Project();
        project1.setUserId("Abc");
        Project project2 = new Project();
        project2.setUserId("Xyz");
        project2.setStatus(Constants.ProjectStatus.NOTSUBMITTED);
        projectService.saveOrUpdate(project1);
        projectService.saveOrUpdate(project2);
    }

    @After
    public void breakdown() {
        projectService.deleteAll();
    }

    @Test
    public void testGET_OK() {
        given()
                .header("Authorization", this.jwtAdmin)
                .when()
                .request(Method.GET, endpoint)
                .then().assertThat()
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
    public void testProjectsForUser_GETwithHeader_OK() {
        given()
                .header("userId", "Abc")
                .header("Authorization", this.jwtAdmin)
                .when()
                .request(Method.GET, endpoint + "/my")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .body("numberOfProjects", equalTo(1));
    }

    @Test
    public void testProjectsForUserByStatus_GETwithHeader_OK() {
        given()
                .header("userId", "Xyz")
                .header("status", "NOTSUBMITTED")
                .header("Authorization", this.jwtAdmin)
                .when()
                .request(Method.GET, endpoint + "/my")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .body("numberOfProjects", equalTo(1));
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
        ProjectsRequestObject projectsRequestObject = new ProjectsRequestObject(
                "Name"
                , "ChairName"
                , "Description"
                , "testImageId"
                , "UserId"
                , new ArrayList<Tag>()
                , new ArrayList<Field>());

        with()
                .body(projectsRequestObject)
                .contentType(ContentType.JSON)
                .given()
                .header("Authorization", this.jwtAdmin)
                .when()
                .request(Method.POST, endpoint)
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK);
    }
}
