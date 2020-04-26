package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.Field;
import de.tum.ar.researchplatform.model.request.FieldsRequestObject;
import de.tum.ar.researchplatform.service.field.FieldService;
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

import static io.restassured.RestAssured.*;

/**
 * Created by karthik on 3/29/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FieldControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private String endpoint = "/api/fields";

    @Autowired
    private FieldService fieldService;

    @Before
    public void setup() throws Exception {
        RestAssured.port = port;
        // Create two objects
        Field field1 = new Field();
        Field field2 = new Field();
        fieldService.saveOrUpdate(field1);
        fieldService.saveOrUpdate(field2);
    }

    @After
    public void breakdown() {
        fieldService.deleteAll();
    }

    @Test
    public void testGET_OK() {
        when()
                .request(Method.GET, endpoint)
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
                .request(Method.DELETE, endpoint)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testPOST_OK() {
        FieldsRequestObject fieldsRequestObject = new FieldsRequestObject(
                "NameEn"
                , "NameDe"
                , "ValueEn"
                , "ValueDe"
                , "Desc"
                , false);

        with()
                .body(fieldsRequestObject)
                .contentType(ContentType.JSON)
                .when()
                .request(Method.POST, endpoint)
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK);
    }
}
