package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.model.Form;
import de.tum.ar.researchplatform.model.response.FormsResponseObject;
import de.tum.ar.researchplatform.service.FormServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by karthik on 9/10/2019
 */
@Component
public class FormControllerImpl implements FormController{

    @Autowired
    private FormServiceImpl formService;

    @Override
    public FormsResponseObject getAllForms() {
        FormsResponseObject formsResponseObject = new FormsResponseObject();
        Iterable<Form> formList = formService.listAll();
        for (Form form: formList) {
            formsResponseObject.addForm(form);
        }
        return formsResponseObject;
    }
}
