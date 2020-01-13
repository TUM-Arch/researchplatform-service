package de.tum.ar.researchplatform.model.response;

/**
 * Created by karthik on 9/10/2019
 */

import de.tum.ar.researchplatform.model.Field;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Response object for list of all fields
 */
@Getter
@NoArgsConstructor
public class FieldsResponseObject {

    @Setter
    private int numberOfFields;
    private List<Field> fieldsList;

    /**
     * Set Fields list
     * @param fieldsList
     */
    public void setFieldsList(List<Field> fieldsList) {
        this.fieldsList = fieldsList;
        this.numberOfFields = fieldsList.size();
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
