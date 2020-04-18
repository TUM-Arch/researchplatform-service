package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.response.FieldsResponseObject;
import de.tum.ar.researchplatform.service.field.FieldServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by karthik on 9/10/2019
 */
@RestController
@RequestMapping(value = "/api")
public class FieldController {

    @Autowired
    FieldServiceImpl fieldService;

    /**
     * Endpoint to get all Fields
     * @return FieldsResponseObject as list of Fields
     */
    @GetMapping(value = "/fields", produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldsResponseObject getFields(
            @RequestHeader(value="Active" , required = false, defaultValue = "true") boolean isActive) {
        FieldsResponseObject fieldsResponseObject = new FieldsResponseObject();
        if(isActive) {
            fieldsResponseObject.setFieldsList(fieldService.listAllActive());
        }
        else {
            fieldsResponseObject.setFieldsList(fieldService.listAll());
        }
        return fieldsResponseObject;
    }
}
