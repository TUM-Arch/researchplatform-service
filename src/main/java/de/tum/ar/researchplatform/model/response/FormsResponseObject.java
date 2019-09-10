package de.tum.ar.researchplatform.model.response;

/**
 * Created by karthik on 9/10/2019
 */

import de.tum.ar.researchplatform.model.Form;

import java.util.ArrayList;
import java.util.List;

/**
 * Response object for list of all forms
 */
public class FormsResponseObject {

    private List<Form> formsList;

    /**
     * No arg constructor
     */
    public FormsResponseObject() {
        this.formsList = new ArrayList<>();
    }

    /**
     * Get list of Forms
     * @return list of Forms
     */
    public List<Form> getFormsList() {
        return formsList;
    }

    /**
     * Set Forms list
     * @param formsList
     */
    public void setFormsList(List<Form> formsList) {
        this.formsList = formsList;
    }

    /**
     * Add single Form object to Forms list
     * @param form
     */
    public void addForm(Form form) {
        this.formsList.add(form);
    }
}
