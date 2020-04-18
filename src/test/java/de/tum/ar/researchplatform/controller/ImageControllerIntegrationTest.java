package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.Image;
import de.tum.ar.researchplatform.service.image.ImageService;
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

/**
 * Created by karthik on 4/18/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImageControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private String endpoint = "/api/images";

    @Autowired
    private ImageService imageService;

    private String imageId;

    @Before
    public void setup() throws Exception {
        RestAssured.port = port;
        // Create two objects
        Image image = new Image();
        image.setImage(null);
        Image savedImage = imageService.saveOrUpdate(image);
        this.imageId = savedImage.getId();
    }

    @After
    public void breakdown() {
        imageService.deleteAll();
    }

    @Test
    public void testGET_OK() {
        when()
                .request(Method.GET, endpoint + '/' + this.imageId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testGET_NOT_FOUND() {
        when()
                .request(Method.GET, endpoint + "/non_existing_id")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void testDELETE_OK() {
        when()
                .request(Method.DELETE, endpoint + '/' + this.imageId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }
}
