package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.Tag;
import de.tum.ar.researchplatform.model.request.TagsRequestObject;
import de.tum.ar.researchplatform.service.tag.TagService;
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
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

/**
 * Created by raymond on 6/1/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TagControllerIntegrationTest extends AbstractControllerIntegrationTest {
    private String endpoint = "/api/tags";

    @Autowired
    private TagService tagService;

    @Override
    public void localSetup() {
        // Create two objects
        Tag tag1 = new Tag();
        Tag tag2 = new Tag();
        tagService.saveOrUpdate(tag1);
        tagService.saveOrUpdate(tag2);
    }

    @After
    public void breakdown() {
        tagService.deleteAll();
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
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);
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
        TagsRequestObject tagsRequestObject = new TagsRequestObject(new ArrayList<String>(Arrays.asList("Tag1", "Tag2")));

        with()
                .body(tagsRequestObject)
                .contentType(ContentType.JSON)
                .given()
                .header("Authorization", this.jwtAdmin)
                .when()
                .request(Method.POST, endpoint)
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK);
    }
}
