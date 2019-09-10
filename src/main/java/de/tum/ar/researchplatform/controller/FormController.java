package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.response.FormsResponseObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by karthik on 9/10/2019
 */
@RestController
@RequestMapping(value = "/api")
public interface FormController {

    /**
     * Endpoint to get all Forms
     * @return FormsResponseObject as list of Forms
     */
    @GetMapping(value = "/forms", produces = MediaType.APPLICATION_JSON_VALUE)
    public FormsResponseObject getAllForms();
}
