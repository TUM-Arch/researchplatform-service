package de.tum.ar.researchplatform.model.subfields;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by karthik on 1/5/2020
 */

/**
 * This field represents a SELECT(dropdown) field
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SelectField implements SubField {
    private String defaultValue;

    private List<String> values;

    private String selectedValue;

    public SelectField(String defaultValue, List<String> values, String selectedValue) {
        this.defaultValue = defaultValue;
        this.values = values;
        this.selectedValue = selectedValue;
    }
}
