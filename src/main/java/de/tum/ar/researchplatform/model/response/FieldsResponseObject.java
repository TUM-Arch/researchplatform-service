package de.tum.ar.researchplatform.model.response;

/**
 * Created by karthik on 9/10/2019
 */

import de.tum.ar.researchplatform.model.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * Response object for list of all fields
 */
public class FieldsResponseObject {

    private int numberOfFields;
    private List<Field> fieldsList;

    /**
     * No arg constructor
     */
    public FieldsResponseObject() {
        this.fieldsList = new ArrayList<>();
        this.numberOfFields = 0;
    }

    /**
     * Get list of Fields
     * @return list of Fields
     */
    public List<Field> getFieldsList() {
        return fieldsList;
    }

    /**
     * Set Fields list
     * @param fieldsList
     */
    public void setFieldsList(List<Field> fieldsList) {
        this.fieldsList = fieldsList;
        this.numberOfFields = fieldsList.size();
    }

    /**
     * Get number of field objects
     * @return number of fields
     */
    public int getNumberOfFields() {
        return numberOfFields;
    }

    /**
     * Add single Field object to Fields list
     * @param field
     */
    public void addField(Field field) {
        this.fieldsList.add(field);
        this.numberOfFields++;
    }
}
