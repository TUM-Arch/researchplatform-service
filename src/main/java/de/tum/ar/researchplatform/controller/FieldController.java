package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.exception.CustomNotFoundException;
import de.tum.ar.researchplatform.model.Field;
import de.tum.ar.researchplatform.model.request.FieldsRequestObject;
import de.tum.ar.researchplatform.model.response.FieldsResponseObject;
import de.tum.ar.researchplatform.service.field.FieldServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public FieldsResponseObject getFields() {
        FieldsResponseObject fieldsResponseObject = new FieldsResponseObject();
        fieldsResponseObject.setFieldsList(fieldService.listAll());
        return fieldsResponseObject;
    }

    /**
     * Endpoint to get a single Field by id
     * @return a single Field
     */
    @GetMapping(value = "/fields/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Field getFieldById(@PathVariable String id) throws CustomNotFoundException {
        Field field = fieldService.findById(id);
        return field;
    }

    /**
     * Endpoint to add a Field
     * @return a single Field
     */
    @PostMapping(value = "/fields", produces = MediaType.APPLICATION_JSON_VALUE)
    public Field addField(@Valid @RequestBody FieldsRequestObject fieldDetails) {
        Field field = new Field(fieldDetails.getNameEn(),
                fieldDetails.getNameDe(),
                fieldDetails.getValueEn(),
                fieldDetails.getValueDe(),
                fieldDetails.getDescription(),
                fieldDetails.isRequired(),
                fieldDetails.getLength());
        return fieldService.saveOrUpdate(field);
    }

    /**
     * Endpoint to update a Field
     * @return a single Field
     */
    @PutMapping(value = "/fields/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Field updateField(@PathVariable String id, @Valid @RequestBody FieldsRequestObject fieldDetails) throws CustomNotFoundException {
        Field field = fieldService.findById(id);
        field.setNameEn(fieldDetails.getNameEn());
        field.setNameDe(fieldDetails.getNameDe());
        field.setValueEn(fieldDetails.getValueEn());
        field.setValueDe(fieldDetails.getValueDe());
        field.setDescription(fieldDetails.getDescription());
        field.setRequired(fieldDetails.isRequired());
        field.setLength(fieldDetails.getLength());
        return fieldService.saveOrUpdate(field);
    }

    /**
     * Endpoint to delete all Fields
     */
    @DeleteMapping(value = "/fields")
    public void deleteAllFields() {
        fieldService.deleteAll();
    }

    /**
     * Endpoint to delete a Field by id
     */
    @DeleteMapping(value = "/fields/{id}")
    public void deleteFieldById(@PathVariable String id) {
        fieldService.deleteById(id);
    }
}
